package mx.edu.utez.tipodia.model;

import mx.edu.utez.dia.model.Dia;
import mx.edu.utez.sucursal.model.Sucursal;

public class TipoDia {

    private int idTipoDia;
    private boolean diaCompra;
    private boolean diaRegistro;
    private Sucursal idSucursal;
    private Dia idDia;

    public int getIdTipoDia() {
        return idTipoDia;
    }

    public void setIdTipoDia(int idTipoDia) {
        this.idTipoDia = idTipoDia;
    }

    public boolean isDiaCompra() {
        return diaCompra;
    }

    public void setDiaCompra(boolean diaCompra) {
        this.diaCompra = diaCompra;
    }

    public boolean isDiaRegistro() {
        return diaRegistro;
    }

    public void setDiaRegistro(boolean diaRegistro) {
        this.diaRegistro = diaRegistro;
    }

    public Sucursal getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(Sucursal idSucursal) {
        this.idSucursal = idSucursal;
    }

    public Dia getIdDia() {
        return idDia;
    }

    public void setIdDia(Dia idDia) {
        this.idDia = idDia;
    }

}
