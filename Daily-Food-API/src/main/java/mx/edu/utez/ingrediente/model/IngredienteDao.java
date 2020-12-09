package mx.edu.utez.ingrediente.model;

import mx.edu.utez.areaingrediente.model.AreaIngredienteDao;
import mx.edu.utez.tools.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IngredienteDao {
    Connection con;
    ResultSet rs;
    PreparedStatement ps;

    public List getIngrediente() throws SQLException{
        ArrayList<Ingrediente> list = new ArrayList();

        try{
            con = ConnectionDB.getConnection();
            ps = con.prepareStatement("SELECT * FROM ingrediente;");
            rs = ps.executeQuery();

            while(rs.next()){
                Ingrediente ingrediente = new Ingrediente();
                AreaIngredienteDao areaIngredienteDao = new AreaIngredienteDao();
                ingrediente.setIdIngrediente(rs.getInt(1));
                ingrediente.setNombreIngrediente(rs.getString(2));
                ingrediente.setIdAreaIngrediente(areaIngredienteDao.getAreaIngredienteById(rs.getInt(3)));
                list.add(ingrediente);
            }
            if(con!=null) con.close();
            if(rs!=null)rs.close();
            if(ps!=null)ps.close();
        }catch(Exception e){
            System.err.println(e.getMessage());
        }finally{
            if(con!=null) con.close();
            if(rs!=null)rs.close();
            if(ps!=null)ps.close();
        }

        return list;
    }

    public Ingrediente getIngredienteById(int id) throws SQLException{
        Ingrediente ingrediente = new Ingrediente();

        try{
            con = ConnectionDB.getConnection();
            ps = con.prepareStatement("SELECT * FROM ingrediente WHERE id_Ingrediente = ?;");
            ps.setInt(1,id);
            rs = ps.executeQuery();

            while(rs.next()){
                AreaIngredienteDao areaIngredienteDao = new AreaIngredienteDao();
                ingrediente.setIdIngrediente(rs.getInt(1));
                ingrediente.setNombreIngrediente(rs.getString(2));
                ingrediente.setIdAreaIngrediente(areaIngredienteDao.getAreaIngredienteById(rs.getInt(3)));
            }
            if(con!=null) con.close();
            if(rs!=null)rs.close();
            if(ps!=null)ps.close();
        }catch(Exception e){
            System.err.println(e.getMessage());
        }finally{
            if(con!=null) con.close();
            if(rs!=null)rs.close();
            if(ps!=null)ps.close();
        }

        return ingrediente;
    }

    public Ingrediente createIngrediente(Ingrediente ingrediente) throws SQLException{
        boolean flag = false;
        Ingrediente ingredienteInsert = new Ingrediente();
        con = null;
        try{
            con = ConnectionDB.getConnection();
            con.setAutoCommit(false);
            ps = con.prepareStatement("INSERT INTO ingrediente (`nombreIngrediente`,`idAreaIngrediente`) VALUES (?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,ingrediente.getNombreIngrediente());
            ps.setInt(2,ingrediente.getIdAreaIngrediente().getId());

            flag = (ps.executeUpdate() == 1);

            if(flag){
                con.commit();

                try(ResultSet generateKeys = ps.getGeneratedKeys()){
                    if(generateKeys.next()){
                        int idRecovery = generateKeys.getInt(1);
                        ingredienteInsert = ingrediente;
                        ingredienteInsert.setIdIngrediente(idRecovery);
                    }else{
                        throw new SQLException("FAIL INGREDIENTE NOT CREATED");
                    }
                }
            }
        }catch(Exception e){
            System.err.println("ERROR created" + e.getMessage());
            con.rollback();
        }finally{
            if(con!=null) con.close();
            if(rs!=null)rs.close();
            if(ps!=null)ps.close();
        }

        return ingredienteInsert;
    }

    public boolean updateIngrediente(Ingrediente ingrediente) throws SQLException{
        boolean flag = false;
        con = null;

        try{
            con = ConnectionDB.getConnection();
            con.setAutoCommit(false);
            ps = con.prepareStatement("UPDATE ingrediente SET `nombreIngrediente` = ?,`idAreaIngrediente` = ? WHERE id_Ingrediente = ?;");
            ps.setString(1,ingrediente.getNombreIngrediente());
            ps.setInt(2,ingrediente.getIdAreaIngrediente().getId());
            ps.setInt(3,ingrediente.getIdIngrediente());

            flag = (ps.executeUpdate() == 1);
            con.commit();
        }catch(Exception e){
            System.err.println("ERROR update" + e.getMessage());
            con.rollback();
        }finally{
            if(con!=null) con.close();
            if(rs!=null)rs.close();
            if(ps!=null)ps.close();
        }

        return flag;
    }

    public boolean deleteIngrediente(int id) throws  SQLException {
        boolean flag = false;

        try{
            con = ConnectionDB.getConnection();
            con.setAutoCommit(false);
            ps  = con.prepareStatement("DELETE FROM ingrediente WHERE id_Ingrediente = ?");
            ps.setInt(1,id);
            flag = ps.executeUpdate() == 1;
            con.commit();
        }catch(Exception e){
            System.err.println("ERROR delete" + e.getMessage());
            con.rollback();
        }finally{
            if(con!=null) con.close();
            if(rs!=null)rs.close();
            if(ps!=null)ps.close();
        }

        return flag;
    }



}
