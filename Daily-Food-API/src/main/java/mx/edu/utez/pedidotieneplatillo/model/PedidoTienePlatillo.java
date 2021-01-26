package mx.edu.utez.pedidotieneplatillo.model;

import mx.edu.utez.pedido.model.Pedido;
import mx.edu.utez.platilloenmenu.model.PlatilloEnMenu;

public class PedidoTienePlatillo {
    private Pedido idPedido;
    private PlatilloEnMenu idMenuPlatillo;
    private int cantidad;
    private String comentario;

    public Pedido getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Pedido idPedido) {
        this.idPedido = idPedido;
    }

    public PlatilloEnMenu getIdMenuPlatillo() {
        return idMenuPlatillo;
    }

    public void setIdMenuPlatillo(PlatilloEnMenu idMenuPlatillo) {
        this.idMenuPlatillo = idMenuPlatillo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
