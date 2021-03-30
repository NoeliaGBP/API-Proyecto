package mx.edu.utez.persona.model;

import mx.edu.utez.tools.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonaDAO {
    public List<Persona> getPersonas() {
        ArrayList<Persona> personas = new ArrayList<>();
        try {
            Connection con = ConnectionDB.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM persona");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs != null) {
                    Persona persona = new Persona();
                    persona.setIdPersona(rs.getInt(1));
                    persona.setNombre(rs.getString(2));
                    persona.setPrimerApellido(rs.getString(3));
                    persona.setSegundoApellido(rs.getString(4));
                    personas.add(persona);
                }
            }
            if (con!=null) con.close();
            if (ps!=null) ps.close();
            if (rs!=null) rs.close();
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
        return personas;
    }

    public Persona getPersonaById(int id) {
        Persona persona = new Persona();
        try {
            Connection con = ConnectionDB.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM persona WHERE idPersona = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs != null) {
                    persona.setIdPersona(rs.getInt(1));
                    persona.setNombre(rs.getString(2));
                    persona.setPrimerApellido(rs.getString(3));
                    persona.setSegundoApellido(rs.getString(4));
                }
            }
            if (con!=null) con.close();
            if (ps!=null) ps.close();
            if (rs!=null) rs.close();
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
        return persona;
    }

    public Persona createPersona(Persona persona) throws SQLException {
        Persona nuevo = new Persona();
        Connection con = null;
        try {
            con = ConnectionDB.getConnection();
            con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement("INSERT INTO `persona`(`nombre`, `primerApellido`, `segundoApellido`) VALUES(?,?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, persona.getNombre());
            ps.setString(2, persona.getPrimerApellido());
            ps.setString(3, persona.getSegundoApellido());

            boolean created = ps.executeUpdate() == 1;
            if (created) {
                con.commit();
                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int idRecovery = generatedKeys.getInt(1);
                        nuevo = getPersonaById(idRecovery);
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

    public Persona updatePersona(Persona persona) throws SQLException {
        Persona actualizado = new Persona();
        Connection con = null;
        try {
            con = ConnectionDB.getConnection();
            con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement("UPDATE `persona` SET `nombre`=?,`primerApellido`=?,`segundoApellido`=? WHERE `idPersona`=?");
            ps.setString(1, persona.getNombre());
            ps.setString(2, persona.getPrimerApellido());
            ps.setString(3, persona.getSegundoApellido());
            ps.setInt(4, persona.getIdPersona());
            boolean updated = ps.executeUpdate() == 1;
            if (updated) {
                con.commit();
                actualizado = getPersonaById(persona.getIdPersona());
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

    public boolean deletePersona(int id) throws SQLException {
        boolean deleted = false;
        Connection con = null;
        try {
            con = ConnectionDB.getConnection();
            con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement("DELETE FROM `persona` WHERE idPersona=?");
            ps.setInt(1, id);

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
}