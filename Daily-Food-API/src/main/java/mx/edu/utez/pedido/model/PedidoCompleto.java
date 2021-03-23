package mx.edu.utez.pedido.model;

import mx.edu.utez.pedidotieneplatillo.model.PedidoTienePlatillo;
import mx.edu.utez.persona.model.Persona;
import mx.edu.utez.platillo.model.Platillo;

import java.util.ArrayList;

public class PedidoCompleto {

    private Pedido idPedido;
    private Persona persona;
    private String telefono;
    private ArrayList<PedidoTienePlatillo> pedidoplatillos;

    public PedidoCompleto() {
    }

    public PedidoCompleto(Pedido idPedido, Persona persona, String telefono, ArrayList<PedidoTienePlatillo> pedidoplatillos) {
        this.idPedido = idPedido;
        this.persona = persona;
        this.telefono = telefono;
        this.pedidoplatillos = pedidoplatillos;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Pedido getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Pedido idPedido) {
        this.idPedido = idPedido;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public ArrayList<PedidoTienePlatillo> getPedidoplatillos() {
        return pedidoplatillos;
    }

    public void setPedidoplatillos(ArrayList<PedidoTienePlatillo> pedidoplatillos) {
        this.pedidoplatillos = pedidoplatillos;
    }
}
