package mx.edu.utez.unidadMedida.model;

import mx.edu.utez.tools.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UnidadMedidaDao {
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public List getUnidadMedida() throws SQLException {
        ArrayList<UnidadMedida> list = new ArrayList();

        try{
            con = ConnectionDB.getConnection();
            ps = con.prepareStatement("SELECT * FROM unidadmedida;");
            rs = ps.executeQuery();

            while(rs.next()){
                UnidadMedida unidadMedida = new UnidadMedida();
                unidadMedida.setIdUnidadMedida(rs.getInt(1));
                unidadMedida.setTipoUnidad(rs.getString(2));
                unidadMedida.setAbreviatura(rs.getString(3));

                list.add(unidadMedida);
            }

        }catch(Exception e){
            System.err.println("ERROR READ UNIDAD DE MEDIDA "+ e.getMessage());
        }finally{
            if(rs!=null)rs.close();
            if(ps!=null)ps.close();
            if(con!=null) con.close();
        }

        return list;
    }

    public UnidadMedida getUnidadMedidaById(int id) throws SQLException {
        UnidadMedida unidadMedida = new UnidadMedida();

        try{
            con = ConnectionDB.getConnection();
            ps = con.prepareStatement("SELECT * FROM unidadmedida WHERE idUnidadMedida = ?;");
            ps.setInt(1,id);
            rs = ps.executeQuery();

            while(rs.next()){
                unidadMedida.setIdUnidadMedida(rs.getInt(1));
                unidadMedida.setTipoUnidad(rs.getString(2));
                unidadMedida.setAbreviatura(rs.getString(3));

            }

        }catch(Exception e){
            System.err.println("ERROR READ UNIDAD DE MEDIDA "+ e.getMessage());
        }finally{
            if(rs!=null)rs.close();
            if(ps!=null)ps.close();
            if(con!=null) con.close();
        }

        return unidadMedida;
    }

    public UnidadMedida createdUnidadMedida(UnidadMedida unidad) throws SQLException{
        UnidadMedida unidadCreated = new UnidadMedida();
        boolean flag = false;
        try{
            con = ConnectionDB.getConnection();
            con.setAutoCommit(false);
            ps = con.prepareStatement("INSERT INTO unidadmedida(tipoUnidad,abreviatura) VALUES(?,?);", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,unidad.getTipoUnidad());
            ps.setString(2,unidad.getAbreviatura());

            flag = ps.executeUpdate() == 1;

            if(flag){
                con.commit();
                try(ResultSet generatedKey = ps.getGeneratedKeys()){

                    if(generatedKey.next()){
                        int idRecobery = generatedKey.getInt(1);
                        unidadCreated = unidad;
                        unidad.setIdUnidadMedida(idRecobery);
                    }else{
                        throw new SQLException("ERROR CREATED UNIDAD MEDIDA");
                    }
                }
            }


        }catch(Exception e){
            con.rollback();
            System.err.println("Error" + e.getMessage());
        }finally{
            if(rs!=null)rs.close();
            if(ps!=null)ps.close();
            if(con!=null) con.close();
        }

        return unidadCreated;
    }

    public boolean updateUnidadMedida(UnidadMedida unidad) throws SQLException{
        boolean flag = false;

        try{
            con = ConnectionDB.getConnection();
            con.setAutoCommit(false);
            ps = con.prepareStatement("UPDATE unidadmedida SET tipoUnidad = ? , abreviatura = ? WHERE idUnidadMedida = ?;");
            ps.setString(1,unidad.getTipoUnidad());
            ps.setString(2,unidad.getAbreviatura());
            ps.setInt(3,unidad.getIdUnidadMedida());

            flag = ps.executeUpdate() == 1;

            if(flag){
                con.commit();
            }

        }catch(Exception e ){
            con.rollback();
            System.err.println("ERROR UPDATE UNIDAD MEDIDA" + e.getMessage());
        }finally{
            if(rs!=null)rs.close();
            if(ps!=null)ps.close();
            if(con!=null) con.close();
        }

        return flag;
    }

    public boolean deleteUnidadMedida(int id) throws SQLException {
        boolean flag = false;

        try{
            con = ConnectionDB.getConnection();
            con.setAutoCommit(false);
            ps = con.prepareStatement("DELETE FROM unidadmedida WHERE idUnidadMedida = ?");
            ps.setInt(1,id);

            flag = ps.executeUpdate() == 1;

            if(flag){
                con.commit();
            }

        }catch(Exception e) {
            con.rollback();
            System.err.println("ERROR DELETE UNIDAD MEDIDA "+e.getMessage());
        }finally{
            if(rs!=null)rs.close();
            if(ps!=null)ps.close();
            if(con!=null) con.close();
        }

        return flag;
    }

}
