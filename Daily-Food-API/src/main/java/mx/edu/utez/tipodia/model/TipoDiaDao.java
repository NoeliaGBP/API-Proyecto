package mx.edu.utez.tipodia.model;

import mx.edu.utez.dia.model.DiaDao;
import mx.edu.utez.sucursal.model.SucursalDao;
import mx.edu.utez.tools.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TipoDiaDao {

    public List getTiposDia(){
        ArrayList<TipoDia> tipoDias = new ArrayList();
        try{
            Connection con = ConnectionDB.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM tipodia");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                TipoDia tipoDia = new TipoDia();
                SucursalDao sucursal = new SucursalDao();
                DiaDao dia = new DiaDao();
                tipoDia.setIdTipoDia(rs.getInt(1));
                tipoDia.setDiaCompra(rs.getBoolean(2));
                tipoDia.setDiaRegistro(rs.getBoolean(3));
                tipoDia.setIdSucursal(sucursal.getSucursalById(rs.getInt(4)));
                tipoDia.setIdDia(dia.getDiaById(rs.getInt(5)));
                tipoDias.add(tipoDia);
            }
            ps.close();
            rs.close();
            con.close();
        }catch(Exception e){
            System.err.println("LIST TipoDia " + e.getMessage());
        }
        return tipoDias;
    }

    public TipoDia getTipoDiaById(int idTipoDia){
        TipoDia tipoDia = new TipoDia();
        try{
            Connection con = ConnectionDB.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM tipodia WHERE idTipoDia = ?");
            ps.setInt(1, idTipoDia);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                DiaDao dia = new DiaDao();
                SucursalDao sucursal = new SucursalDao();
                tipoDia.setIdTipoDia(rs.getInt(1));
                tipoDia.setDiaCompra(rs.getBoolean(2));
                tipoDia.setDiaRegistro(rs.getBoolean(3));
                tipoDia.setIdSucursal(sucursal.getSucursalById(rs.getInt(4)));
                tipoDia.setIdDia(dia.getDiaById(rs.getInt(5)));
            }
            rs.close();
            ps.close();
            con.close();
        }catch(Exception e){
            System.err.println("GET TipoDia" +e.getMessage());
        }
        return tipoDia;
    }

    public TipoDia createTipoDia(TipoDia tipoDiaX) throws SQLException {
        boolean insert = false;
        Connection con = null;
        try{
            con = ConnectionDB.getConnection();
            con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement("INSERT INTO tipodia (`diaCompra`, `diaRegistro`, " +
                    "`idSucursal`, `idDia`) VALUES (?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setBoolean(1, tipoDiaX.isDiaCompra());
            ps.setBoolean(2, tipoDiaX.isDiaRegistro());
            ps.setInt(3, tipoDiaX.getIdSucursal().getIdSucursal());
            ps.setInt(4, tipoDiaX.getIdDia().getIdDia());
            insert = (ps.executeUpdate() == 1);

            if(insert){
                con.commit();
                try(ResultSet generatedKeys = ps.getGeneratedKeys()){
                    if(generatedKeys.next()){
                        int idRecovery = generatedKeys.getInt(1);
                        tipoDiaX.setIdTipoDia(idRecovery);
                    }else{
                        throw new SQLException("FAIL NOT CREATED");
                    }
                }
            }
            ps.close();
        }catch (Exception e){
            con.rollback();
            System.err.println("CREATE TipoDia " + e.getMessage());
        }finally {
            con.close();
        }
        return tipoDiaX;
    }

    public boolean updateTipoDia(TipoDia newTipoDia) throws SQLException {
        boolean updated = false;
        Connection con = null;
        try{
            con = ConnectionDB.getConnection();
            con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement("UPDATE tipodia SET diaCompra = ?," +
                    " diaRegistro = ?, idSucursal = ?, idDia = ? WHERE idTipoDia = ?");
            ps.setBoolean(1, newTipoDia.isDiaCompra());
            ps.setBoolean(2, newTipoDia.isDiaRegistro());
            ps.setInt(3, newTipoDia.getIdSucursal().getIdSucursal());
            ps.setInt(4, newTipoDia.getIdDia().getIdDia());
            ps.setInt(5, newTipoDia.getIdTipoDia());
            updated = (ps.executeUpdate() == 1);

            if(updated){
                con.commit();
            }
            ps.close();
        }catch (Exception e){
            con.rollback();
            System.err.println("UPDATE TipoDia" + e.getMessage());
        }finally{
            con.close();
        }
        return updated;
    }

    public boolean deleteTipoDia(int idTipoDia) throws SQLException{
        boolean deleted = false;
        Connection con = null;
        try{
            con = ConnectionDB.getConnection();
            con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement("DELETE FROM tipodia WHERE idTipoDia = ?");
            ps.setInt(1, idTipoDia);
            deleted = (ps.executeUpdate() == 1);

            if(deleted){
                con.commit();
            }
            ps.close();
        }catch (Exception e){
            con.rollback();
            System.err.println("DELETE TipoDia " + e.getMessage());
        }finally {
            con.close();
        }
        return deleted;
    }

}
