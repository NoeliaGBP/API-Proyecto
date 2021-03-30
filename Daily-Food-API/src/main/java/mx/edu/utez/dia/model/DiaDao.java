package mx.edu.utez.dia.model;

import mx.edu.utez.tools.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DiaDao {


    public List getDias(){
        ArrayList listDias = new ArrayList();
        try{
            Connection con = ConnectionDB.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM dia");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Dia obj = new Dia();
                obj.setIdDia(rs.getInt(1));
                obj.setNombreDia(rs.getString(2));
                listDias.add(obj);
            }
            rs.close();
            con.close();
        }catch(Exception e){
            System.err.println("LIST" + e.getMessage());
        }
        return listDias;
    }

    public Dia getDiaById(int id){
        Dia obj = new Dia();
        try{
            Connection con = ConnectionDB.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM dia WHERE idDia = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                obj.setIdDia(rs.getInt(1));
                obj.setNombreDia(rs.getString(2));
            }
            rs.close();
            con.close();
            ps.close();
        }catch(Exception e){
            System.err.println("Error de conexión");
        }
        return obj;
    }

    public Dia createDia(Dia diaNuevo) throws SQLException {
        boolean insert = false;
        Dia diaReturn = new Dia();
        Connection con = null;
        try{
            con = ConnectionDB.getConnection();
            con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement("INSERT INTO dia(`nombreDia`) VALUES(?)", Statement.RETURN_GENERATED_KEYS);
            //<editor-folder desc="SETSTRING">
            ps.setString(1, diaNuevo.getNombreDia());
            //</editor-folder>
            insert = (ps.executeUpdate() == 1);
            if(insert){
                try(ResultSet generatedKeys = ps.getGeneratedKeys()){
                    if(generatedKeys.next()){
                        int idRecovery = generatedKeys.getInt(1);
                        diaReturn = diaNuevo;
                        diaReturn.setIdDia(idRecovery);
                    }else{
                        throw new SQLException("FAIL NOT CREATED");
                    }
                }
            }
        }catch(Exception e){
            con.rollback();
            System.err.println("Error "+e.getMessage());
        }finally {
            con.commit();
            con.close();

        }
        return diaReturn;
    }

    public boolean updateDia(Dia dia) throws  SQLException{
        boolean update = false;
        Connection con = null;
        try{
            con = ConnectionDB.getConnection();
            con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement("UPDATE dia SET nombreDia=? WHERE idDia = ?");
            ps.setString(1, dia.getNombreDia());
            ps.setInt(2, dia.getIdDia());
            update = (ps.executeUpdate() == 1);
            if(update){
                con.commit();
            }
            ps.close();
        }catch(Exception e){
            con.rollback();
            System.err.println("Error "+e.getMessage());
        }finally {
            con.close();
        }
        return update;
    }

    public boolean deleteDia(int idDia) throws SQLException{
        boolean delete = false;
        Connection con = null;
        try{
            con = ConnectionDB.getConnection();
            con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement("DELETE FROM dia WHERE idDia = ?");
            ps.setInt(1, idDia);
            delete= (ps.executeUpdate() == 1);
            if(delete){
                con.commit();
            }
            ps.close();
        }catch (Exception e){
            System.err.println("Error "+e.getMessage());
            con.rollback();
        }finally {
            con.close();
        }
        return delete;
    }

}
