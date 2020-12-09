package mx.edu.utez.direccion.model;

import mx.edu.utez.tools.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DireccionDao {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public List getDireccion() throws SQLException {
        ArrayList<Direccion> list = new ArrayList();

        try {
            con = ConnectionDB.getConnection();
            ps = con.prepareStatement("SELECT * FROM direccion;");

            rs = ps.executeQuery();

            while (rs.next()) {
                Direccion direccion = new Direccion();
                direccion.setId(rs.getInt(1));
                direccion.setLatitud(rs.getDouble(2));
                direccion.setLongitud(rs.getDouble(3));
                direccion.setAltitud(rs.getDouble(4));

                list.add(direccion);
            }
        } catch (Exception e) {
            System.err.println("Error Dirección read" + e.getMessage());
        } finally {
            if (ps != null) ps.close();
            if (rs != null) rs.close();
            if (con != null) con.close();
        }

        return list;
    }

    public Direccion getDireccionById(int id) throws SQLException {
        Direccion direccion = new Direccion();

        try {

            con = ConnectionDB.getConnection();
            ps = con.prepareStatement("SELECT * FROM direccion WHERE idDireccion = ?;");
            ps.setInt(1, id);

            rs = ps.executeQuery();

            while (rs.next()) {
                direccion.setId(rs.getInt(1));
                direccion.setLatitud(rs.getDouble(2));
                direccion.setLongitud(rs.getDouble(3));
                direccion.setAltitud(rs.getDouble(4));
            }
        } catch (Exception e) {
            System.err.println("Error Direccion By Id " + e.getMessage());
        } finally {
            if (ps != null) ps.close();
            if (rs != null) rs.close();
            if (con != null) con.close();
        }
        return direccion;
    }

    public Direccion createDireccion(Direccion object) throws SQLException {
        Direccion direccionInsert = new Direccion();
        boolean condition = false;
        try {
            con = ConnectionDB.getConnection();
            con.setAutoCommit(false);
            ps = con.prepareStatement("INSERT INTO `direccion` (`latitud`,`longitud`,`altitud`) VALUES (?,?,?);", Statement.RETURN_GENERATED_KEYS);
            ps.setDouble(1, object.getLatitud());
            ps.setDouble(2, object.getLongitud());
            ps.setDouble(3, object.getAltitud());

            condition = ps.executeUpdate() == 1;

            if (condition) {
                con.commit();

                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int idRecobery = generatedKeys.getInt(1);
                        direccionInsert = object;
                        direccionInsert.setId(idRecobery);
                    } else {
                        throw new SQLException("FAIL Area Ingrediente NOT CREATED");
                    }
                }

            }

        } catch (Exception e) {
            System.err.println("Error Direccion not created " + e.getMessage());
            con.rollback();
        } finally {
            if (ps != null) ps.close();
            if (rs != null) rs.close();
            if (con != null) con.close();
        }

        return direccionInsert;
    }

    public boolean updateDireccion(Direccion object) throws SQLException {
        boolean flag = false;

        try {
            con = ConnectionDB.getConnection();
            con.setAutoCommit(false);

            ps = con.prepareStatement("UPDATE direccion SET `latitud` = ? , `longitud` = ? , `altitud` = ? WHERE `idDireccion` = ?;");
            ps.setDouble(1, object.getLatitud());
            ps.setDouble(2, object.getLongitud());
            ps.setDouble(3, object.getAltitud());
            ps.setInt(4, object.getId());

            flag = ps.executeUpdate() == 1;

            if (flag) con.commit();

        } catch (Exception e) {
            System.err.println("Error Dirección Update " + e.getMessage());
            con.rollback();

        } finally {
            if (ps != null) ps.close();
            if (rs != null) rs.close();
            if (con != null) con.close();
        }

        return flag;
    }

    public boolean deleteDireccion(int id) throws SQLException {
        boolean flag = false;

        try {
            con = ConnectionDB.getConnection();
            con.setAutoCommit(false);

            ps = con.prepareStatement("DELETE FROM direccion WHERE idDireccion  = ? ");
            ps.setInt(1, id);

            flag = ps.executeUpdate() == 1;

            if (flag) con.commit();

        } catch (Exception e) {
            System.err.println("Error Dirección Delete " + e.getMessage());
            con.rollback();
        } finally {
            if (ps != null) ps.close();
            if (rs != null) rs.close();
            if (con != null) con.close();
        }

        return flag;
    }
}
