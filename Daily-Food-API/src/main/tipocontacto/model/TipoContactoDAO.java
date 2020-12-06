package mx.edu.utez.tipocontacto.model;

import tools.ConexionSQL;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TipoContactoDAO {
    public List<TipoContacto> getTiposContacto() {
        ArrayList<TipoContacto> tipoContactos = new ArrayList<>();
        try {
            Connection con = ConexionSQL.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM tipocontacto");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs != null) {
                    TipoContacto tipoContacto = new TipoContacto();
                    tipoContacto.setIdTipoContacto(rs.getInt(1));
                    tipoContacto.setNombreTipo(rs.getString(2));
                    tipoContactos.add(tipoContacto);
                }
            }
            if (con!=null) con.close();
            if (ps!=null) ps.close();
            if (rs!=null) rs.close();
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
        return tipoContactos;
    }

    public TipoContacto getTipoContactoById(int id) {
        TipoContacto tipoContacto = new TipoContacto();
        try {
            Connection con = ConexionSQL.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM tipocontacto WHERE idTipoContacto = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs != null) {
                    tipoContacto.setIdTipoContacto(rs.getInt(1));
                    tipoContacto.setNombreTipo(rs.getString(2));
                }
            }
            if (con!=null) con.close();
            if (ps!=null) ps.close();
            if (rs!=null) rs.close();
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
        return tipoContacto;
    }

    public TipoContacto createTipoContacto(TipoContacto tipo) throws SQLException {
        TipoContacto nuevo = new TipoContacto();
        Connection con = null;
        try {
            con = ConexionSQL.getConnection();
            con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement("INSERT INTO `tipocontacto`(`nombreTipo`) VALUES(?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, tipo.getNombreTipo());

            boolean created = ps.executeUpdate() == 1;
            if (created) {
                con.commit();
                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int idRecovery = generatedKeys.getInt(1);
                        nuevo = getTipoContactoById(idRecovery);
                    } else {
                        throw new SQLException("FAIL NOT CREATED");
                    }
                }
            }
            if (ps != null) ps.close();
        }  catch (Exception e) {
            con.rollback();
            e.getMessage();
            e.printStackTrace();
        } finally {
            if (con != null)  con.close();
        }
        return nuevo;
    }

    public TipoContacto updateTipoContacto(TipoContacto tipo) throws SQLException {
        TipoContacto actualizado = new TipoContacto();
        Connection con = null;
        try {
            con = ConexionSQL.getConnection();
            con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement("UPDATE `tipocontacto` SET `nombreTipo`=? WHERE `idTipoContacto`=?");
            ps.setString(1, tipo.getNombreTipo());
            ps.setInt(2, tipo.getIdTipoContacto());
            boolean updated = ps.executeUpdate() == 1;
            if (updated) {
                con.commit();
                actualizado = getTipoContactoById(tipo.getIdTipoContacto());
            }
            if (ps != null) ps.close();
        }  catch (Exception e) {
            con.rollback();
            e.getMessage();
            e.printStackTrace();
        } finally {
            if (con != null)  con.close();
        }
        return actualizado;
    }

    public boolean deleteTipoContacto(int id) throws SQLException {
        boolean deleted = false;
        Connection con = null;
        try {
            con = ConexionSQL.getConnection();
            con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement("DELETE FROM `tipocontacto` WHERE idTipoContacto=?");
            ps.setInt(1, id);

            deleted = ps.executeUpdate() == 1;
            if (deleted) {
                con.commit();
            }
            if (ps != null) ps.close();
        } catch (Exception e) {
            con.rollback();
            e.getMessage();
            e.printStackTrace();
        } finally {
            if (con != null) con.close();
        }
        return deleted;
    }
}
