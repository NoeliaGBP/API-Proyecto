package mx.edu.utez.tipoPlatillo.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "TipoPlatillo")
@XmlAccessorType(XmlAccessType.FIELD)
public class TipoPlatillo {

    private int idTipoPlatillo;
    private String nombreTipo;

    public TipoPlatillo() {
    }

    public int getIdTipoPlatillo() {
        return idTipoPlatillo;
    }

    public void setIdTipoPlatillo(int idTipoPlatillo) {
        this.idTipoPlatillo = idTipoPlatillo;
    }

    public String getNombreTipo() {
        return nombreTipo;
    }

    public void setNombreTipo(String nombreTipo) {
        this.nombreTipo = nombreTipo;
    }
}
