package mx.edu.utez.pedido.model;

import mx.edu.utez.direccion.model.Direccion;
import mx.edu.utez.sucursal.model.Sucursal;
import mx.edu.utez.usuario.model.Usuario;

import java.sql.Date;
import java.sql.Time;

public class Pedido {

    private int id;
    private String fecha;
    private double costoTotal;
    private double cantidadPago;
    private String horaEntrega;
    private String status;
    private Usuario nombreUsuario;
    private Direccion  idDireccion;
    private Sucursal idSucursal;

    public Pedido(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public double getCostoTotal() {
        return costoTotal;
    }

    public void setCostoTotal(double cantidadTotal) {
        this.costoTotal = costoTotal;
    }

    public double getCantidadPago() {
        return cantidadPago;
    }

    public void setCantidadPago(double cantidadPago) {
        this.cantidadPago = cantidadPago;
    }

    public String getHoraEntrega() {
        return horaEntrega;
    }

    public void setHoraEntrega(String horaEntrega) {
        this.horaEntrega = horaEntrega;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Usuario getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(Usuario nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public Direccion getIdDireccion() {
        return idDireccion;
    }

    public void setIdDireccion(Direccion idDireccion) {
        this.idDireccion = idDireccion;
    }

    public Sucursal getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(Sucursal idSucursal) {
        this.idSucursal = idSucursal;
    }
}
