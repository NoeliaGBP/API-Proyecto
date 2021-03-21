package mx.edu.utez.platillo.model;

import mx.edu.utez.imagenplatillo.model.ImagenPlatillo;
import mx.edu.utez.ingredientePlatillo.model.IngredientePlatillo;
import mx.edu.utez.precio.model.Precio;
import mx.edu.utez.preparacion.model.Preparacion;
import mx.edu.utez.tipoPlatillo.model.TipoPlatillo;

import java.util.List;

public class PlatilloCompleto {

    private Platillo platillo;
    private Preparacion preparacion;
    private Precio precio;
    private List<IngredientePlatillo> ingredientes;
    private ImagenPlatillo imagen;
    private TipoPlatillo tipoPlatillo;

    public PlatilloCompleto() {
    }

    public PlatilloCompleto(Platillo platillo, Preparacion preparacion, Precio precio, List<IngredientePlatillo> ingredientes, ImagenPlatillo imagen, TipoPlatillo tipoPlatillo) {
        this.platillo = platillo;
        this.preparacion = preparacion;
        this.precio = precio;
        this.ingredientes = ingredientes;
        this.imagen = imagen;
        this.tipoPlatillo = tipoPlatillo;
    }

    public Platillo getPlatillo() {
        return platillo;
    }

    public void setPlatillo(Platillo platillo) {
        this.platillo = platillo;
    }

    public Preparacion getPreparacion() {
        return preparacion;
    }

    public void setPreparacion(Preparacion preparacion) {
        this.preparacion = preparacion;
    }

    public Precio getPrecio() {
        return precio;
    }

    public void setPrecio(Precio precio) {
        this.precio = precio;
    }

    public List<IngredientePlatillo> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(List<IngredientePlatillo> ingredientes) {
        this.ingredientes = ingredientes;
    }

    public ImagenPlatillo getImagen() {
        return imagen;
    }

    public void setImagen(ImagenPlatillo imagen) {
        this.imagen = imagen;
    }

    public TipoPlatillo getTipoPlatillo() {
        return tipoPlatillo;
    }

    public void setTipoPlatillo(TipoPlatillo tipoPlatillo) {
        this.tipoPlatillo = tipoPlatillo;
    }
}
