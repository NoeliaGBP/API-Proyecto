package mx.edu.utez.pedido.model;

import mx.edu.utez.direccion.model.DireccionDao;
import mx.edu.utez.sucursal.model.SucursalDao;
import mx.edu.utez.tools.ConnectionDB;
import mx.edu.utez.usuario.model.Usuario;
import mx.edu.utez.usuario.model.UsuarioDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PedidoDao {
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    /*public List  getPedidos() throws SQLException {
        ArrayList<PedidoCompleto> list = new ArrayList();
        try{
            con = ConnectionDB.getConnection();
            ps = con.prepareStatement("SELECT * FROM pedido;");
            rs = ps.executeQuery();
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            DireccionDao direccionDao = new DireccionDao();
            SucursalDao sucursalDao = new SucursalDao();
            while(rs.next()){
                Pedido pedido = new Pedido();
                pedido.setId(rs.getInt(1));
                pedido.setFecha(rs.getString(2));
                pedido.setCostoTotal(rs.getDouble(3));
                pedido.setCantidadPago(rs.getDouble(4));
                pedido.setStatus(rs.getString(5));
                pedido.setNombreUsuario(usuarioDAO.getUsuarioByUser(rs.getString(6)));
                pedido.setIdDireccion(direccionDao.getDireccionById(rs.getInt(7)));
                pedido.setIdSucursal(sucursalDao.getSucursalById(rs.getInt(8)));
                list.add(pedido);
            }
        }catch(Exception e){
            System.err.println("ERROR READ PEDIDO");
        }finally{
            if (ps != null) ps.close();
            if (rs != null) rs.close();
            if (con != null) con.close();
        }
        return list;
    }*/

    public List getAllPedidosPreparacion() throws SQLException{
        ArrayList<Pedido> pedidosP = new ArrayList();
        try{
            con = ConnectionDB.getConnection();
            ps = con.prepareStatement("SELECT * FROM pedido WHERE status LIKE 'Preparación'");
            rs = ps.executeQuery();
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            DireccionDao direccionDao = new DireccionDao();
            SucursalDao sucursalDao = new SucursalDao();
            while(rs.next()){
                Pedido ped = new Pedido();
                ped.setId(rs.getInt(1));
                ped.setFecha(rs.getString(2));
                ped.setCostoTotal(rs.getDouble(3));
                ped.setCantidadPago(rs.getDouble(4));
                ped.setStatus(rs.getString(5));
                ped.setNombreUsuario(usuarioDAO.getUsuarioByUser(rs.getString(6)));
                ped.setIdDireccion(direccionDao.getDireccionById(rs.getInt(7)));
                ped.setIdSucursal(sucursalDao.getSucursalById(rs.getInt(8)));
                pedidosP.add(ped);
            }
        }catch (Exception e) {
            System.err.println("ERROR => " + e.getMessage());
        }finally {
            if (ps != null) ps.close();
            if (rs != null) rs.close();
            if (con != null) con.close();
        }
        return pedidosP;
    }

    public List getPedidosPreparacionByUser(String user) throws SQLException{
        ArrayList<Pedido> pedidosP = new ArrayList();
        try{
            con = ConnectionDB.getConnection();
            ps = con.prepareStatement("SELECT * FROM pedido WHERE status LIKE 'Preparación' AND nombreUsuario LIKE ?");
            ps.setString(1, user);
            rs = ps.executeQuery();
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            DireccionDao direccionDao = new DireccionDao();
            SucursalDao sucursalDao = new SucursalDao();
            while(rs.next()){
                Pedido ped = new Pedido();
                ped.setId(rs.getInt(1));
                ped.setFecha(rs.getString(2));
                ped.setCostoTotal(rs.getDouble(3));
                ped.setCantidadPago(rs.getDouble(4));
                ped.setStatus(rs.getString(5));
                ped.setNombreUsuario(usuarioDAO.getUsuarioByUser(rs.getString(6)));
                ped.setIdDireccion(direccionDao.getDireccionById(rs.getInt(7)));
                ped.setIdSucursal(sucursalDao.getSucursalById(rs.getInt(8)));
                pedidosP.add(ped);
            }
        }catch (Exception e) {
            System.err.println("ERROR => " + e.getMessage());
        }finally {
            if (ps != null) ps.close();
            if (rs != null) rs.close();
            if (con != null) con.close();
        }
        return pedidosP;
    }

    public List getPedidosEnCurso() throws SQLException{
        ArrayList<Pedido> pedidosC = new ArrayList();
        try{
            con = ConnectionDB.getConnection();
            ps = con.prepareStatement("SELECT * FROM pedido WHERE status LIKE 'En curso'");
            rs = ps.executeQuery();
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            DireccionDao direccionDao = new DireccionDao();
            SucursalDao sucursalDao = new SucursalDao();
            while(rs.next()){
                Pedido ped = new Pedido();
                ped.setId(rs.getInt(1));
                ped.setFecha(rs.getString(2));
                ped.setCostoTotal(rs.getDouble(3));
                ped.setCantidadPago(rs.getDouble(4));
                ped.setStatus(rs.getString(5));
                ped.setNombreUsuario(usuarioDAO.getUsuarioByUser(rs.getString(6)));
                ped.setIdDireccion(direccionDao.getDireccionById(rs.getInt(7)));
                ped.setIdSucursal(sucursalDao.getSucursalById(rs.getInt(8)));
                pedidosC.add(ped);
            }
        }catch (Exception e) {
            System.err.println("ERROR => " + e.getMessage());
        }finally {
            if (ps != null) ps.close();
            if (rs != null) rs.close();
            if (con != null) con.close();
        }
        return pedidosC;
    }

    public List getPedidosEnCursoByUser(String user) throws SQLException{
        ArrayList<Pedido> pedidosC = new ArrayList();
        try{
            con = ConnectionDB.getConnection();
            ps = con.prepareStatement("SELECT * FROM pedido WHERE status LIKE 'En curso' AND nombreUsuario LIKE ?");
            ps.setString(1, user);
            rs = ps.executeQuery();
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            DireccionDao direccionDao = new DireccionDao();
            SucursalDao sucursalDao = new SucursalDao();
            while(rs.next()){
                Pedido ped = new Pedido();
                ped.setId(rs.getInt(1));
                ped.setFecha(rs.getString(2));
                ped.setCostoTotal(rs.getDouble(3));
                ped.setCantidadPago(rs.getDouble(4));
                ped.setStatus(rs.getString(5));
                ped.setNombreUsuario(usuarioDAO.getUsuarioByUser(rs.getString(6)));
                ped.setIdDireccion(direccionDao.getDireccionById(rs.getInt(7)));
                ped.setIdSucursal(sucursalDao.getSucursalById(rs.getInt(8)));
                pedidosC.add(ped);
            }
        }catch (Exception e) {
            System.err.println("ERROR => " + e.getMessage());
        }finally {
            if (ps != null) ps.close();
            if (rs != null) rs.close();
            if (con != null) con.close();
        }
        return pedidosC;
    }

    public List getPedidosEntregados() throws SQLException{
        ArrayList<Pedido> pedidosE = new ArrayList();
        try{
            con = ConnectionDB.getConnection();
            ps = con.prepareStatement("SELECT * FROM pedido WHERE status LIKE 'Entregado'");
            rs = ps.executeQuery();
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            DireccionDao direccionDao = new DireccionDao();
            SucursalDao sucursalDao = new SucursalDao();
            while(rs.next()){
                Pedido ped = new Pedido();
                ped.setId(rs.getInt(1));
                ped.setFecha(rs.getString(2));
                ped.setCostoTotal(rs.getDouble(3));
                ped.setCantidadPago(rs.getDouble(4));
                ped.setStatus(rs.getString(5));
                ped.setNombreUsuario(usuarioDAO.getUsuarioByUser(rs.getString(6)));
                ped.setIdDireccion(direccionDao.getDireccionById(rs.getInt(7)));
                ped.setIdSucursal(sucursalDao.getSucursalById(rs.getInt(8)));
                pedidosE.add(ped);
            }
        }catch (Exception e) {
            System.err.println("ERROR => " + e.getMessage());
        }finally {
            if (ps != null) ps.close();
            if (rs != null) rs.close();
            if (con != null) con.close();
        }
        return pedidosE;
    }

    public List getPedidosEntregadosByUser(String user) throws SQLException{
        ArrayList<Pedido> pedidosE = new ArrayList();
        try{
            con = ConnectionDB.getConnection();
            ps = con.prepareStatement("SELECT * FROM pedido WHERE status LIKE 'Entregado' AND nombreUsuario LIKE ?");
            ps.setString(1, user);
            rs = ps.executeQuery();
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            DireccionDao direccionDao = new DireccionDao();
            SucursalDao sucursalDao = new SucursalDao();
            while(rs.next()){
                Pedido ped = new Pedido();
                ped.setId(rs.getInt(1));
                ped.setFecha(rs.getString(2));
                ped.setCostoTotal(rs.getDouble(3));
                ped.setCantidadPago(rs.getDouble(4));
                ped.setStatus(rs.getString(5));
                ped.setNombreUsuario(usuarioDAO.getUsuarioByUser(rs.getString(6)));
                ped.setIdDireccion(direccionDao.getDireccionById(rs.getInt(7)));
                ped.setIdSucursal(sucursalDao.getSucursalById(rs.getInt(8)));
                pedidosE.add(ped);
            }
        }catch (Exception e) {
            System.err.println("ERROR => " + e.getMessage());
        }finally {
            if (ps != null) ps.close();
            if (rs != null) rs.close();
            if (con != null) con.close();
        }
        return pedidosE;
    }

    public List getPedidosFinalizadosByUser(String user) throws SQLException{
        ArrayList<Pedido> pedidosF = new ArrayList();
        try{
            con = ConnectionDB.getConnection();
            ps = con.prepareStatement("SELECT * FROM pedido WHERE status LIKE 'Finalizado' AND nombreUsuario LIKE ?");
            ps.setString(1, user);
            rs = ps.executeQuery();
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            DireccionDao direccionDao = new DireccionDao();
            SucursalDao sucursalDao = new SucursalDao();
            while(rs.next()){
                Pedido ped = new Pedido();
                ped.setId(rs.getInt(1));
                ped.setFecha(rs.getString(2));
                ped.setCostoTotal(rs.getDouble(3));
                ped.setCantidadPago(rs.getDouble(4));
                ped.setStatus(rs.getString(5));
                ped.setNombreUsuario(usuarioDAO.getUsuarioByUser(rs.getString(6)));
                ped.setIdDireccion(direccionDao.getDireccionById(rs.getInt(7)));
                ped.setIdSucursal(sucursalDao.getSucursalById(rs.getInt(8)));
                pedidosF.add(ped);
            }
        }catch (Exception e) {
            System.err.println("ERROR => " + e.getMessage());
        }finally {
            if (ps != null) ps.close();
            if (rs != null) rs.close();
            if (con != null) con.close();
        }
        return pedidosF;
    }

    public Pedido getPedidoById(int id) throws SQLException {
        Pedido pedido = new Pedido();
        try{
            con = ConnectionDB.getConnection();
            ps = con.prepareStatement("SELECT * FROM pedido WHERE `idPedido` = ?;");
            ps.setInt(1,id);
            rs = ps.executeQuery();

            UsuarioDAO usuarioDAO = new UsuarioDAO();
            DireccionDao direccionDao = new DireccionDao();
            SucursalDao sucursalDao = new SucursalDao();

            while(rs.next()){
                pedido.setId(rs.getInt(1));
                pedido.setFecha(rs.getString(2));
                pedido.setCostoTotal(rs.getDouble(3));
                pedido.setCantidadPago(rs.getDouble(4));
                pedido.setStatus(rs.getString(5));
                pedido.setNombreUsuario(usuarioDAO.getUsuarioByUser(rs.getString(6)));
                pedido.setIdDireccion(direccionDao.getDireccionById(rs.getInt(7)));
                pedido.setIdSucursal(sucursalDao.getSucursalById(rs.getInt(8)));

            }

        }catch(Exception e){
            System.err.println("ERROR READ PEDIDO");
        }finally{
            if (ps != null) ps.close();
            if (rs != null) rs.close();
            if (con != null) con.close();
        }

        return pedido;
    }

    public Pedido createPedido(Pedido pedido) throws SQLException {
        Pedido pedidoInsert = new Pedido();
        boolean flag = false;

        try{
            con = ConnectionDB.getConnection();
            con.setAutoCommit(false);
            ps = con.prepareStatement("INSERT INTO pedido (fecha, costoTotal, cantidadPago, status, nombreUsuario, idDireccion, idSucursal) VALUES (?,?,?,?,?,?,?);" , Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,pedido.getFecha());
            ps.setDouble(2,pedido.getCostoTotal());
            ps.setDouble(3,pedido.getCantidadPago());
            ps.setString(4,pedido.getStatus());
            ps.setString(5,pedido.getNombreUsuario().getNombreUsuario());
            ps.setInt(6,pedido.getIdDireccion().getId());
            ps.setInt(7,pedido.getIdSucursal().getIdSucursal());

            flag = ps.executeUpdate() == 1;

            if(flag){
                con.commit();

                try(ResultSet generatedKeys = ps.getGeneratedKeys()){
                    if(generatedKeys.next()){
                        int idRecobery = generatedKeys.getInt(1);
                        pedidoInsert = pedido;
                        pedidoInsert.setId(idRecobery);
                    }else{
                        throw new SQLException("ERROR CREATE PEDIDO");
                    }
                }
            }

        }catch(Exception e){
            System.err.println("ERROR CREATE PEDIDO" + e.getMessage());
            con.rollback();
        }finally{
            if (ps != null) ps.close();
            if (rs != null) rs.close();
            if (con != null) con.close();
        }

        return pedidoInsert;
    }

    public boolean updatePedido(Pedido pedido) throws SQLException{
        boolean flag = false;

        try{
            con = ConnectionDB.getConnection();
            con.setAutoCommit(false);
            ps = con.prepareStatement("UPDATE pedido SET `fecha` = ? , `costoTotal` = ?,`cantidadPago` = ?, `status` = ?, `nombreUsuario` = ?,  `idDireccion` = ?, `idSucursal` = ? WHERE  `idPedido` = ?;");
            ps.setString(1,pedido.getFecha());
            ps.setDouble(2,pedido.getCostoTotal());
            ps.setDouble(3,pedido.getCantidadPago());
            ps.setString(4,pedido.getStatus());
            ps.setString(5,pedido.getNombreUsuario().getNombreUsuario());
            ps.setInt(6,pedido.getIdDireccion().getId());
            ps.setInt(7,pedido.getIdSucursal().getIdSucursal());
            ps.setInt(8,pedido.getId());

            flag = ps.executeUpdate() == 1;

            if(flag) con.commit();

        }catch(Exception e){
            System.err.println("ERROR UPDATE PEDIDO" + e.getMessage());
            con.rollback();
        }finally{
            if (ps != null) ps.close();
            if (rs != null) rs.close();
            if (con != null) con.close();
        }
        return flag;
    }

    public boolean cancelarPedido(int id) throws SQLException {
        boolean flag = false;

        try{
            con = ConnectionDB.getConnection();
            con.setAutoCommit(false);
            ps = con.prepareStatement("UPDATE pedido SET `status` = ? WHERE  `idPedido` = ?;");
            ps.setString(1,"Cancelado");
            ps.setInt(2,id);
            flag = ps.executeUpdate() == 1;

            if(flag) con.commit();

        }catch(Exception e){
            System.err.println("ERROR CANCELAR PEDIDO"+ e.getMessage());
            con.rollback();
        }finally{
            if (ps != null) ps.close();
            if (rs != null) rs.close();
            if (con != null) con.close();
        }
        return flag;
    }

}
