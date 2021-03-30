package mx.edu.utez.promocion.model;

import mx.edu.utez.tools.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PromocionDao {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public List readAllPromocion() throws SQLException {
        ArrayList<Promocion> list = new ArrayList();

        try{
            con = ConnectionDB.getConnection();
            ps = con.prepareStatement("SELECT * FROM promocion;");

            rs = ps.executeQuery();

            while(rs.next()){
                Promocion promocion = new Promocion();
                promocion.setIdPromocion(rs.getInt(1));
                promocion.setDescripcion(rs.getString(2));
                promocion.setFechaInicio(rs.getString(3));
                promocion.setFechaFin(rs.getString(4));
                promocion.setStatus(rs.getBoolean(5));
                promocion.setPrecio(rs.getDouble((6)));

                list.add(promocion);
            }

        }catch (Exception e){
            System.err.println("Error "+e.getMessage());
        }finally {
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

    public Promocion readPromocionById(int id) throws SQLException {
        Promocion promocion = new Promocion();

        try{
            con = ConnectionDB.getConnection();
            ps = con.prepareStatement("SELECT * FROM promocion WHERE idPromocion = ?;");
            ps.setInt(1,id);

            rs = ps.executeQuery();

            while(rs.next()){
                promocion.setIdPromocion(rs.getInt(1));
                promocion.setDescripcion(rs.getString(2));
                promocion.setFechaInicio(rs.getString(3));
                promocion.setFechaFin(rs.getString(4));
                promocion.setStatus(rs.getBoolean(5));
                promocion.setPrecio(rs.getDouble((6)));
            }

        }catch (Exception e){
            System.err.println("Error "+e.getMessage());
        }finally {
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

        return promocion;
    }

    public Promocion createPromocion(Promocion promocion) throws SQLException{
        Promocion promocionCreated = new Promocion();
        boolean flag = false;

        try{
            con = ConnectionDB.getConnection();
            con.setAutoCommit(false);

            ps = con.prepareStatement("INSERT INTO promocion(descripcion,fechaInicio,fechaFin,status,precio) VALUES (?,?,?,?,?);", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,promocion.getDescripcion());
            ps.setString(2,promocion.getFechaInicio());
            ps.setString(3,promocion.getFechaFin());
            ps.setBoolean(4,promocion.isStatus());
            ps.setDouble(5,promocion.getPrecio());

            flag = ps.executeUpdate() == 1;

            if(flag){
                con.commit();

                try(ResultSet generatedKey = ps.getGeneratedKeys()){
                    if(generatedKey.next()){
                        int idGenerated = generatedKey.getInt(1);
                        promocionCreated = promocion;
                        promocionCreated.setIdPromocion(idGenerated);

                    }else{
                        throw new SQLException("ERROR CREATED PROMOCION ");
                    }
                }

            }


        }catch(Exception e){
            con.rollback();
            System.err.println("ERROR "+e.getMessage());
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

        return promocionCreated;
    }

    public boolean updatePromocion(Promocion promocion) throws SQLException{
        boolean flag = false;

        try{
            con = ConnectionDB.getConnection();
            con.setAutoCommit(false);
            ps = con.prepareStatement("UPDATE promocion SET descripcion = ? ,fechaInicio = ? ,fechaFin = ? ,status = ? ,precio = ? WHERE idPromocion = ?;");
            ps.setString(1,promocion.getDescripcion());
            ps.setString(2,promocion.getFechaInicio());
            ps.setString(3,promocion.getFechaFin());
            ps.setBoolean(4,promocion.isStatus());
            ps.setDouble(5,promocion.getPrecio());
            ps.setInt(6,promocion.getIdPromocion());

            flag = ps.executeUpdate() == 1;

            if(flag){
                con.commit();
            }

        }catch(Exception e){
            con.rollback();
            System.err.println("ERROR UPDATE PROMOCION "+e.getMessage());
        }finally {
            if(rs != null){
                rs.close();
            }
            ps.close();
            if( ps != null){
            }
            if(con != null){
                con.close();
            }
        }

        return flag;
    }

    public boolean deletePromocion(int id ) throws SQLException{
        boolean flag = false;

        try{

            con = ConnectionDB.getConnection();
            con.setAutoCommit(false);
            ps = con.prepareStatement("DELETE FROM promocion WHERE idPromocion = ?;");
            ps.setInt(1,id);

            flag = ps.executeUpdate() == 1;

            if(flag){
                con.commit();
            }

        }catch(Exception e){
            con.rollback();
            System.err.println("ERROR DELETED PROMOCION" + e.getMessage());
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
