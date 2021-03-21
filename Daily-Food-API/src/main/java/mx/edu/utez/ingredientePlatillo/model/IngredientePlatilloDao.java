package mx.edu.utez.ingredientePlatillo.model;

import mx.edu.utez.ingrediente.model.Ingrediente;
import mx.edu.utez.ingrediente.model.IngredienteDao;
import mx.edu.utez.platillo.model.Platillo;
import mx.edu.utez.platillo.model.PlatilloDao;
import mx.edu.utez.tools.ConnectionDB;
import mx.edu.utez.unidadMedida.model.UnidadMedidaDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IngredientePlatilloDao {
    Connection con = null;
    ResultSet rs = null;
    PreparedStatement ps = null;

    public List getIngredientePlatillo() throws SQLException {
        ArrayList<IngredientePlatillo> list = new ArrayList();
        try{
            con = ConnectionDB.getConnection();
            ps = con.prepareStatement("SELECT * FROM ingredienteenplatillo;");
            rs = ps.executeQuery();
            IngredienteDao daoIngrediente = new IngredienteDao();
            PlatilloDao daoPlatillo = new PlatilloDao();
            UnidadMedidaDao daoUnidadMedida = new UnidadMedidaDao();
            while(rs.next()){
                IngredientePlatillo ingredientePlatillo = new IngredientePlatillo();
                ingredientePlatillo.setIdIngrediente(daoIngrediente.getIngredienteById(rs.getInt(1)));
                ingredientePlatillo.setIdPlatillo(daoPlatillo.getPlatilloById(rs.getInt(2)));
                ingredientePlatillo.setPorcion(rs.getDouble(3));
                ingredientePlatillo.setIdUnidadMedida(daoUnidadMedida.getUnidadMedidaById(rs.getInt(4)));
                list.add(ingredientePlatillo);
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

    public List getIngredientesByPlatillo(int id) throws SQLException {
        ArrayList<IngredientePlatillo> list = new ArrayList();
        try{
            con = ConnectionDB.getConnection();
            ps = con.prepareStatement("SELECT * FROM ingredienteenplatillo WHERE idPlatillo = ?;");
            ps.setInt(1,id);

            rs = ps.executeQuery();
            IngredienteDao daoIngrediente = new IngredienteDao();
            PlatilloDao daoPlatillo = new PlatilloDao();
            UnidadMedidaDao daoUnidadMedida = new UnidadMedidaDao();
            while(rs.next()){
                IngredientePlatillo ingredientePlatillo = new IngredientePlatillo();
                ingredientePlatillo.setIdIngrediente(daoIngrediente.getIngredienteById(rs.getInt(1)));
                //ingredientePlatillo.setIdPlatillo(daoPlatillo.getPlatilloById(rs.getInt(2)));
                ingredientePlatillo.setPorcion(rs.getDouble(3));
                ingredientePlatillo.setIdUnidadMedida(daoUnidadMedida.getUnidadMedidaById(rs.getInt(4)));
                list.add(ingredientePlatillo);
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


    public boolean createIngredientePlatillo(IngredientePlatillo object) throws SQLException{
        boolean flag = false;

        try{
            con = ConnectionDB.getConnection();
            con.setAutoCommit(false);
            ps = con.prepareStatement("INSERT INTO ingredienteenplatillo (idIngrediente,idPlatillo,porcion,idUnidadMedida) VALUES (?,?,?,?);");
            ps.setInt(1, object.getIdIngrediente().getIdIngrediente());
            ps.setInt(2,object.getIdPlatillo().getIdPlatillo());
            ps.setDouble(3,object.getPorcion());
            ps.setInt(4,object.getIdUnidadMedida().getIdUnidadMedida());

            flag = ps.executeUpdate() == 1;

            if(flag){
                con.commit();
            }

        }catch(Exception e){
            con.rollback();
            e.getMessage();
        }finally {
            if(con!=null) con.close();
            if(rs!=null)rs.close();
            if(ps!=null)ps.close();
        }

        return flag;
    }

    public boolean updateIngredientePlatillo(IngredientePlatillo object) throws SQLException{
        boolean flag = false;

        try{
            con = ConnectionDB.getConnection();
            con.setAutoCommit(false);
            ps = con.prepareStatement("UPDATE ingredienteenplatillo SET porcion = ? , idUnidadMedida = ? WHERE idIngrediente = ? AND idPlatillo = ?;");
            ps.setDouble(1,object.getPorcion());
            ps.setInt(2,object.getIdUnidadMedida().getIdUnidadMedida());
            ps.setInt(3 ,object.getIdIngrediente().getIdIngrediente());
            ps.setInt(4,object.getIdPlatillo().getIdPlatillo());

            flag = ps.executeUpdate() == 1;

            if(flag){
                con.commit();
            }

        }catch(Exception e){
            con.rollback();
            e.getMessage();
        }finally {
            if(con!=null) con.close();
            if(rs!=null)rs.close();
            if(ps!=null)ps.close();
        }

        return flag;
    }

    public boolean deleteIngredientePlatillo (IngredientePlatillo object) throws SQLException{
        boolean flag = false;

        try{
            con = ConnectionDB.getConnection();
            con.setAutoCommit(false);
            ps = con.prepareStatement("DELETE FROM ingredienteenplatillo WHERE idIngrediente = ? AND idPlatillo = ?;");
            ps.setInt(1,object.getIdIngrediente().getIdIngrediente());
            ps.setInt(2,object.getIdPlatillo().getIdPlatillo());

            flag = ps.executeUpdate() == 1;

            if(flag){
                con.commit();
            }

        }catch(Exception e){
            con.rollback();
            e.getMessage();
        }finally {
            if(con!=null) con.close();
            if(rs!=null)rs.close();
            if(ps!=null)ps.close();
        }

        return flag;
    }

}
