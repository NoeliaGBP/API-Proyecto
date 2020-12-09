package mx.edu.utez.menudia.model;

import mx.edu.utez.menu.model.MenuDao;
import mx.edu.utez.tools.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MenuDiaDao {

    public List getMenusDia(){
        ArrayList<MenuDia> listMenusDia = new ArrayList();
        try{
            Connection con = ConnectionDB.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM menudia");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                MenuDia obj = new MenuDia();
                MenuDao menu = new MenuDao();
                obj.setIdMenuDia(rs.getInt(1));
                obj.setFecha(rs.getDate(2));
                obj.setStatus(rs.getBoolean(3));
                obj.setIdMenu(menu.getMenuById(rs.getInt(4)));
                listMenusDia.add(obj);
            }
            rs.close();
            con.close();
        }catch(Exception e){
            System.err.println("LIST "+e.getMessage());
        }
        return listMenusDia;
    }

    public MenuDia getMenuDiaById(int idMenuDia){
        MenuDia obj = new MenuDia();
        try{
            Connection con = ConnectionDB.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM menudia WHERE idMenuDia = ?");
            ps.setInt(1, idMenuDia);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                MenuDao menu = new MenuDao();
                obj.setIdMenuDia(rs.getInt(1));
                obj.setFecha(rs.getDate(2));
                obj.setStatus(rs.getBoolean(3));
                obj.setIdMenu(menu.getMenuById(rs.getInt(4)));
            }
            rs.close();
            con.close();
        }catch(Exception e){
            System.err.println("Error de conexión");
        }
        return obj;
    }

    public MenuDia createMenuDia(MenuDia menuNuevo) throws SQLException {
        boolean insert = false;
        Connection con = null;
        try{
            con = ConnectionDB.getConnection();
            con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement("INSERT INTO menudia (`fecha`, `status`, `idMenu`) VALUES(?,?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setDate(1, menuNuevo.getFecha());
            ps.setBoolean(2, menuNuevo.isStatus());
            ps.setInt(3, menuNuevo.getIdMenu().getIdMenu());

            insert = (ps.executeUpdate() == 1);

            if(insert){
                try(ResultSet generatedKeys = ps.getGeneratedKeys()){
                    if(generatedKeys.next()){
                        int idRecovery = generatedKeys.getInt(1);
                        menuNuevo.setIdMenuDia(idRecovery);
                    }else{
                        throw new SQLException("FAIL NOT CREATED");
                    }
                }
            }
            con.commit();
        }catch(Exception e){
            con.rollback();
            System.err.println("Error "+e.getMessage());
        }finally {
            con.close();
        }
        return menuNuevo;
    }

    public boolean updateMenuDia(MenuDia menudia) throws  SQLException{
        boolean update = false;
        Connection con = null;
        try{
            con = ConnectionDB.getConnection();
            con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement("UPDATE menudia " +
                    "SET fecha=?, status=?, idMenu=?" +
                    " WHERE idMenuDia=?");
            ps.setDate(1, menudia.getFecha());
            ps.setBoolean(2, menudia.isStatus());
            ps.setInt(3, menudia.getIdMenu().getIdMenu());
            ps.setInt(4, menudia.getIdMenuDia());
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

    public boolean deleteMenuDia(int idMenuDia) throws SQLException{
        boolean delete = false;
        Connection con = null;
        try{
            con = ConnectionDB.getConnection();
            con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement("DELETE FROM menudia WHERE idMenuDia = ?");
            ps.setInt(1, idMenuDia);
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
