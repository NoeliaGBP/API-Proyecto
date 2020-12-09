package mx.edu.utez.usuariosucursal.model;

import mx.edu.utez.sucursal.model.Sucursal;
import mx.edu.utez.usuario.model.Usuario;

public class SucursalTieneUsuario {

    private Sucursal idSucursal;
    private Usuario nombreUsuario;

    public SucursalTieneUsuario(){

    }

    public Sucursal getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(Sucursal idSucursal) {
        this.idSucursal = idSucursal;
    }

    public Usuario getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(Usuario nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
}
