package mx.edu.utez.pedido.model;

import mx.edu.utez.pedidotieneplatillo.model.PedidoTienePlatillo;
import mx.edu.utez.persona.model.Persona;
import mx.edu.utez.platillo.model.Platillo;

import java.util.ArrayList;

public class PedidoCompleto {

    private int idPedido;
    private Persona persona;
    private ArrayList<PedidoTienePlatillo> pedidoplatillos;
    private ArrayList<Platillo> platillos;

    public PedidoCompleto() {
    }

    public PedidoCompleto(int idPedido, Persona persona, ArrayList<PedidoTienePlatillo> pedidoplatillos, ArrayList<Platillo> platillos) {
        this.idPedido = idPedido;
        this.persona = persona;
        this.pedidoplatillos = pedidoplatillos;
        this.platillos = platillos;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
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

    public ArrayList<Platillo> getPlatillos() {
        return platillos;
    }

    public void setPlatillos(ArrayList<Platillo> platillos) {
        this.platillos = platillos;
    }
}
