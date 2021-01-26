package mx.edu.utez.tipomenu.model;

public class TipoMenu {

    private int idTipoMenu;
    private String nombreTipoMenu;
    private boolean modoMenu;

    public int getIdTipoMenu() {
        return idTipoMenu;
    }

    public void setIdTipoMenu(int id){
        this.idTipoMenu = id;
    }

    public String getNombreTipoMenu() {
        return nombreTipoMenu;
    }

    public void setNombreTipoMenu(String nombreTipoMenu) {
        this.nombreTipoMenu = nombreTipoMenu;
    }

    public boolean isModoMenu() {
        return modoMenu;
    }

    public void setModoMenu(boolean modoMenu) {
        this.modoMenu = modoMenu;
    }

}
