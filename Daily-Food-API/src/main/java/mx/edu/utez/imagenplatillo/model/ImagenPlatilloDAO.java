package mx.edu.utez.imagenplatillo.model;

import java.io.*;

import mx.edu.utez.platillo.model.PlatilloDao;
import mx.edu.utez.tools.ConnectionDB;
import org.apache.commons.io.FileUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class ImagenPlatilloDAO {
    public List<ImagenPlatillo> getImagenes() {
        ArrayList<ImagenPlatillo> imagenes = new ArrayList<>();
        try {
            Connection con = ConnectionDB.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM imagenplatillo");
            ResultSet rs = ps.executeQuery();
            PlatilloDao platilloDao = new PlatilloDao();
            while (rs.next()) {
                if (rs != null) {
                    ImagenPlatillo imagenPlatillo = new ImagenPlatillo();
                    imagenPlatillo.setIdImagenPlatillo(rs.getInt(1));
                    imagenPlatillo.setIdPlatillo(platilloDao.getPlatilloById(rs.getInt(2)));
                    imagenPlatillo.setImg(rs.getString(3));
                    imagenes.add(imagenPlatillo);
                }
            }
            if (con != null) con.close();
            if (ps != null) ps.close();
            if (rs != null) rs.close();
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
        return imagenes;
    }

    public ImagenPlatillo getImagenPlatilloById(int id) {
        ImagenPlatillo imagenPlatillo = new ImagenPlatillo();
        try {
            Connection con = ConnectionDB.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM imagenplatillo WHERE idImagenPlatillo = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            PlatilloDao platilloDao = new PlatilloDao();
            while (rs.next()) {
                if (rs != null) {
                    imagenPlatillo.setIdImagenPlatillo(rs.getInt(1));
                    imagenPlatillo.setIdPlatillo(platilloDao.getPlatilloById(rs.getInt(2)));
                    imagenPlatillo.setImg(rs.getString(3));
                }
            }
            if (con != null) con.close();
            if (ps != null) ps.close();
            if (rs != null) rs.close();
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
        return imagenPlatillo;
    }

    public ImagenPlatillo createImagenPlatillo(File imagenPlatillo, int idPlatillo) throws SQLException {
        ImagenPlatillo nueva = new ImagenPlatillo();
        Connection con = null;
        try {
            con = ConnectionDB.getConnection();
            con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement("INSERT INTO `imagenplatillo`(`idPlatillo`, `img`) VALUES(?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, (new PlatilloDao()).getPlatilloById(idPlatillo).getIdPlatillo());
            ps.setString(2, convertFile(imagenPlatillo));

            boolean created = ps.executeUpdate() == 1;
            if (created) {
                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int id = generatedKeys.getInt(1);
                        nueva = getImagenPlatilloById(id);
                        con.commit();
                    }
                }
            }
            if (ps != null) ps.close();
        } catch (Exception e) {
            if (con != null) con.rollback();
            e.printStackTrace();
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
        } finally {
            if (con != null) con.close();
        }
        return nueva;
    }

    public ImagenPlatillo updateImagenPlatillo(File imagen, ImagenPlatillo imagenPlatillo) throws SQLException {
        ImagenPlatillo actualizada = new ImagenPlatillo();
        Connection con = null;
        try {
            con = ConnectionDB.getConnection();
            con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement("UPDATE `imagenplatillo` SET `idPlatillo`=?,`img`=? WHERE `idImagenPlatillo` = ?");
            ps.setInt(1, imagenPlatillo.getIdPlatillo().getIdPlatillo());
            ps.setString(2, convertFile(imagen));
            ps.setInt(3, imagenPlatillo.getIdImagenPlatillo());
            boolean updated = ps.executeUpdate() == 1;
            if (updated) {
                con.commit();
                actualizada = getImagenPlatilloById(imagenPlatillo.getIdImagenPlatillo());
            }
            if (ps != null) ps.close();
        } catch (Exception e) {
            con.rollback();
            e.getMessage();
            e.printStackTrace();
        } finally {
            if (con != null) con.close();
        }
        return actualizada;
    }

    public boolean deleteImagenPlatillo(int idImagenPlatillo) throws SQLException {
        boolean deleted = false;
        Connection con = null;
        try {
            con = ConnectionDB.getConnection();
            con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement("DELETE FROM `imagenplatillo` WHERE idImagenPlatillo = ?");
            ps.setInt(1, idImagenPlatillo);

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

    public static String convertFile(File file) throws IOException {
        byte[] fileContent = FileUtils.readFileToByteArray(file);
        return Base64.getEncoder().encodeToString(fileContent);
    }
}
