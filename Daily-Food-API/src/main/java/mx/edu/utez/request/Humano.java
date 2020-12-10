package mx.edu.utez.request;

import mx.edu.utez.persona.model.Persona;
import mx.edu.utez.rol.model.Rol;
import mx.edu.utez.usuario.model.Usuario;

public class Humano {
    private Persona persona;
    private Usuario usuario;
    private Rol rol;

    public Humano() {
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
}
