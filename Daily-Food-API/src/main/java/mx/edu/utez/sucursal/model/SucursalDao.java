package mx.edu.utez.sucursal.model;


import mx.edu.utez.direccion.model.DireccionDao;
import mx.edu.utez.tools.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SucursalDao {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public List getSucursal() throws SQLException {
        ArrayList<Sucursal> list = new ArrayList();

        try {
            con = ConnectionDB.getConnection();
            ps = con.prepareStatement("SELECT * FROM sucursal;");

            rs = ps.executeQuery();
            DireccionDao direccionDao = new DireccionDao();
            while (rs.next()) {
                Sucursal sucursal = new Sucursal();
                sucursal.setIdSucursal(rs.getInt(1));
                sucursal.setNombreSucursal(rs.getString(2));
                sucursal.setIdDireccion(direccionDao.getDireccionById(rs.getInt(3)));

                list.add(sucursal);
            }

        } catch (Exception e) {

        } finally {
            if (ps != null) ps.close();
            if (rs != null) rs.close();
            if (con != null) con.close();
        }

        return list;
    }

    public Sucursal getSucursalById(int id) throws SQLException {
        Sucursal sucursal = new Sucursal();

        try {
            con = ConnectionDB.getConnection();
            ps = con.prepareStatement("SELECT * FROM sucursal WHERE `idSucursal` = ?;");
            ps.setInt(1, id);

            rs = ps.executeQuery();
            DireccionDao direccionDao = new DireccionDao();
            while (rs.next()) {
                sucursal.setIdSucursal(rs.getInt(1));
                sucursal.setNombreSucursal(rs.getString(2));
                sucursal.setIdDireccion(direccionDao.getDireccionById(rs.getInt(3)));

            }
        } catch (Exception e) {

        } finally {
            if (ps != null) ps.close();
            if (rs != null) rs.close();
            if (con != null) con.close();
        }
        return sucursal;
    }

    public Sucursal createSucursal(Sucursal object) throws SQLException {
        Sucursal sucursalInsert = new Sucursal();
        boolean flag = false;
        try {
            con = ConnectionDB.getConnection();
            con.setAutoCommit(false);
            ps = con.prepareStatement("INSERT INTO sucursal(`nombreSucursal`,`idDireccion`) VALUES (?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, object.getNombreSucursal());
            ps.setInt(2, object.getIdDireccion().getId());

            flag = ps.executeUpdate() == 1;

            if (flag) {
                con.commit();

                try (ResultSet generatedKey = ps.getGeneratedKeys()) {
                    if (generatedKey.next()) {
                        int idRecovery = generatedKey.getInt(1);
                        sucursalInsert = object;
                        sucursalInsert.setIdSucursal(idRecovery);

                    } else {
                        throw new SQLException("ERROR SUCURSAL CREATE ");
                    }
                }
            }

        } catch (Exception e) {
            System.err.println("ERROR SUCURSAL CREATE");
            con.rollback();
        } finally {
            if (ps != null) ps.close();
            if (rs != null) rs.close();
            if (con != null) con.close();
        }

        return sucursalInsert;
    }

    public boolean updateSucursal(Sucursal object) throws SQLException {
        boolean flag = false;

        try {
            con = ConnectionDB.getConnection();
            con.setAutoCommit(false);
            ps = con.prepareStatement("UPDATE `sucursal` SET `nombreSucursal` = ? , `idDireccion` = ? WHERE `idSucursal` = ?;");
            ps.setString(1, object.getNombreSucursal());
            ps.setInt(2, object.getIdDireccion().getId());
            ps.setInt(3, object.getIdSucursal());

            flag = ps.executeUpdate() == 1;

            if (flag) con.commit();

        } catch (Exception e) {
            System.err.println("ERROR SUCURSAL UPDATE " + e.getMessage());
            con.rollback();
        } finally {
            if (ps != null) ps.close();
            if (rs != null) rs.close();
            if (con != null) con.close();
        }

        return flag;
    }

    public boolean deleteSucursal(int id) throws SQLException {
        boolean flag = false;

        try {
            con = ConnectionDB.getConnection();
            con.setAutoCommit(false);
            ps = con.prepareStatement("DELETE FROM sucursal WHERE `idSucursal` = ? ; ");
            ps.setInt(1, id);

            flag = ps.executeUpdate() == 1;

            if (flag) con.commit();

        } catch (Exception e) {
            System.err.println("ERROR SUCURSAL DELETED "+e.getMessage());
            con.rollback();
        } finally {
            if (ps != null) ps.close();
            if (rs != null) rs.close();
            if (con != null) con.close();
        }

        return flag;
    }

}
