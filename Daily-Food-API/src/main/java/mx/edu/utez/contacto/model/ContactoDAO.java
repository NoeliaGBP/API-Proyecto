package mx.edu.utez.contacto.model;

import mx.edu.utez.persona.model.PersonaDAO;
import mx.edu.utez.tipocontacto.model.TipoContactoDAO;
import mx.edu.utez.tools.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;

public class ContactoDAO {
   public ArrayList<Contacto> getContactos() {
        ArrayList<Contacto> contactos = new ArrayList<>();
        try {
            Connection con = ConnectionDB.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM contacto");
            ResultSet rs = ps.executeQuery();
            PersonaDAO personaDAO = new PersonaDAO();
            TipoContactoDAO tipoContactoDAO = new TipoContactoDAO();
            while (rs.next()) {
                if (rs != null) {
                    Contacto contacto = new Contacto();
                    contacto.setIdContacto(rs.getInt(1));
                    contacto.setIdPersona(personaDAO.getPersonaById(rs.getInt(2)));
                    contacto.setIdTipoContacto(tipoContactoDAO.getTipoContactoById(rs.getInt(3)));
                    contacto.setContacto(rs.getString(4));
                    contactos.add(contacto);
                }
            }
            if (con!=null) con.close();
            if (ps!=null) ps.close();
            if (rs!=null) rs.close();
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
        return contactos;
    }

    public Contacto getContactoByCode(int code) {
        Contacto contacto = new Contacto();
        try {
            Connection con = ConnectionDB.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM contacto WHERE idContacto = ?");
            ps.setInt(1, code);
            ResultSet rs = ps.executeQuery();

            PersonaDAO personaDAO = new PersonaDAO();
            TipoContactoDAO tipoContactoDAO = new TipoContactoDAO();
            while (rs.next()) {
                if (rs != null) {
                    contacto.setIdContacto(rs.getInt(1));
                    contacto.setIdPersona(personaDAO.getPersonaById(rs.getInt(2)));
                    contacto.setIdTipoContacto(tipoContactoDAO.getTipoContactoById(rs.getInt(3)));
                    contacto.setContacto(rs.getString(4));
                }
            }
            if (con != null) con.close();
            if (ps != null) ps.close();
            if (rs != null) rs.close();
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
        return contacto;
    }

    public ArrayList<Contacto> getContactoByPersona(int id) {
        ArrayList<Contacto> contactos = new ArrayList<>();
        try {
            Connection con = ConnectionDB.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM contacto WHERE idPersona = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            PersonaDAO personaDAO = new PersonaDAO();
            TipoContactoDAO tipoContactoDAO = new TipoContactoDAO();
            while (rs.next()) {
                if (rs != null) {
                    Contacto contacto = new Contacto();
                    contacto.setIdContacto(rs.getInt(1));
                    contacto.setIdPersona(personaDAO.getPersonaById(rs.getInt(2)));
                    contacto.setIdTipoContacto(tipoContactoDAO.getTipoContactoById(rs.getInt(3)));
                    contacto.setContacto(rs.getString(4));
                    contactos.add(contacto);
                }
            }
            if (con!=null) con.close();
            if (ps!=null) ps.close();
            if (rs!=null) rs.close();
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
        return contactos;
    }

    public Contacto createContacto(Contacto contacto) throws SQLException {
        Contacto nuevo = new Contacto();
        Connection con = null;
        try {
            con = ConnectionDB.getConnection();
            con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement("INSERT INTO `contacto` (`idPersona`, `idTipoContacto`, `contacto`) VALUES(?,?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, contacto.getIdPersona().getIdPersona());
            ps.setInt(2, contacto.getIdTipoContacto().getIdTipoContacto());
            ps.setString(3, contacto.getContacto());

            boolean created = ps.executeUpdate() == 1;
            if (created) {
                con.commit();
                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int idRecovery = generatedKeys.getInt(1);
                        nuevo = getContactoByCode(idRecovery);
                    } else {
                        throw new SQLException("FAIL NOT CREATED");
                    }
                }
            }
            if (ps != null) ps.close();
        }  catch (Exception e) {
            con.rollback();
            e.getMessage();
            e.printStackTrace();
        } finally {
            if (con != null)  con.close();
        }
        return nuevo;
    }

    public Contacto updateContacto(Contacto contacto) throws SQLException {
        Contacto actualizado = new Contacto();
        Connection con = null;
        try {
            con = ConnectionDB.getConnection();
            con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement("UPDATE `contacto` SET `idPersona`=?,`idTipoContacto`=?,`contacto`=? WHERE `idContacto`=?");
            ps.setInt(1, contacto.getIdPersona().getIdPersona());
            ps.setInt(2, contacto.getIdTipoContacto().getIdTipoContacto());
            ps.setString(3, contacto.getContacto());
            ps.setInt(4, contacto.getIdContacto());
            boolean updated = ps.executeUpdate() == 1;
            if (updated) {
                con.commit();
                actualizado = getContactoByCode(contacto.getIdContacto());
            }
            if (ps != null) ps.close();
        }  catch (Exception e) {
            con.rollback();
            e.getMessage();
            e.printStackTrace();
        } finally {
            if (con != null)  con.close();
        }
        return actualizado;
    }

    public boolean deleteContacto(int code) throws SQLException {
        boolean deleted = false;
        Connection con = null;
        try {
            con = ConnectionDB.getConnection();
            con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement("DELETE FROM `contacto` WHERE idContacto=?");
            ps.setInt(1, code);

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
            if (con != null)  con.close();
        }
        return deleted;
    }
}
