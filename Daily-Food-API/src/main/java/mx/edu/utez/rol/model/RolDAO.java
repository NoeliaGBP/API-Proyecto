package mx.edu.utez.rol.model;

import mx.edu.utez.tools.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RolDAO {
    public List<Rol> getRoles() {
        ArrayList<Rol> roles = new ArrayList<>();
        try {
            Connection con = ConnectionDB.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM rol");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs != null) {
                    Rol rol = new Rol();
                    rol.setIdRol(rs.getInt(1));
                    rol.setNombreRol(rs.getString(2));
                    roles.add(rol);
                }
            }
            if (con!=null) con.close();
            if (ps!=null) ps.close();
            if (rs!=null) rs.close();
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
        return roles;
    }

    public Rol getRolById(int id) {
        Rol rol = new Rol();
        try {
            Connection con = ConnectionDB.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM rol WHERE idRol = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs != null) {
                    rol.setIdRol(rs.getInt(1));
                    rol.setNombreRol(rs.getString(2));
                }
            }
            if (con!=null) con.close();
            if (ps!=null) ps.close();
            if (rs!=null) rs.close();
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
        return rol;
    }

    public Rol createRol(Rol rol) throws SQLException {
        Rol nuevo = new Rol();
        Connection con = null;
        try {
            con = ConnectionDB.getConnection();
            con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement("INSERT INTO `rol`(`nombreRol`) VALUES(?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, rol.getNombreRol());

            boolean created = ps.executeUpdate() == 1;
            if (created) {
                con.commit();
                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int idRecovery = generatedKeys.getInt(1);
                        nuevo = getRolById(idRecovery);
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

    public Rol updateRol(Rol rol) throws SQLException {
        Rol actualizado = new Rol();
        Connection con = null;
        try {
            con = ConnectionDB.getConnection();
            con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement("UPDATE `rol` SET `nombreRol`=? WHERE `idRol`=?");
            ps.setString(1, rol.getNombreRol());
            ps.setInt(2, rol.getIdRol());
            boolean updated = ps.executeUpdate() == 1;
            if (updated) {
                con.commit();
                actualizado = getRolById(rol.getIdRol());
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

    public boolean deleteRol(int id) throws SQLException {
        boolean deleted = false;
        Connection con = null;
        try {
            con = ConnectionDB.getConnection();
            con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement("DELETE FROM `rol` WHERE idRol=?");
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