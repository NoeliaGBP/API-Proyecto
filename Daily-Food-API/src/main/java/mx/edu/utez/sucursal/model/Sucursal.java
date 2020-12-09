package mx.edu.utez.sucursal.model;

import mx.edu.utez.direccion.model.Direccion;

public class Sucursal {
    private int idSucursal;
    private String nombreSucursal;
    private Direccion idDireccion;

    public Sucursal(){

    }

    public int getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(int idSucursal) {
        this.idSucursal = idSucursal;
    }

    public String getNombreSucursal() {
        return nombreSucursal;
    }

    public void setNombreSucursal(String nombreSucursal) {
        this.nombreSucursal = nombreSucursal;
    }

    public Direccion getIdDireccion() {
        return idDireccion;
    }

    public void setIdDireccion(Direccion idDireccion) {
        this.idDireccion = idDireccion;
    }
}
