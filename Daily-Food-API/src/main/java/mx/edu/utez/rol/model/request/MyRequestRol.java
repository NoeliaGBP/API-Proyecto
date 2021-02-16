package mx.edu.utez.rol.model.request;

import mx.edu.utez.request.MyToken;
import mx.edu.utez.rol.model.Rol;

public class MyRequestRol {
    private Rol rol;
    private MyToken token;

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public MyToken getToken() {
        return token;
    }

    public void setToken(MyToken token) {
        this.token = token;
    }
}
