package mx.edu.utez.usuario.model.request;

import mx.edu.utez.request.MyToken;
import mx.edu.utez.usuario.model.Usuario;

public class MyRequestUsuario {
    private Usuario usuario;
    private MyToken token;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public MyToken getToken() {
        return token;
    }

    public void setToken(MyToken token) {
        this.token = token;
    }
}
