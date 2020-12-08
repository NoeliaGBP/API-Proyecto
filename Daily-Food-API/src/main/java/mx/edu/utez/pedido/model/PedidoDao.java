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

    public List  getPedidos() throws SQLException {
        ArrayList<Pedido> list = new ArrayList();

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
                pedido.setCantidadTotal(rs.getDouble(3));
                pedido.setCantidadPago(rs.getDouble(4));
                pedido.setHoraEntrega(rs.getString(5));
                pedido.setStatus(rs.getString(6));
                pedido.setNombreUsuario(usuarioDAO.getUsuarioByUser(rs.getString(7)));
                pedido.setIdDireccion(direccionDao.getDireccionById(rs.getInt(8)));
                pedido.setIdSucursal(sucursalDao.getSucursalById(rs.getInt(9)));
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
                pedido.setCantidadTotal(rs.getDouble(3));
                pedido.setCantidadPago(rs.getDouble(4));
                pedido.setHoraEntrega(rs.getString(5));
                pedido.setStatus(rs.getString(6));
                pedido.setNombreUsuario(usuarioDAO.getUsuarioByUser(rs.getString(7)));
                pedido.setIdDireccion(direccionDao.getDireccionById(rs.getInt(8)));
                pedido.setIdSucursal(sucursalDao.getSucursalById(rs.getInt(9)));

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
            ps = con.prepareStatement("INSERT INTO pedido (fecha, costoTotal, cantidadPago, horaEntrega,status, nombreUsuario, idDireccion, idSucursal) VALUES (?,?,?,?,?,?,?,?);" , Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,pedido.getFecha());
            ps.setDouble(2,pedido.getCantidadTotal());
            ps.setDouble(3,pedido.getCantidadPago());
            ps.setString(4,pedido.getHoraEntrega());
            ps.setString(5,pedido.getStatus());
            ps.setString(6,pedido.getNombreUsuario().getNombreUsuario());
            ps.setInt(7,pedido.getIdDireccion().getId());
            ps.setInt(8,pedido.getIdSucursal().getIdSucursal());

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
            ps = con.prepareStatement("UPDATE pedido SET `fecha` = ? , `costoTotal` = ?,`cantidadPago` = ?,  `horaEntrega` = ?, `status` = ?, `nombreUsuario` = ?,  `idDireccion` = ?, `idSucursal` = ? WHERE  `idPedido` = ?;");
            ps.setString(1,pedido.getFecha());
            ps.setDouble(2,pedido.getCantidadTotal());
            ps.setDouble(3,pedido.getCantidadPago());
            ps.setString(4,pedido.getHoraEntrega());
            ps.setString(5,pedido.getStatus());
            ps.setString(6,pedido.getNombreUsuario().getNombreUsuario());
            ps.setInt(7,pedido.getIdDireccion().getId());
            ps.setInt(8,pedido.getIdSucursal().getIdSucursal());
            ps.setInt(9,pedido.getId());

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
            ps.setString(1,"CANCELADO");
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
