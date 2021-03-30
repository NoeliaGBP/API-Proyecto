package mx.edu.utez.menu.model;

import mx.edu.utez.platillo.model.PlatilloCompleto;
import mx.edu.utez.platillo.model.PlatilloDao;
import mx.edu.utez.sucursal.model.SucursalDao;
import mx.edu.utez.tipomenu.model.TipoMenuDao;
import mx.edu.utez.tools.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MenuDao {

    public Menu getMenus(){
        Menu menu = new Menu();
        List<PlatilloCompleto> platillos = new ArrayList();
        try{
            Connection con = ConnectionDB.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT pem.idPlatillo, m.idMenu FROM platilloenmenu pem \n" +
                    "INNER JOIN menu m ON pem.idMenu = m.idMenu\n" +
                    "INNER JOIN menudia md on m.idMenu = md.idMenu\n" +
                    "WHERE md.fecha = curdate()\n" +
                    "AND pem.status = 1\n" +
                    "AND md.status = 1;");
            ResultSet rs = ps.executeQuery();
            PlatilloDao platDao = new PlatilloDao();
            while(rs.next()){
                menu = (this.getMenuById(2));
                PlatilloCompleto platillo = platDao.getPlatilloCompletoById(rs.getInt(1));
                platillo.setIngredientes(null);
                platillo.setPreparacion(null);
                platillos.add(platillo);
            }
            menu.setPlatillos(platillos);
            System.out.println(platillos);
            rs.close();
            ps.close();
            con.close();
        }catch(Exception e){
            System.err.println("LIST "+e.getMessage());
        }
        return menu;
    }

    public Menu getMenuById(int idMenu){
        Menu obj = new Menu();
        try{
            Connection con = ConnectionDB.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM menu WHERE idMenu = ?");
            ps.setInt(1, idMenu);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                SucursalDao sucursal = new SucursalDao();
                TipoMenuDao tipoMenu = new TipoMenuDao();
                obj.setIdMenu(rs.getInt(1));
                obj.setNombreMenu(rs.getString(2));
                obj.setIdTipoMenu(tipoMenu.getTipoMenuById(rs.getInt(3)));
                obj.setIdSucursal(sucursal.getSucursalById(rs.getInt(4)));
            }
            rs.close();
            con.close();
        }catch(Exception e){
            System.err.println("Error de conexión");
        }
        return obj;
    }

    public Menu createMenu(Menu menuNuevo) throws SQLException {
        boolean insert = false;
        Menu menuReturn = new Menu();
        Connection con = null;
        try{
            con = ConnectionDB.getConnection();
            con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement("INSERT INTO menu (`nombreMenu`, `idTipoMenu`, `idSucursal`) VALUES(?,?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, menuNuevo.getNombreMenu());
            ps.setInt(2, menuNuevo.getIdTipoMenu().getIdTipoMenu());
            ps.setInt(3, menuNuevo.getIdSucursal().getIdSucursal());
            insert = (ps.executeUpdate() == 1);
            if(insert){
                try(ResultSet generatedKeys = ps.getGeneratedKeys()){
                    if(generatedKeys.next()){
                        int idRecovery = generatedKeys.getInt(1);
                        menuReturn = menuNuevo;
                        menuReturn.setIdMenu(idRecovery);
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
        return menuReturn;
    }

    public boolean updateMenu(Menu menu) throws  SQLException{
        boolean update = false;
        Connection con = null;
        try{
            con = ConnectionDB.getConnection();
            con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement("UPDATE menu " +
                    "SET nombreMenu=?, idTipoMenu=?, idSucursal=?" +
                    " WHERE idMenu=?");
            ps.setString(1, menu.getNombreMenu());
            ps.setInt(2, menu.getIdTipoMenu().getIdTipoMenu());
            ps.setInt(3, menu.getIdSucursal().getIdSucursal());
            ps.setInt(4, menu.getIdMenu());
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

    public boolean deleteMenu(int idMenu) throws SQLException{
        boolean delete = false;
        Connection con = null;
        try{
            con = ConnectionDB.getConnection();
            con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement("DELETE FROM menu WHERE idMenu = ?");
            ps.setInt(1, idMenu);
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
