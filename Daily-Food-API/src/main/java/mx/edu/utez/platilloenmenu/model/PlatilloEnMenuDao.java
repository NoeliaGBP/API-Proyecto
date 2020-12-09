package mx.edu.utez.platilloenmenu.model;

import mx.edu.utez.menu.model.MenuDao;
import mx.edu.utez.tools.ConnectionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlatilloEnMenuDao {

    public List getPlatillosEnMenu(){
        ArrayList<PlatilloEnMenu> platillosEnMenu = new ArrayList();
        try{
            Connection con = ConnectionDB.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT  * FROM platilloenmenu");
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                PlatilloEnMenu platilloEnMenu = new PlatilloEnMenu();
                MenuDao menu = new MenuDao();
                PlatilloDao platillo = new PlatilloDao();
                platilloEnMenu.setIdPlatilloMenu(rs.getInt(1));
                platilloEnMenu.setCantidadEstimada(rs.getInt(2));
                platilloEnMenu.setStatus(rs.getBoolean(3));
                platilloEnMenu.setIdMenu(menu.getMenuById(rs.getInt(4)));
                platilloEnMenu.setIdPlatillo(platillo.getPlatilloById(rs.getInt(5)));
                getPlatillosEnMenu().add(platilloEnMenu);
            }
            con.close();
            ps.close();
            rs.close();
        }catch(Exception e){
            System.err.println("LIST "+e.getMessage());
        }
        return platillosEnMenu;
    }

    public PlatilloEnMenu getPlatilloEnMenuById(int idPlatilloEnMenu){
        PlatilloEnMenu platilloMenu = new PlatilloEnMenu();
        try{
            Connection con = ConnectionDB.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM platilloenmenu WHERE idMenuPlatillo = ?");
            ps.setInt(1, idPlatilloEnMenu);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                MenuDao menu = new MenuDao();
                PlatilloDao platillo = new PlatilloDao();
                platilloMenu.setIdPlatilloMenu(rs.getInt(1));
                platilloMenu.setCantidadEstimada(rs.getInt(2));
                platilloMenu.setStatus(rs.getBoolean(3));
                platilloMenu.setIdMenu(menu.getMenuById(rs.getInt(4)));
                platilloMenu.setIdPlatillo(platillo.getPlatilloById(rs.getInt(5)));
            }
            rs.close();
            ps.close();
            con.close();
        }catch(Exception e){
            System.err.println("ERROR " +e.getMessage());
        }
        return platilloMenu;
    }

    public PlatilloEnMenu createPlatilloEnMenu(PlatilloEnMenu platilloInsert) throws SQLException {
        boolean insert = false;
        Connection con = null;
        try{
            con = ConnectionDB.getConnection();
            con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement("INSERT INTO platilloenmenu (`cantidadEstimada`, `status`, " +
                    "`idMenu`, `idPlatillo`) VALUES (?,?,?,?)");
            ps.setInt(1, platilloInsert.getCantidadEstimada());
            ps.setBoolean(2, platilloInsert.isStatus());
            ps.setInt(3, platilloInsert.getIdMenu().getIdMenu());
            ps.setInt(4, platilloInsert.getIdPlatillo().getIdPlatillo());

            insert = (ps.executeUpdate() == 1);

            if(insert){
                con.commit();
                try(ResultSet generatedKeys = ps.getGeneratedKeys()){
                    if(generatedKeys.next()){
                        int idRecovery = generatedKeys.getInt(1);
                        platilloInsert.setIdPlatilloMenu(idRecovery);
                    }else{
                        throw new SQLException("FAIL NOT CREATED");
                    }
                }
            }
            ps.close();
        }catch (Exception e){
            con.rollback();
            System.err.println("Create " + e.getMessage());
        }finally {
            con.close();
        }
        return platilloInsert;
    }

    public boolean updatePlatilloEnMenu(PlatilloEnMenu newPlatilloEnMenu) throws SQLException {
        boolean updated = false;
        Connection con = null;
        try{
            con = ConnectionDB.getConnection();
            con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement("UPDATE platilloenmenu SET cantidadEstimada = ?," +
                    " status = ?, idMenu = ?, idPlatillo = ? WHERE idMenuPlatillo = ?");
            ps.setInt(1, newPlatilloEnMenu.getCantidadEstimada());
            ps.setBoolean(2, newPlatilloEnMenu.isStatus());
            ps.setInt(3, newPlatilloEnMenu.getIdMenu().getIdMenu());
            ps.setInt(4, newPlatilloEnMenu.getIdPlatillo().getIdPlatillo());
            ps.setInt(5, newPlatilloEnMenu.getIdPlatilloMenu());
            updated = (ps.executeUpdate() == 1);

            if(updated){
                con.commit();
            }
            ps.close();
        }catch (Exception e){
            con.rollback();
            System.err.println("UPDATE PlatilloEnMenu" + e.getMessage());
        }finally{
            con.close();
        }
        return updated;
    }

    public boolean deletePlatilloEnMenu(int idPlatilloMenu) throws SQLException{
        boolean deleted = false;
        Connection con = null;
        try{
            con = ConnectionDB.getConnection();
            con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement("DELETE FROM platilloenmenu WHERE idMenuPlatillo = ?");
            ps.setInt(1, idPlatilloMenu);
            deleted = (ps.executeUpdate() == 1);

            if(deleted){
                con.commit();
            }
            ps.close();
        }catch (Exception e){
            con.rollback();
            System.err.println("DELETE platilloEnMenu " + e.getMessage());
        }finally {
            con.close();
        }
        return deleted;
    }

}
