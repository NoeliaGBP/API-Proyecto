package mx.edu.utez.menudia.model;

import mx.edu.utez.menu.model.Menu;

import java.sql.Date;

public class MenuDia {

    private int idMenuDia;
    private Date fecha;
    private boolean status;
    private Menu idMenu;

    public int getIdMenuDia() {
        return idMenuDia;
    }

    public void setIdMenuDia(int idMenuDia) {
        this.idMenuDia = idMenuDia;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
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

}
