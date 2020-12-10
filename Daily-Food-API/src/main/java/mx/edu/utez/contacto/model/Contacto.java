package mx.edu.utez.contacto.model;

import mx.edu.utez.persona.model.Persona;
import mx.edu.utez.tipocontacto.model.TipoContacto;

public class Contacto {
    private int idContacto;
    private Persona idPersona;
    private TipoContacto idTipoContacto;
    private String contacto;

    public int getIdContacto() {
        return idContacto;
    }

    public void setIdContacto(int idContacto) {
        this.idContacto = idContacto;
    }

    public Persona getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Persona idPersona) {
        this.idPersona = idPersona;
    }

    public TipoContacto getIdTipoContacto() {
        return idTipoContacto;
    }

    public void setIdTipoContacto(TipoContacto idTipoContacto) {
        this.idTipoContacto = idTipoContacto;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }
}
