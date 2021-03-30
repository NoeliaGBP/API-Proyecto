package mx.edu.utez.imagenplatillo.model;

import java.io.*;
import mx.edu.utez.platillo.model.Platillo;
import org.apache.commons.io.FileUtils;

public class ImagenPlatillo {
    private int idImagenPlatillo;
    private Platillo idPlatillo;
    private String img;
    private File imgFile;

    public File getImgFile() {
        return imgFile;
    }

    public void setImgFile(File imgFile) {
        this.imgFile = imgFile;
    }

    public ImagenPlatillo() {
    }

    public int getIdImagenPlatillo() {
        return idImagenPlatillo;
    }

    public void setIdImagenPlatillo(int idImagenPlatillo) {
        this.idImagenPlatillo = idImagenPlatillo;
    }

    public Platillo getIdPlatillo() {
        return idPlatillo;
    }

    public void setIdPlatillo(Platillo idPlatillo) {
        this.idPlatillo = idPlatillo;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}