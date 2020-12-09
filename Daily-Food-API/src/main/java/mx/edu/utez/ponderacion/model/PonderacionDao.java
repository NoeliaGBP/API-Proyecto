package mx.edu.utez.ponderacion.model;

import mx.edu.utez.pedido.model.PedidoDao;
import mx.edu.utez.tools.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PonderacionDao {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public List getPonderacion() throws SQLException {
        ArrayList<Ponderacion> list = new ArrayList();

        try {
            con = ConnectionDB.getConnection();
            ps = con.prepareStatement("SELECT * FROM ponderacion;");
            rs = ps.executeQuery();

            PedidoDao pedidoDao = new PedidoDao();
            while (rs.next()) {
                Ponderacion ponderacion = new Ponderacion();
                ponderacion.setId(rs.getInt(1));
                ponderacion.setPonderacion(rs.getInt(2));
                ponderacion.setComentario(rs.getString(3));
                ponderacion.setIdPedido(pedidoDao.getPedidoById(rs.getInt(4)));
                list.add(ponderacion);
            }
        } catch (Exception e) {
            System.err.println("ERROR PONDERACION READ");
        } finally {
            if (ps != null) ps.close();
            if (rs != null) rs.close();
            if (con != null) con.close();
        }

        return list;
    }

    public Ponderacion getPonderacionById(int id) throws SQLException {
        Ponderacion ponderacion = new Ponderacion();

        try {
            con = ConnectionDB.getConnection();
            ps = con.prepareStatement("SELECT * FROM ponderacion WHERE idPonderacion = ?;");
            ps.setInt(1,id);

            rs = ps.executeQuery();

            PedidoDao pedidoDao = new PedidoDao();
            while (rs.next()) {
                ponderacion.setId(rs.getInt(1));
                ponderacion.setPonderacion(rs.getInt(2));
                ponderacion.setComentario(rs.getString(3));
                ponderacion.setIdPedido(pedidoDao.getPedidoById(rs.getInt(4)));
            }

        } catch (Exception e) {
            System.err.println("ERROR PONDERACION READ BY ID");
        } finally {
            if (ps != null) ps.close();
            if (rs != null) rs.close();
            if (con != null) con.close();
        }
        return ponderacion;
    }

    public Ponderacion createPonderacion(Ponderacion ponderacion) throws SQLException {
        Ponderacion ponderacionInsert = new Ponderacion();
        boolean flag = false;
        try {
            con = ConnectionDB.getConnection();
            con.setAutoCommit(false);
            ps = con.prepareStatement("INSERT INTO ponderacion (`ponderacion`,`comentario`,`idPedido`) VALUES (?,?,?);", Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1,ponderacion.getPonderacion());
            ps.setString(2,ponderacion.getComentario());
            ps.setInt(3,ponderacion.getIdPedido().getId());

            flag = ps.executeUpdate() == 1;

            if(flag){
                con.commit();
                try(ResultSet generatedKeys = ps.getGeneratedKeys()){

                    if(generatedKeys.next()){
                        int idRecobery = generatedKeys.getInt(1);
                        ponderacionInsert = ponderacion;
                        ponderacionInsert.setId(idRecobery);

                    }else{
                        throw new  SQLException ("ERROR PONDERACION CREATE");
                    }

                }
            }

        } catch (Exception e) {
            System.err.println("ERROR PONDERACION CREATE "+e.getMessage());
            con.rollback();
        } finally {
            if (ps != null) ps.close();
            if (rs != null) rs.close();
            if (con != null) con.close();
        }

        return ponderacionInsert;
    }

    public boolean updatePonderacion(Ponderacion ponderacion) throws SQLException {
        boolean flag = false;

        try {
            con = ConnectionDB.getConnection();
            con.setAutoCommit(false);
            ps = con.prepareStatement("UPDATE ponderacion SET `ponderacion` = ?,`comentario` = ?, `idPedido`= ? WHERE `idPonderacion` = ?;");

            ps.setInt(1,ponderacion.getPonderacion());
            ps.setString(2,ponderacion.getComentario());
            ps.setInt(3,ponderacion.getIdPedido().getId());
            ps.setInt(4,ponderacion.getId());

            flag = ps.executeUpdate() == 1;

            if(flag) con.commit();

        } catch (Exception e) {
            System.err.println("ERROR PONDERACION UPDATE "+e.getMessage());
            con.rollback();
        } finally {
            if (ps != null) ps.close();
            if (rs != null) rs.close();
            if (con != null) con.close();
        }

        return flag;
    }

    public boolean deletePonderacion(int id) throws SQLException {
        boolean flag = false;

        try {
            con = ConnectionDB.getConnection();
            con.setAutoCommit(false);
            ps = con.prepareStatement("DELETE FROM ponderacion WHERE `idPonderacion` = ?;");
            ps.setInt(1,id);

            flag = ps.executeUpdate() == 1;

            if(flag) con.commit();

        } catch (Exception e) {
            System.err.println("ERROR PONDERACION DELETED "+e.getMessage());
            con.rollback();
        } finally {
            if (ps != null) ps.close();
            if (rs != null) rs.close();
            if (con != null) con.close();
        }

        return flag;
    }

}
