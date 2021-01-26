package mx.edu.utez.tipoPlatillo.model;

import mx.edu.utez.tools.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TipoPlatilloDao {
    Connection con = null;
    ResultSet rs = null;
    PreparedStatement ps = null;

    public List getTipoPlatillo() throws SQLException {
        ArrayList<TipoPlatillo> list = new ArrayList();
        try{
            con = ConnectionDB.getConnection();
            ps = con.prepareStatement("SELECT * FROM tipoplatillo;");
            rs = ps.executeQuery();

            while(rs.next()){
                TipoPlatillo tipoPlatillo = new TipoPlatillo();
                tipoPlatillo.setIdTipoPlatillo(rs.getInt(1));
                tipoPlatillo.setNombreTipo(rs.getString(2));
                list.add(tipoPlatillo);
            }
        }catch(Exception e){
            e.getMessage();
        }finally {
            if(con!=null) con.close();
            if(rs!=null)rs.close();
            if(ps!=null)ps.close();
        }


        return list;
    }

    public TipoPlatillo getTipoPlatilloById(int id) throws SQLException {
        TipoPlatillo tipoPlatillo = new TipoPlatillo();
        try{
            con = ConnectionDB.getConnection();
            ps = con.prepareStatement("SELECT * FROM tipoplatillo WHERE idTipoPlatillo = ?;");
            ps.setInt(1,id);
            rs = ps.executeQuery();

            while(rs.next()){
                tipoPlatillo.setIdTipoPlatillo(rs.getInt(1));
                tipoPlatillo.setNombreTipo(rs.getString(2));
            }

        }catch(Exception e){
            System.err.println(e.getMessage());
        }finally{
            if(con!=null) con.close();
            if(rs!=null)rs.close();
            if(ps!=null)ps.close();
        }

        return tipoPlatillo;
    }

    public TipoPlatillo createTipoPlatillo(TipoPlatillo object) throws SQLException{
        TipoPlatillo platilloCreate = new TipoPlatillo();

        try{
            con = ConnectionDB.getConnection();
            con.setAutoCommit(false);
            ps = con.prepareStatement("INSERT INTO tipoplatillo (nombreTipo) VALUES (?);", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,object.getNombreTipo());

            boolean flag = ps.executeUpdate() == 1;

            if(flag){
                con.commit();
                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int idRecovery = generatedKeys.getInt(1);
                        platilloCreate = object;
                        platilloCreate.setIdTipoPlatillo(idRecovery);
                    } else {
                        throw new SQLException("FAIL NOT CREATED");
                    }
                }
            }
        }catch(Exception e){
            con.rollback();
            System.err.println(e.getMessage());
        }finally{
            if(con!=null) con.close();
            if(rs!=null)rs.close();
            if(ps!=null)ps.close();
        }

        return platilloCreate;
    }

    public boolean updateTipoPlatillo(TipoPlatillo object) throws SQLException{
        boolean flag = false;

        try{
            con = ConnectionDB.getConnection();
            con.setAutoCommit(false);
            ps = con.prepareStatement("UPDATE tipoplatillo SET nombreTipo = ? WHERE idTipoPlatillo = ?;");
            ps.setString(1,object.getNombreTipo());
            ps.setInt(2,object.getIdTipoPlatillo());
            flag = ps.executeUpdate() == 1;

            if(flag){
                con.commit();
            }
        }catch(Exception e){
            con.rollback();
            System.err.println(e.getMessage());
        }finally{
            if(con!=null) con.close();
            if(rs!=null)rs.close();
            if(ps!=null)ps.close();
        }
        return flag;
    }

    public boolean deleteTipoPlatillo(int id) throws SQLException{
        boolean flag = false;

        try{
            con = ConnectionDB.getConnection();
            con.setAutoCommit(false);
            ps = con.prepareStatement("DELETE FROM tipoplatillo WHERE idTipoPlatillo = ?");
            ps.setInt(1 , id);

            flag = ps.executeUpdate() == 1;

            if(flag) {
                con.commit();
            }

        }catch(Exception e){
            con.rollback();
            System.err.println(e.getMessage());
        }finally{
            if(con!=null) con.close();
            if(rs!=null)rs.close();
            if(ps!=null)ps.close();
        }

        return flag;
    }
}
