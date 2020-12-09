package mx.edu.utez.sucursalusuario.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SucursalTieneUsuarioDao {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public List getSucursalTieneUsuario() throws SQLException {
        ArrayList<SucursalTieneUsuario> list = new ArrayList();

        try{

        }catch(Exception e){

        }finally{
            if(ps!=null) ps.close();
            if(rs!=null)rs.close();
            if(con!=null) con.close();
        }

        return list;
    }

    public SucursalTieneUsuario getSucursalTieneUsuarioByUser(String user) throws SQLException{
        SucursalTieneUsuario sucursalTieneUsuario = new SucursalTieneUsuario();

        try{

        }catch(Exception e){

        }finally{
            if(ps!=null) ps.close();
            if(rs!=null)rs.close();
            if(con!=null) con.close();
        }
        return sucursalTieneUsuario;
    }

    public SucursalTieneUsuario createSucursalTieneUsuario(SucursalTieneUsuario object) throws SQLException{
        SucursalTieneUsuario usuarioSucursalInsert = new SucursalTieneUsuario();

        try{

        }catch(Exception e){

        }finally{
            if(ps!=null) ps.close();
            if(rs!=null)rs.close();
            if(con!=null) con.close();
        }

        return usuarioSucursalInsert;
    }

    public boolean updateSucursalTieneUsuario(SucursalTieneUsuario object) throws SQLException{
        boolean flag = false;

        try{

        }catch(Exception e){

        }finally{
            if(ps!=null) ps.close();
            if(rs!=null)rs.close();
            if(con!=null) con.close();
        }

        return flag;
    }

    public boolean deleteSucursalTieneUsuario (String user) throws SQLException{
        boolean flag = false;

        try{

        }catch(Exception e){

        }finally{
            if(ps!=null) ps.close();
            if(rs!=null)rs.close();
            if(con!=null) con.close();
        }

        return flag;
    }

}
