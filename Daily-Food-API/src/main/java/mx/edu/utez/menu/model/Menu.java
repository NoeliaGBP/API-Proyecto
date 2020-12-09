package mx.edu.utez.menu.model;

import mx.edu.utez.sucursal.model.Sucursal;
import mx.edu.utez.tipomenu.model.TipoMenu;

public class Menu {
    private int idMenu;
    private String nombreMenu;
    private TipoMenu idTipoMenu;
    private Sucursal idSucursal;

    public int getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(int idMenu) {
        this.idMenu = idMenu;
    }

    public String getNombreMenu() {
        return nombreMenu;
    }

    public void setNombreMenu(String nombreMenu) {
        this.nombreMenu = nombreMenu;
    }

    public TipoMenu getIdTipoMenu() {
        return idTipoMenu;
    }

    public void setIdTipoMenu(TipoMenu idTipoMenu) {
        this.idTipoMenu = idTipoMenu;
    }

    public Sucursal getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(Sucursal idSucursal) {
        this.idSucursal = idSucursal;
    }

}
