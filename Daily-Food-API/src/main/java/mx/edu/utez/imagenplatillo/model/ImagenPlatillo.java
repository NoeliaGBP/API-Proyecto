package mx.edu.utez.imagenplatillo.model;

import mx.edu.utez.platillo.model.Platillo;

public class ImagenPlatillo {
    private int idImagenPlatillo;
    private Platillo idPlatillo;
    private String img;

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
