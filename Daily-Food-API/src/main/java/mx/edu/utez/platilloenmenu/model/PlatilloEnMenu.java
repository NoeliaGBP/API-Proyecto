package mx.edu.utez.platilloenmenu.model;

import mx.edu.utez.menu.model.Menu;

public class PlatilloEnMenu {

    private int idPlatilloMenu;
    private int cantidadEstimada;
    private boolean status;
    private Menu idMenu;
    private Platillo idPlatillo;

    public int getIdPlatilloMenu() {
        return idPlatilloMenu;
    }

    public void setIdPlatilloMenu(int idMenuPlatillo) {
        this.idPlatilloMenu = idMenuPlatillo;
    }

    public int getCantidadEstimada() {
        return cantidadEstimada;
    }

    public void setCantidadEstimada(int cantidadEstimada) {
        this.cantidadEstimada = cantidadEstimada;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Menu getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(Menu idMenu) {
        this.idMenu = idMenu;
    }

    public Platillo getIdPlatillo() {
        return idPlatillo;
    }

    public void setIdPlatillo(Platillo idPlatillo) {
        this.idPlatillo = idPlatillo;
    }

}
