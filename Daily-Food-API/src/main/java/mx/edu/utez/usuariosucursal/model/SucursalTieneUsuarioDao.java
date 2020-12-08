package mx.edu.utez.usuariosucursal.model;

import mx.edu.utez.sucursal.model.SucursalDao;
import mx.edu.utez.tools.ConnectionDB;
import mx.edu.utez.usuario.model.UsuarioDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SucursalTieneUsuarioDao {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public List getSucursalTieneUsuario() throws SQLException {
        ArrayList<SucursalTieneUsuario> list = new ArrayList();
        try {
            con = ConnectionDB.getConnection();
            ps = con.prepareStatement("SELECT * FROM sucursaltieneusuario");

            rs = ps.executeQuery();
            SucursalDao sucursalDao = new SucursalDao();
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            while (rs.next()) {
                SucursalTieneUsuario object = new SucursalTieneUsuario();
                object.setIdSucursal(sucursalDao.getSucursalById(rs.getInt(1)));
                object.setNombreUsuario(usuarioDAO.getUsuarioByUser(rs.getString(2)));
                list.add(object);
            }

        } catch (Exception e) {
            System.err.println("ERROR USUARIO SUCURSAL READ " + e.getMessage());
        } finally {
            if (ps != null) ps.close();
            if (rs != null) rs.close();
            if (con != null) con.close();
        }
        return list;
    }

    public SucursalTieneUsuario getSucursalTieneUsuarioByUser(String user) throws SQLException {
        SucursalTieneUsuario object = new SucursalTieneUsuario();

        try {
            con = ConnectionDB.getConnection();
            ps = con.prepareStatement("SELECT * FROM sucursaltieneusuario WHERE `nombreUsuario` LIKE ?");
            ps.setString(1, user);

            rs = ps.executeQuery();

            SucursalDao sucursalDao = new SucursalDao();
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            while (rs.next()) {
                object.setIdSucursal(sucursalDao.getSucursalById(rs.getInt(1)));
                object.setNombreUsuario(usuarioDAO.getUsuarioByUser(rs.getString(2)));
            }

        } catch (Exception e) {

        } finally {
            if (ps != null) ps.close();
            if (rs != null) rs.close();
            if (con != null) con.close();
        }

        return object;
    }

    public boolean createSucursalTieneUsuario(SucursalTieneUsuario object) throws SQLException {
        boolean flag = false;
        try {
            con = ConnectionDB.getConnection();
            con.setAutoCommit(false);
            ps = con.prepareStatement("INSERT INTO sucursaltieneusuario (`idSucursal`,`nombreUsuario`) VALUES (?,?);");
            ps.setInt(1, object.getIdSucursal().getIdSucursal());
            ps.setString(2, object.getNombreUsuario().getNombreUsuario());

            flag = ps.executeUpdate() == 1;

            if (flag) con.commit();

        } catch (Exception e) {
            System.err.println("ERROR USUARIO SUCURSAL CREATE " + e.getMessage());
            con.rollback();
        } finally {
            if (ps != null) ps.close();
            if (rs != null) rs.close();
            if (con != null) con.close();
        }
        return flag;
    }

    public boolean updateSucursalTieneUsuario(SucursalTieneUsuario object) throws SQLException {
        boolean flag = false;

        try {
            con = ConnectionDB.getConnection();
            con.setAutoCommit(false);
            ps = con.prepareStatement("UPDATE sucursaltieneusuario SET `idSucursal` = ? WHERE `nombreUsuario` LIKE ?;");
            ps.setInt(1, object.getIdSucursal().getIdSucursal());
            ps.setString(2, object.getNombreUsuario().getNombreUsuario());

            flag = ps.executeUpdate() == 1;

            if (flag) con.commit();
        } catch (Exception e) {
            System.err.println("ERROR USUARIO SUCURSAL UPDATE "+e.getMessage());
            con.rollback();
        } finally {
            if (ps != null) ps.close();
            if (rs != null) rs.close();
            if (con != null) con.close();
        }
        return flag;
    }

    public boolean deleteSucursalTieneUsuario(String user) throws SQLException{
        boolean flag = false;

        try {
            con = ConnectionDB.getConnection();
            con.setAutoCommit(false);
            ps = con.prepareStatement("DELETE FROM sucursaltieneusuario WHERE `nombreUsuario` LIKE ?;");
            ps.setString(1, user);

            flag = ps.executeUpdate() == 1;

            if (flag) con.commit();
        }  catch (Exception e) {
            System.err.println("ERROR USUARIO SUCURSAL DELETED");
            con.rollback();
        } finally {
            if (ps != null) ps.close();
            if (rs != null) rs.close();
            if (con != null) con.close();
        }

        return flag;
    }
}
