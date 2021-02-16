package mx.edu.utez.persona.model.request;

import mx.edu.utez.persona.model.Persona;
import mx.edu.utez.request.MyToken;

public class MyRequestPersona {
    private Persona persona;
    private MyToken token;

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public MyToken getToken() {
        return token;
    }

    public void setToken(MyToken token) {
        this.token = token;
    }
}
