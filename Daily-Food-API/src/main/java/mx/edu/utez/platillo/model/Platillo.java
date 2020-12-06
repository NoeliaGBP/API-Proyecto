package mx.edu.utez.platillo.model;


import mx.edu.utez.tipoPlatillo.model.TipoPlatillo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "Platillo")
@XmlAccessorType(XmlAccessType.FIELD)
public class Platillo {

    private int idPlatillo;
    private String nombrePlatillo;
    private int tiempoPreparacion;
    private String descripcion;
    private TipoPlatillo idTipoPlatillo;

    public Platillo() {
    }

    public int getIdPlatillo() {
        return idPlatillo;
    }

    public void setIdPlatillo(int idPlatillo) {
        this.idPlatillo = idPlatillo;
    }

    public String getNombrePlatillo() {
        return nombrePlatillo;
    }

    public void setNombrePlatillo(String nombrePlatillo) {
        this.nombrePlatillo = nombrePlatillo;
    }

    public int getTiempoPreparacion() {
        return tiempoPreparacion;
    }

    public void setTiempoPreparacion(int tiempoPreparacion) {
        this.tiempoPreparacion = tiempoPreparacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public TipoPlatillo getIdTipoPlatillo() {
        return idTipoPlatillo;
    }

    public void setIdTipoPlatillo(TipoPlatillo idTipoPlatillo) {
        this.idTipoPlatillo = idTipoPlatillo;
    }
}
