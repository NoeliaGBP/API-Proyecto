package mx.edu.utez.ingredientePlatillo.model;



import mx.edu.utez.ingrediente.model.Ingrediente;
import mx.edu.utez.platillo.model.Platillo;
import mx.edu.utez.unidadMedida.model.UnidadMedida;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "IngredientePlatillo")
@XmlAccessorType(XmlAccessType.FIELD)
public class IngredientePlatillo {

    private Ingrediente idIngrediente;
    private Platillo idPlatillo;
    private double porcion;
    private UnidadMedida idUnidadMedida;

    public IngredientePlatillo() {
    }

    public Ingrediente getIdIngrediente() {
        return idIngrediente;
    }

    public void setIdIngrediente(Ingrediente idIngrediente) {
        this.idIngrediente = idIngrediente;
    }

    public Platillo getIdPlatillo() {
        return idPlatillo;
    }

    public void setIdPlatillo(Platillo idPlatillo) {
        this.idPlatillo = idPlatillo;
    }

    public double getPorcion() {
        return porcion;
    }

    public void setPorcion(double porcion) {
        this.porcion = porcion;
    }

    public UnidadMedida getIdUnidadMedida() {
        return idUnidadMedida;
    }

    public void setIdUnidadMedida(UnidadMedida idUnidadMedida) {
        this.idUnidadMedida = idUnidadMedida;
    }
}
