package mx.edu.utez.usuario.model;

import mx.edu.utez.persona.model.Persona;
import mx.edu.utez.persona.model.PersonaDAO;
import mx.edu.utez.rol.model.Rol;
import mx.edu.utez.rol.model.RolDAO;
import mx.edu.utez.tools.ConnectionDB;
import mx.edu.utez.usuario.model.request.Contrasenia;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
    public Usuario login(String user, String contrasenia) throws SQLException {
        Usuario usuarioN = new Usuario();
        Connection con = null;
        try {
            con = ConnectionDB.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM usuario WHERE contrasenia LIKE ? AND (correo LIKE '"+user+"' OR nombreUsuario LIKE '"+user+"') LIMIT 1");
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(contrasenia.getBytes(Charset.forName("UTF-8")));
            byte[] hashed = md.digest();
            String contraseniaEncrypt = byteToHex(hashed);
            ps.setString(1, contraseniaEncrypt);

            ResultSet rs = ps.executeQuery();
            PersonaDAO personaDAO = new PersonaDAO();
            RolDAO rolDAO = new RolDAO();
            while (rs.next()) {
                if (rs != null) {
                    usuarioN.setNombreUsuario(rs.getString(1));
                    usuarioN.setContrasenia("PRIVATE");
                    usuarioN.setToken(rs.getInt(3));
                    usuarioN.setCorreo(rs.getString(4));
                    usuarioN.setTelefono(rs.getLong(5));
                    usuarioN.setIdPersona(personaDAO.getPersonaById(rs.getInt(6)));
                    usuarioN.setIdRol(rolDAO.getRolById(rs.getInt(7)));
                }
            }
            if (ps != null) ps.close();
            if (rs != null) rs.close();
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        } finally {
            if (con != null) con.close();
        }
        return usuarioN;
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
                    usuario.setCorreo(rs.getString(4));
                    usuario.setTelefono(rs.getLong(5));
                    usuario.setIdPersona(personaDAO.getPersonaById(rs.getInt(6)));
                    usuario.setIdRol(rolDAO.getRolById(rs.getInt(7)));
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

    public List<Usuario> getUsuariosByRol(int rol) {
        ArrayList<Usuario> usuarios = new ArrayList<>();
        try {
            Connection con = ConnectionDB.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM usuario WHERE idRol = ?");
            ps.setInt(1, rol);
            ResultSet rs = ps.executeQuery();
            PersonaDAO personaDAO = new PersonaDAO();
            RolDAO rolDAO = new RolDAO();
            while (rs.next()) {
                if (rs != null) {
                    Usuario usuario = new Usuario();
                    usuario.setNombreUsuario(rs.getString(1));
                    usuario.setContrasenia("PRIVATE");
                    usuario.setToken(rs.getInt(3));
                    usuario.setCorreo(rs.getString(4));
                    usuario.setTelefono(rs.getLong(5));
                    usuario.setIdPersona(personaDAO.getPersonaById(rs.getInt(6)));
                    usuario.setIdRol(rolDAO.getRolById(rs.getInt(7)));
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

    public List<Usuario> getUsuariosByPersona(int persona) {
        ArrayList<Usuario> usuarios = new ArrayList<>();
        try {
            Connection con = ConnectionDB.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM usuario WHERE idPersona = ?");
            ps.setInt(1, persona);
            ResultSet rs = ps.executeQuery();
            PersonaDAO personaDAO = new PersonaDAO();
            RolDAO rolDAO = new RolDAO();
            while (rs.next()) {
                if (rs != null) {
                    Usuario usuario = new Usuario();
                    usuario.setNombreUsuario(rs.getString(1));
                    usuario.setContrasenia("PRIVATE");
                    usuario.setToken(rs.getInt(3));
                    usuario.setCorreo(rs.getString(4));
                    usuario.setTelefono(rs.getLong(5));
                    usuario.setIdPersona(personaDAO.getPersonaById(rs.getInt(6)));
                    usuario.setIdRol(rolDAO.getRolById(rs.getInt(7)));
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
                    usuario.setCorreo(rs.getString(4));
                    usuario.setTelefono(rs.getLong(5));
                    usuario.setIdPersona(personaDAO.getPersonaById(rs.getInt(6)));
                    usuario.setIdRol(rolDAO.getRolById(rs.getInt(7)));
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
            Usuario buscado = getUsuarioByUser(usuario.getNombreUsuario());
            if (buscado.getNombreUsuario() == null) {
                PersonaDAO personaDAO = new PersonaDAO();
                Persona persona = personaDAO.createPersona(usuario.getIdPersona());
                if (persona.getNombre() != null) {
                    PreparedStatement ps = con.prepareStatement("INSERT INTO `usuario`(`nombreUsuario`, `contrasenia`, `token`, `correo`, `telefono`, `idPersona`, `idRol`) VALUES(?,?,?,?,?,?,?)");
                    MessageDigest md = MessageDigest.getInstance("SHA-256");
                    md.update(usuario.getContrasenia().getBytes(Charset.forName("UTF-8")));
                    byte[] hashed = md.digest();
                    String contraseniaEncrypt = byteToHex(hashed);
                    ps.setString(1, usuario.getNombreUsuario());
                    ps.setString(2, contraseniaEncrypt);
                    ps.setInt(3, usuario.getToken());
                    ps.setString(4, usuario.getCorreo());
                    ps.setLong(5, usuario.getTelefono());
                    ps.setInt(6, persona.getIdPersona());
                    ps.setInt(7, 4);

                    boolean created = ps.executeUpdate() == 1;
                    if (created) {
                        con.commit();
                        nuevo = getUsuarioByUser(usuario.getNombreUsuario());
                    }
                    if (ps != null) ps.close();
                }
            }
        } catch (Exception e) {
            con.rollback();
            e.getMessage();
            e.printStackTrace();
        } finally {
            if (con != null) con.close();
        }
        return nuevo;
    }

    public Usuario createUsuarioByRol(Usuario usuario) throws SQLException {
        Usuario nuevo = new Usuario();
        Connection con = null;
        try {
            Usuario buscado = getUsuarioByUser(usuario.getNombreUsuario());
            if (buscado.getNombreUsuario() == null) {
                RolDAO rolDAO = new RolDAO();
                Rol rol = rolDAO.getRolById(usuario.getIdRol().getIdRol());
                con = ConnectionDB.getConnection();
                con.setAutoCommit(false);
                if (rol.getNombreRol() != null) {
                    PersonaDAO personaDAO = new PersonaDAO();
                    Persona persona = personaDAO.createPersona(usuario.getIdPersona());
                    usuario.setIdRol(rol);
                    usuario.setIdPersona(persona);
                    if (persona.getNombre() != null) {
                        PreparedStatement ps = con.prepareStatement("INSERT INTO `usuario`(`nombreUsuario`, `contrasenia`, `token`, `correo`, `telefono`, `idPersona`, `idRol`) VALUES(?,?,?,?,?,?,?)");
                        MessageDigest md = MessageDigest.getInstance("SHA-256");
                        md.update(usuario.getContrasenia().getBytes(Charset.forName("UTF-8")));
                        byte[] hashed = md.digest();
                        String contraseniaEncrypt = byteToHex(hashed);
                        ps.setString(1, usuario.getNombreUsuario());
                        ps.setString(2, contraseniaEncrypt);
                        ps.setInt(3, usuario.getToken());
                        ps.setString(4, usuario.getCorreo());
                        ps.setLong(5, usuario.getTelefono());
                        ps.setInt(6, persona.getIdPersona());
                        ps.setInt(7, rol.getIdRol());

                        boolean created = ps.executeUpdate() == 1;
                        if (created) {
                            con.commit();
                            nuevo = getUsuarioByUser(usuario.getNombreUsuario());
                        }
                        if (ps != null) ps.close();
                    }
                }
            }
        } catch (Exception e) {
            if (con != null) con.rollback();
            e.getMessage();
            e.printStackTrace();
        } finally {
            if (con != null) con.close();
        }
        return nuevo;
    }

    public Usuario updateRolUsuario(Usuario usuario) throws SQLException {
        Usuario actualizado = new Usuario();
        Connection con = null;
        try {
            con = ConnectionDB.getConnection();
            con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement("UPDATE `usuario` SET `idRol`=? WHERE `nombreUsuario` LIKE ?");
            RolDAO rolDAO = new RolDAO();
            Rol rol = rolDAO.getRolById(usuario.getIdRol().getIdRol());
            ps.setInt(1, rol.getIdRol());
            ps.setString(2, usuario.getNombreUsuario());
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

    public Usuario updateUsuario(Usuario usuario) throws SQLException {
        Usuario actualizado = new Usuario();
        Connection con = null;
        try {
            con = ConnectionDB.getConnection();
            con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement("UPDATE `usuario` SET `telefono`=? WHERE `nombreUsuario` LIKE ?");
            ps.setLong(1, usuario.getTelefono());
            ps.setString(2, usuario.getNombreUsuario());

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

    public Usuario updateContrasenia(Usuario usuario) throws SQLException {
        Usuario busqueda, actualizado = new Usuario();
        UsuarioDAO dao = new UsuarioDAO();
        busqueda = dao.getUsuarioByUser(usuario.getNombreUsuario());
        if (usuario.getToken() == busqueda.getToken()) {
            Connection con = null;
            try {
                con = ConnectionDB.getConnection();
                con.setAutoCommit(false);
                PreparedStatement ps = con.prepareStatement("UPDATE `usuario` SET `contrasenia`=? WHERE `nombreUsuario` LIKE ?");
                MessageDigest md = MessageDigest.getInstance("SHA-256");
                md.update(usuario.getContrasenia().getBytes(Charset.forName("UTF-8")));
                byte[] hashed = md.digest();
                String contraseniaEncrypt = byteToHex(hashed);
                ps.setString(1, contraseniaEncrypt);
                ps.setString(2, usuario.getNombreUsuario());
                boolean updated = ps.executeUpdate() == 1;
                if (updated) {
                    con.commit();
                    actualizado = getUsuarioByUser(busqueda.getNombreUsuario());
                }
                if (ps != null) ps.close();
            } catch (Exception e) {
                con.rollback();
                e.getMessage();
                e.printStackTrace();
            } finally {
                if (con != null) con.close();
            }
        }
        return actualizado;
    }

    public Usuario updateContrasenia2(Contrasenia contrasenia) throws SQLException {
        Usuario busqueda, actualizado = new Usuario();
        UsuarioDAO dao = new UsuarioDAO();
        busqueda = dao.getUsuarioByUser(contrasenia.getUsuario().getNombreUsuario());
        if (contrasenia.getUsuario().getToken() == busqueda.getToken()) {
            Connection con = null;
            try {
                Usuario existe = login(contrasenia.getUsuario().getNombreUsuario(), contrasenia.getUsuario().getContrasenia());
                if (existe.getNombreUsuario() != null) {
                    con = ConnectionDB.getConnection();
                    con.setAutoCommit(false);
                    PreparedStatement ps = con.prepareStatement("UPDATE `usuario` SET `contrasenia`=? WHERE `nombreUsuario` LIKE ?");
                    MessageDigest md = MessageDigest.getInstance("SHA-256");
                    md.update(contrasenia.getContraseniaNueva().getBytes(Charset.forName("UTF-8")));
                    byte[] hashed2 = md.digest();
                    String contraseniaNuevaEncrypt = byteToHex(hashed2);
                    ps.setString(1, contraseniaNuevaEncrypt);
                    ps.setString(2, contrasenia.getUsuario().getNombreUsuario());
                    boolean updated = ps.executeUpdate() == 1;
                    if (updated) {
                        con.commit();
                        actualizado = getUsuarioByUser(existe.getNombreUsuario());
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
