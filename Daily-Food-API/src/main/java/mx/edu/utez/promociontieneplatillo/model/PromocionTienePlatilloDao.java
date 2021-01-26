package mx.edu.utez.promociontieneplatillo.model;

import mx.edu.utez.platillo.model.PlatilloDao;
import mx.edu.utez.promocion.model.PromocionDao;
import mx.edu.utez.tools.ConnectionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PromocionTienePlatilloDao {
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public List  readPromocionTienePlatilloByPromocion(int idPromocion) throws SQLException {
        ArrayList<PromocionTienePlatillo> list = new ArrayList();

        try{
            con = ConnectionDB.getConnection();
            ps = con.prepareStatement("SELECT * FROM promociontieneplatillo WHERE idPromocion = ?;");
            ps.setInt(1,idPromocion);

            rs = ps.executeQuery();

            PromocionDao promocionDao = new PromocionDao();
            PlatilloDao platilloDao = new PlatilloDao();
            if(rs.next()){
                PromocionTienePlatillo promocionTienePlatillo = new PromocionTienePlatillo();
                promocionTienePlatillo.setIdPromocion(promocionDao.readPromocionById(rs.getInt(1)));
                promocionTienePlatillo.setIdPlatillo(platilloDao.getPlatilloById(rs.getInt(2)));
                System.out.println(promocionTienePlatillo);
                list.add(promocionTienePlatillo);
            }

        }catch(Exception e){
            System.err.println("ERROR "+e.getMessage());
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

    public boolean createPromocionTienePlatillo(PromocionTienePlatillo object) throws SQLException{
        boolean flag = false;

        try{
            con = ConnectionDB.getConnection();
            con.setAutoCommit(false);
            ps = con.prepareStatement("INSERT INTO promociontieneplatillo(idPromocion,idPlatillo) VALUES (?,?);");
            ps.setInt(1,object.getIdPromocion().getIdPromocion());
            ps.setInt(2,object.getIdPlatillo().getIdPlatillo());

            flag = ps.executeUpdate() == 1;

            if(flag){
                con.commit();
            }

        }catch(Exception e){
            con.rollback();
            System.err.println("ERROR CREATED PROMOCION TIENE PLATILLO "+e.getMessage() );
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

    public boolean deletePromocionTienePlatillo(PromocionTienePlatillo object) throws SQLException{
        boolean flag = false;

        try{
            con = ConnectionDB.getConnection();
            con.setAutoCommit(false);
            ps = con.prepareStatement("DELETE FROM promociontieneplatillo WHERE idPromocion = ? AND idPlatillo = ?;");
            ps.setInt(1,object.getIdPromocion().getIdPromocion());
            ps.setInt(2,object.getIdPlatillo().getIdPlatillo());

            flag = ps.executeUpdate() == 1;

            if(flag){
                con.commit();
            }

        }catch(Exception e){
            con.rollback();
            System.err.println("ERROR DELETE PROMOCION TIENE PLATILLO "+e.getMessage() );
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
