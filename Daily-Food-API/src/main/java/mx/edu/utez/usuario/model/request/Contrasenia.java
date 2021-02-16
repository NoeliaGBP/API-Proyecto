package mx.edu.utez.usuario.model.request;

import mx.edu.utez.request.MyToken;
import mx.edu.utez.usuario.model.Usuario;

public class Contrasenia {
    private Usuario usuario;
    private String contraseniaNueva;
    private MyToken token;

    public Contrasenia() {
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getContraseniaNueva() {
        return contraseniaNueva;
    }

    public void setContraseniaNueva(String contraseniaNueva) {
        this.contraseniaNueva = contraseniaNueva;
    }

    public MyToken getToken() {
        return token;
    }

    public void setToken(MyToken token) {
        this.token = token;
    }
}
