package mx.edu.utez.precio.model;

import mx.edu.utez.platillo.model.PlatilloDao;
import mx.edu.utez.tools.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PrecioDao {
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public List readPrecios() throws SQLException {
        ArrayList<Precio> list = new ArrayList();

        try{
            con = ConnectionDB.getConnection();
            ps = con.prepareStatement("SELECT * FROM precio;");

            rs = ps.executeQuery();

            PlatilloDao precioDao = new PlatilloDao();
            while(rs.next()){
                Precio precio = new Precio();
                precio.setIdPrecio(rs.getInt(1));
                precio.setPrecio(rs.getDouble(2));
                precio.setIdPlatillo(precioDao.getPlatilloById(rs.getInt(3)));
                list.add(precio);
            }

        }catch(Exception e){
            System.err.println("Error"+e.getMessage());
        }finally{
            if(rs != null){
                rs.close();
            }

            if(ps != null){
                ps.close();
            }
            if(con != null){
                con.close();
            }

        }

        return list;
    }

    public Precio createPrecio(Precio precio) throws SQLException{
        Precio precioCreated = new Precio();
        boolean flag = false;
        try{
            con = ConnectionDB.getConnection();
            con.setAutoCommit(false);

            ps = con.prepareStatement("INSERT INTO precio(precio,idPlatillo) values (?,?);", Statement.RETURN_GENERATED_KEYS);
            ps.setDouble(1 , precio.getPrecio());
            ps.setInt(2,precio.getIdPlatillo().getIdPlatillo());

            flag = ps.executeUpdate() == 1;

            if(flag){
                con.commit();
                try(ResultSet generatedKeys = ps.getGeneratedKeys()){

                    if(generatedKeys.next()){
                        int idGenerated = generatedKeys.getInt(1);
                        precioCreated = precio;
                        precio.setIdPrecio(idGenerated);
                    }else{
                        throw new SQLException("ERROR PRECIO CREATED");
                    }

                }
            }


        }catch(Exception e){
            con.rollback();
            System.err.println("ERROR" + e.getMessage());
        }finally{
            if(rs != null){
                rs.close();
            }

            if(ps != null){
                ps.close();
            }
            if(con != null){
                con.close();
            }
        }

        return precioCreated;
    }

    public boolean updatePrecio(Precio precio) throws SQLException {
        boolean flag = false;

        try{
            con = ConnectionDB.getConnection();
            con.setAutoCommit(false);
            ps = con.prepareStatement("UPDATE precio SET precio = ? , idPlatillo = ?  WHERE idPrecio = ?;");
            ps.setDouble(1,precio.getPrecio());
            ps.setInt(2,precio.getIdPlatillo().getIdPlatillo());
            ps.setInt(3,precio.getIdPrecio());

            flag = ps.executeUpdate() == 1;

            if(flag){
                con.commit();
            }

        }catch(Exception e){
            con.rollback();
            System.err.println("EXECUTE " + e.getMessage());
        }finally{
            if(rs != null){
                rs.close();
            }

            if(ps != null){
                ps.close();
            }
            if(con != null){
                con.close();
            }
        }

        return flag;
    }

    public boolean deletePrecio(int id) throws SQLException {
        boolean flag = false;

        try{
            con = ConnectionDB.getConnection();
            con.setAutoCommit(false);
            ps = con.prepareStatement("DELETE FROM precio WHERE idPrecio = ?;");
            ps.setInt(1,id);

            flag = ps.executeUpdate() == 1 ;

            if(flag){
                con.commit();
            }

        }catch(Exception e){
            con.rollback();
            System.err.println("ERROR" + e.getMessage());

        }finally{
            if(rs != null){
                rs.close();
            }
            if(ps != null){
                ps.close();
            }
            if(con != null){
                con.close();
            }
        }

        return flag;
    }

}
