package mx.edu.utez.preparacion.model;

import mx.edu.utez.platillo.model.PlatilloDao;
import mx.edu.utez.tools.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PreparacionDao {
    Connection con;
    ResultSet rs;
    PreparedStatement ps;

    public List readPreparacion() throws SQLException {
        ArrayList<Preparacion> list = new ArrayList();

        try{
            con = ConnectionDB.getConnection();
            ps = con.prepareStatement("SELECT * FROM  preparacion;");

            rs = ps.executeQuery();

            PlatilloDao platilloDao = new PlatilloDao();
            while(rs.next()){
                Preparacion object = new Preparacion();
                object.setIdPreparacion(rs.getInt(1));
                object.setDescripcion(rs.getString(2));
                object.setIdPlatillo(platilloDao.getPlatilloById(rs.getInt(3)));
                list.add(object);
            }

        }catch(Exception e){

        }finally {
            if(ps != null){
                ps.close();
            }
            if(rs != null){
                rs.close();
            }
            if(con != null){
                con.close();
            }
        }

        return list;
    }

    public Preparacion readPreparacionById(int id) throws SQLException {
        Preparacion object = new Preparacion();

        try{
            con = ConnectionDB.getConnection();
            ps = con.prepareStatement("SELECT * FROM  preparacion WHERE idPreparacion = ?;");
            ps.setInt(1,id);

            rs = ps.executeQuery();

            PlatilloDao platilloDao = new PlatilloDao();
            while(rs.next()){
                object.setIdPreparacion(rs.getInt(1));
                object.setDescripcion(rs.getString(2));
                object.setIdPlatillo(platilloDao.getPlatilloById(rs.getInt(3)));
            }

        }catch(Exception e){
            System.err.println("ERROR " + e.getMessage());
        }finally {
            if(ps != null){
                ps.close();
            }
            if(rs != null){
                rs.close();
            }
            if(con != null){
                con.close();
            }
        }

        return object;
    }

    public List readPreparacionByPlatillo(int id) throws SQLException {
        ArrayList<Preparacion> list = new ArrayList();

        try{
            con = ConnectionDB.getConnection();
            ps = con.prepareStatement("SELECT * FROM  preparacion WHERE idPlatillo = ?;");
            ps.setInt(1, id);

            rs = ps.executeQuery();

            PlatilloDao platilloDao = new PlatilloDao();
            while(rs.next()){
                Preparacion object = new Preparacion();
                object.setIdPreparacion(rs.getInt(1));
                object.setDescripcion(rs.getString(2));
                object.setIdPlatillo(platilloDao.getPlatilloById(rs.getInt(3)));
                list.add(object);
            }

        }catch(Exception e){
            System.err.println("ERROR "+e.getMessage() );
        }finally {
            if(ps != null){
                ps.close();
            }
            if(rs != null){
                rs.close();
            }
            if(con != null){
                con.close();
            }
        }

        return list;
    }



    public Preparacion createPreparacion(Preparacion object) throws SQLException {
        Preparacion preparacionCreated = new Preparacion();
        boolean flag = false;
        try{
            con = ConnectionDB.getConnection();
            con.setAutoCommit(false);
            ps = con.prepareStatement("INSERT INTO preparacion (descripcion,idPlatillo) VALUES (?,?);", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,object.getDescripcion());
            ps.setInt(2,object.getIdPlatillo().getIdPlatillo());

            flag = ps.executeUpdate() == 1 ;

            if(flag){
                con.commit();

                try(ResultSet generatedKey = ps.getGeneratedKeys()){
                    if(generatedKey.next()){
                        int idGenerated = generatedKey.getInt(1);
                        preparacionCreated = object;
                        preparacionCreated.setIdPreparacion(idGenerated);
                    }else{
                        throw new SQLException("ERROR PREPARACION CREATE");
                    }
                }
            }

        }catch(Exception e){
            con.rollback();
            System.err.println("Error "+e.getMessage());
        }finally {
            if(ps != null){
                ps.close();
            }
            if(rs != null){
                rs.close();
            }
            if(con != null){
                con.close();
            }
        }

        return preparacionCreated;
    }

    public boolean updatePreparacion(Preparacion object) throws SQLException {
        boolean flag = false;
        try{
            con = ConnectionDB.getConnection();
            con.setAutoCommit(false);
            ps = con.prepareStatement("UPDATE preparacion SET descripcion = ? WHERE idPlatillo = ?;");
            ps.setString(1,object.getDescripcion());
            ps.setInt(2,object.getIdPlatillo().getIdPlatillo());

            flag = ps.executeUpdate() == 1 ;

            if(flag){
                con.commit();
            }

        }catch(Exception e){
            con.rollback();
            System.err.println("Error "+e.getMessage());
        }finally {
            if(ps != null){
                ps.close();
            }
            if(rs != null){
                rs.close();
            }
            if(con != null){
                con.close();
            }
        }

        return flag;
    }

    public boolean deletePreparacion(int id) throws SQLException{
        boolean flag = false;

        try{
            con = ConnectionDB.getConnection();
            con.setAutoCommit(false);
            ps = con.prepareStatement("DELETE FROM preparacion WHERE idPlatillo = ?");
            ps.setInt(1,id);

            flag = ps.executeUpdate() == 1;

            if(flag){
                con.commit();
            }

        }catch(Exception e){
            con.rollback();
            System.err.println("Error "+e.getMessage());
        }finally{
            if(ps != null){
                ps.close();
            }
            if(rs != null){
                rs.close();
            }
            if(con != null){
                con.close();
            }
        }

        return flag;
    }
}
