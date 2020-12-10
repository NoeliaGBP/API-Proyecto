package mx.edu.utez.usuario.model;

import mx.edu.utez.request.Humano;
import mx.edu.utez.persona.model.Persona;
import mx.edu.utez.persona.model.PersonaDAO;
import mx.edu.utez.rol.model.Rol;
import mx.edu.utez.rol.model.RolDAO;
import mx.edu.utez.tools.ConnectionDB;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
    public Usuario login(String nombreUsuario, String contrasenia) {
        Usuario usuario = new Usuario();
        try {
            Connection con = ConnectionDB.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM usuario WHERE nombreUsuario LIKE ? AND contrasenia LIKE ? LIMIT 1");
            ps.setString(1, nombreUsuario);
            ps.setString(2, contrasenia);
            ResultSet rs = ps.executeQuery();
            PersonaDAO personaDAO = new PersonaDAO();
            RolDAO rolDAO = new RolDAO();
            while (rs.next()) {
                if (rs != null) {
                    usuario.setNombreUsuario(rs.getString(1));
                    usuario.setContrasenia("PRIVATE");
                    usuario.setToken(rs.getInt(3));
                    usuario.setIdPersona(personaDAO.getPersonaById(rs.getInt(4)));
                    usuario.setIdRol(rolDAO.getRolById(rs.getInt(5)));
                }
            }
            if (ps != null) ps.close();
            if (rs != null) rs.close();
            if (con != null) con.close();
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
        return usuario;
    }

    public List<Usuario> getUsuarios() {
        ArrayList<Usuario> usuarios = new ArrayList<>();
        try {
            Connection con = ConnectionDB.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM usuario");
            ResultSet rs = ps.executeQuery();
            PersonaDAO personaDAO = new PersonaDAO();
            RolDAO rolDAO = new RolDAO();
            while (rs.next()) {
                if (rs != null) {
                    Usuario usuario = new Usuario();
                    usuario.setNombreUsuario(rs.getString(1));
                    usuario.setContrasenia("PRIVATE");
                    usuario.setToken(rs.getInt(3));
                    usuario.setIdPersona(personaDAO.getPersonaById(rs.getInt(4)));
                    usuario.setIdRol(rolDAO.getRolById(rs.getInt(5)));
                    usuarios.add(usuario);
                }
            }
            if (con != null) con.close();
            if (ps != null) ps.close();
            if (rs != null) rs.close();
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
        return usuarios;
    }

    public Usuario getUsuarioByUser(String user) {
        Usuario usuario = new Usuario();
        try {
            Connection con = ConnectionDB.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM usuario WHERE nombreUsuario LIKE ?");
            ps.setString(1, user);
            ResultSet rs = ps.executeQuery();
            PersonaDAO personaDAO = new PersonaDAO();
            RolDAO rolDAO = new RolDAO();
            while (rs.next()) {
                if (rs != null) {
                    usuario.setNombreUsuario(rs.getString(1));
                    usuario.setContrasenia("PRIVATE");
                    usuario.setToken(rs.getInt(3));
                    usuario.setIdPersona(personaDAO.getPersonaById(rs.getInt(4)));
                    usuario.setIdRol(rolDAO.getRolById(rs.getInt(5)));
                }
            }
            if (con != null) con.close();
            if (ps != null) ps.close();
            if (rs != null) rs.close();
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
        return usuario;
    }

    public Usuario createUsuario(Usuario usuario) throws SQLException {
        Usuario nuevo = new Usuario();
        Connection con = null;
        try {
            con = ConnectionDB.getConnection();
            con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement("INSERT INTO `usuario`(`nombreUsuario`, `contrasenia`, `token`, `idPersona`, `idRol`) VALUES(?,?,?,?,?)");
            ps.setString(1, usuario.getNombreUsuario());
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(usuario.getContrasenia().getBytes(Charset.forName("UTF-8")));
            byte[] hashed = md.digest();
            String contraseniaEncrypt = byteToHex(hashed);
            ps.setString(2, contraseniaEncrypt);
            ps.setInt(3, usuario.getToken());//Ahorita solo es ejemplo xD
            ps.setInt(4, usuario.getIdPersona().getIdPersona());
            ps.setInt(5, usuario.getIdRol().getIdRol());

            boolean created = ps.executeUpdate() == 1;
            if (created) {
                con.commit();
                nuevo = getUsuarioByUser(usuario.getNombreUsuario());
            }
            if (ps != null) ps.close();
        } catch (Exception e) {
            con.rollback();
            e.getMessage();
            e.printStackTrace();
        } finally {
            if (con != null) con.close();
        }
        return nuevo;
    }

    public Humano createUsuarioByRol(Humano humano){
        Humano nuevoHumano = new Humano();
        try {
            RolDAO rolDAO = new RolDAO();
            Rol rol = rolDAO.getRolById(humano.getRol().getIdRol());
            if(rol.getNombreRol()!=null){
                PersonaDAO personaDAO = new PersonaDAO();
                Persona persona = personaDAO.createPersona(humano.getPersona());
                humano.getUsuario().setIdRol(rol);
                humano.getUsuario().setIdPersona(persona);
                if (persona.getNombre()!=null){
                    Usuario nuevo = createUsuario(humano.getUsuario());
                    nuevoHumano.setUsuario(nuevo);
                    nuevoHumano.setPersona(persona);
                    nuevoHumano.setRol(rol);
                }
            }
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
        return nuevoHumano;
    }

    public Usuario updateUsuario(Usuario usuario) throws SQLException {
        Usuario actualizado = new Usuario();
        Connection con = null;
        try {
            con = ConnectionDB.getConnection();
            con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement("UPDATE `usuario` SET `contrasenia`=?,`idPersona`=?,`idRol`=? WHERE `nombreUsuario` LIKE ?");
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(usuario.getContrasenia().getBytes(Charset.forName("UTF-8")));
            byte[] hashed = md.digest();
            String contraseniaEncrypt = byteToHex(hashed);
            ps.setString(1, contraseniaEncrypt);
            ps.setInt(2, usuario.getIdPersona().getIdPersona());
            ps.setInt(3, usuario.getIdRol().getIdRol());
            ps.setString(4, usuario.getNombreUsuario());
            boolean updated = ps.executeUpdate() == 1;
            if (updated) {
                con.commit();
                actualizado = getUsuarioByUser(usuario.getNombreUsuario());
            }
            if (ps != null) ps.close();
        } catch (Exception e) {
            con.rollback();
            e.getMessage();
            e.printStackTrace();
        } finally {
            if (con != null) con.close();
        }
        return actualizado;
    }

    public Usuario updateContrasenia(String usuario, String contrasenia) throws SQLException {
        Usuario actualizado = new Usuario();
        Connection con = null;
        try {
            con = ConnectionDB.getConnection();
            con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement("UPDATE `usuario` SET `contrasenia`=? WHERE `nombreUsuario` LIKE ?");
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(contrasenia.getBytes(Charset.forName("UTF-8")));
            byte[] hashed = md.digest();
            String contraseniaEncrypt = byteToHex(hashed);
            ps.setString(1, contraseniaEncrypt);
            ps.setString(2, usuario);
            boolean updated = ps.executeUpdate() == 1;
            if (updated) {
                con.commit();
                actualizado = getUsuarioByUser(usuario);
            }
            if (ps != null) ps.close();
        } catch (Exception e) {
            con.rollback();
            e.getMessage();
            e.printStackTrace();
        } finally {
            if (con != null) con.close();
        }
        return actualizado;
    }

    public Usuario updateContrasenia2(Contrasenia contrasenia) throws SQLException {
        Usuario actualizado = new Usuario();
        Connection con = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(contrasenia.getContrasenia().getBytes(Charset.forName("UTF-8")));
            byte[] hashed = md.digest();
            String contraseniaEncrypt = byteToHex(hashed);

            Usuario existe = login(contrasenia.getUsuario(), contraseniaEncrypt);
            if (existe.getNombreUsuario() != null) {
                con = ConnectionDB.getConnection();
                con.setAutoCommit(false);
                PreparedStatement ps = con.prepareStatement("UPDATE `usuario` SET `contrasenia`=? WHERE `nombreUsuario` LIKE ?");
                md.update(contrasenia.getContraseniaNueva().getBytes(Charset.forName("UTF-8")));
                byte[] hashed2 = md.digest();
                String contraseniaNuevaEncrypt = byteToHex(hashed2);
                ps.setString(1, contraseniaNuevaEncrypt);
                ps.setString(2, contrasenia.getUsuario());
                boolean updated = ps.executeUpdate() == 1;
                if (updated) {
                    con.commit();
                    actualizado = getUsuarioByUser(contrasenia.getUsuario());
                }
                if (ps != null) ps.close();
            }
        } catch (Exception e) {
            con.rollback();
            e.getMessage();
            e.printStackTrace();
        } finally {
            if (con != null) con.close();
        }
        return actualizado;
    }

    public boolean deleteUsuario(String usuario) throws SQLException {
        boolean deleted = false;
        Connection con = null;
        try {
            con = ConnectionDB.getConnection();
            con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement("DELETE FROM `usuario` WHERE nombreUsuario LIKE ?");
            ps.setString(1, usuario);

            deleted = ps.executeUpdate() == 1;
            if (deleted) {
                con.commit();
            }
            if (ps != null) ps.close();
        } catch (Exception e) {
            con.rollback();
            e.getMessage();
            e.printStackTrace();
        } finally {
            if (con != null) con.close();
        }
        return deleted;
    }

    public static String byteToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (Byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
