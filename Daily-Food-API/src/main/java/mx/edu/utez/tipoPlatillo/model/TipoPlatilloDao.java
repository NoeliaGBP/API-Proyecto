package mx.edu.utez.tipoPlatillo.model;

import mx.edu.utez.tools.ConnectionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TipoPlatilloDao {
    Connection con = null;
    ResultSet rs = null;
    PreparedStatement ps = null;

    public TipoPlatillo getTipoPlatilloById(int id) throws SQLException {
        TipoPlatillo tipoPlatillo = new TipoPlatillo();
        try{
            con = ConnectionDB.getConnection();
            ps = con.prepareStatement("SELECT * FROM tipoplatillo WHERE idTipoPlatillo = ?;");
            ps.setInt(1,id);
            rs = ps.executeQuery();

            while(rs.next()){
                tipoPlatillo.setIdTipoPlatillo(rs.getInt(1));
                tipoPlatillo.setNombreTipo(rs.getString(2));
            }

        }catch(Exception e){
            System.err.println(e.getMessage());
        }finally{
            if(con!=null) con.close();
            if(rs!=null)rs.close();
            if(ps!=null)ps.close();
        }

        return tipoPlatillo;
    }
}
