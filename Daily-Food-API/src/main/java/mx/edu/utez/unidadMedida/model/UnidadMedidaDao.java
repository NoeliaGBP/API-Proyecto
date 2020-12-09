package mx.edu.utez.unidadMedida.model;

import mx.edu.utez.tools.ConnectionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

}
