package mx.edu.utez.ingrediente.model;

import mx.edu.utez.areaingrediente.model.AreaIngrediente;

public class Ingrediente {
    private int idIngrediente;
    private String nombreIngrediente;
    private AreaIngrediente idAreaIngrediente;

    public Ingrediente() {

    }

    public int getIdIngrediente() {
        return idIngrediente;
    }

    public void setIdIngrediente(int idIngrediente) {
        this.idIngrediente = idIngrediente;
    }

    public String getNombreIngrediente() {
        return nombreIngrediente;
    }

    public void setNombreIngrediente(String nombreIngrediente) {
        this.nombreIngrediente = nombreIngrediente;
    }

    public AreaIngrediente getIdAreaIngrediente() {
        return idAreaIngrediente;
    }

    public void setIdAreaIngrediente(AreaIngrediente idAreaIngrediente) {
        this.idAreaIngrediente = idAreaIngrediente;
    }
}
