package mx.edu.utez.tipomenu.model;

import mx.edu.utez.tools.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TipoMenuDao {

    public List getTiposMenu(){
        ArrayList<TipoMenu> tiposMenu = new ArrayList();
        try{
            Connection con = ConnectionDB.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM tipomenu");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                TipoMenu tipoMenu = new TipoMenu();
                tipoMenu.setIdTipoMenu(rs.getInt(1));
                tipoMenu.setNombreTipoMenu(rs.getString(2));
                tipoMenu.setModoMenu(rs.getBoolean(3));
                tiposMenu.add(tipoMenu);
            }
            con.close();
            ps.close();
            rs.close();
        }catch (Exception e){
            System.err.println("LIST TipoMenu " + e.getMessage());
        }
        return tiposMenu;
    }

    public TipoMenu getTipoMenuById(int idTipoMenu){
        TipoMenu tipoMenu = new TipoMenu();
        try{
            Connection con = ConnectionDB.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM tipomenu WHERE idTipoMenu = ?");
            ps.setInt(1, idTipoMenu);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                tipoMenu.setIdTipoMenu(rs.getInt(1));
                tipoMenu.setNombreTipoMenu(rs.getString(2));
                tipoMenu.setModoMenu(rs.getBoolean(3));
            }
            con.close();
            ps.close();
            rs.close();
        }catch(Exception e){
            System.err.println("GET TipoMenu " + e.getMessage());
        }
        return tipoMenu;
    }

    public TipoMenu createTipoMenu(TipoMenu tipoMenu) throws SQLException {
        boolean insert = false;
        Connection con = null;
        try{
            con = ConnectionDB.getConnection();
            con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement("INSERT INTO tipomenu (`nombreTMenu`, `modoMenu`)" +
                    "VALUES(?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, tipoMenu.getNombreTipoMenu());
            ps.setBoolean(2, tipoMenu.isModoMenu());
            insert = (ps.executeUpdate() == 1);

            if(insert){
                con.commit();
                try(ResultSet generatedKeys = ps.getGeneratedKeys()){
                    if(generatedKeys.next()){
                        int idRecovery = generatedKeys.getInt(1);
                        tipoMenu.setIdTipoMenu(idRecovery);
                    }else{
                        throw new SQLException("FAIL NOT CREATED");
                    }
                }
            }
            ps.close();
        }catch (Exception e){
            con.rollback();
            System.err.println("INSERT TipoMenu "+ e.getMessage());
        }finally{
            con.close();
        }
        return tipoMenu;
    }

    public boolean updateTipoMenu(TipoMenu newTipoMenu) throws SQLException{
        boolean updated = false;
        Connection con = null;
        try{
            con = ConnectionDB.getConnection();
            con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement("UPDATE tipomenu SET " +
                    "nombreTMenu = ?, modoMenu = ? WHERE idTipoMenu = ?");
            ps.setString(1, newTipoMenu.getNombreTipoMenu());
            ps.setBoolean(2, newTipoMenu.isModoMenu());
            ps.setInt(3, newTipoMenu.getIdTipoMenu());

            updated = (ps.executeUpdate() == 1);

            if(updated){
                con.commit();
            }
            ps.close();
        }catch (Exception e){
            con.rollback();
            System.err.println("UPDATE TipoMenu " + e.getMessage());
        }finally {
            con.close();
        }
        return updated;
    }

    public boolean deleteTipoMenu(int idTipoMenu) throws SQLException{
        boolean deleted = false;
        Connection con = null;
        try{
            con = ConnectionDB.getConnection();
            con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement("DELETE FROM tipomenu WHERE idTipoMenu = ?");
            ps.setInt(1, idTipoMenu);
            deleted = (ps.executeUpdate() == 1);
            if(deleted){
                con.commit();
            }
            ps.close();
        }catch (Exception e){
            con.rollback();
            System.err.println("DELETE TipoMenu " + e.getMessage());
        }finally {
            con.close();
        }
        return deleted;
    }

}
