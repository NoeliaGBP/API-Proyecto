package mx.edu.utez.pedidotieneplatillo.model;

import mx.edu.utez.tools.ConnectionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PedidoTienePlatiloDao {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;


    public boolean createPedidoTienePlatillo(PedidoTienePlatillo pedidoTienePlatillo) throws SQLException{
       boolean flag = false;

       try{
            con = ConnectionDB.getConnection();
            con.setAutoCommit(false);
            ps = con.prepareStatement("INSERT INTO pedidotieneplatillo (idPedido,idMenuPlatillo,cantidad,comentario)  VALUES (?,?,?,?)");
            ps.setInt(1 , pedidoTienePlatillo.getIdPedido().getId() );
            
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
