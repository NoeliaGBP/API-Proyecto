package mx.edu.utez.pedidotieneplatillo.model;

import mx.edu.utez.pedido.model.PedidoDao;
import mx.edu.utez.platilloenmenu.model.PlatilloEnMenu;
import mx.edu.utez.platilloenmenu.model.PlatilloEnMenuDao;
import mx.edu.utez.tools.ConnectionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PedidoTienePlatiloDao {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public List readPlatilloByMenu(int id) throws SQLException{
        ArrayList<PedidoTienePlatillo> list = new ArrayList();

        try{
            con = ConnectionDB.getConnection();
            ps = con.prepareStatement("SELECT * FROM pedidotieneplatillo WHERE idPedido = ?");
            ps.setInt(1,id);

            rs = ps.executeQuery();

            PedidoDao pedidoDao = new PedidoDao();
            PlatilloEnMenuDao platilloEnMenuDao = new PlatilloEnMenuDao();

            while(rs.next()){
                PedidoTienePlatillo object = new PedidoTienePlatillo();
                object.setIdPedido(pedidoDao.getPedidoById(rs.getInt(1)));
                object.setIdMenuPlatillo(platilloEnMenuDao.getPlatilloEnMenuById(rs.getInt(2)));
                object.setCantidad(rs.getInt(3));
                object.setComentario(rs.getString(4));

                list.add(object);
            }
        }catch(Exception e){
            con.rollback();
            System.err.println("ERROR PEDIDO TIENE PROMOCION  "+e.getMessage());
        }finally{
            if(rs != null){
                rs.close();
            }

            if( ps != null){
                ps.close();
            }

            if(con != null){
                con.close();
            }

        }

        return list;
    }

    public boolean createPedidoTienePlatillo(PedidoTienePlatillo pedidoTienePlatillo) throws SQLException{
       boolean flag = false;

       try{
            con = ConnectionDB.getConnection();
            con.setAutoCommit(false);
            ps = con.prepareStatement("INSERT INTO pedidotieneplatillo (idPedido,idMenuPlatillo,cantidad,comentario)  VALUES (?,?,?,?)");
            ps.setInt(1 , pedidoTienePlatillo.getIdPedido().getId());
            ps.setInt(2, pedidoTienePlatillo.getIdMenuPlatillo().getIdMenu().getIdMenu());
            ps.setInt(3,pedidoTienePlatillo.getCantidad());
            ps.setString(4,pedidoTienePlatillo.getComentario());

            flag = ps.executeUpdate() == 1 ;

            if(flag){
                con.commit();
            }

       }catch(Exception e){
           con.rollback();
           System.err.println("ERROR PEDIDO TIENE PROMOCION  "+e.getMessage());
       }finally{
           if(rs != null){
               rs.close();
           }

           if( ps != null){
             ps.close();
           }

           if(con != null){
               con.close();
           }

       }

       return flag;
    }



}
