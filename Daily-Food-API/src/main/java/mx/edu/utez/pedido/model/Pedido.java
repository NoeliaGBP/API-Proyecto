package mx.edu.utez.pedido.model;

import mx.edu.utez.direccion.model.Direccion;
import mx.edu.utez.usuario.model.Usuario;

import java.sql.Date;
import java.sql.Time;

public class Pedido {

    private int id;
    private Date fecha;
    private double cantidadTotal;
    private double cantidadPago;
    private Time horaEntrega;
    private String status;
    private Usuario nombreUsuario;
    private Direccion  idDireccion;
    //private Sucursal idSucursal;

    public Pedido(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getCantidadTotal() {
        return cantidadTotal;
    }

    public void setCantidadTotal(double cantidadTotal) {
        this.cantidadTotal = cantidadTotal;
    }

    public double getCantidadPago() {
        return cantidadPago;
    }

    public void setCantidadPago(double cantidadPago) {
        this.cantidadPago = cantidadPago;
    }

    public Time getHoraEntrega() {
        return horaEntrega;
    }

    public void setHoraEntrega(Time horaEntrega) {
        this.horaEntrega = horaEntrega;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Direccion getDireccion() {
        return idDireccion;
    }

    public void setDireccion(Direccion direccion) {
        this.idDireccion = direccion;
    }
}
