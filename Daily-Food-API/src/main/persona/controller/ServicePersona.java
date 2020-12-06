package mx.edu.utez.persona.controller;

import mx.edu.utez.response.MyResponse;
import mx.edu.utez.persona.model.Persona;
import mx.edu.utez.persona.model.PersonaDAO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.List;

@Path("/personas")
public class ServicePersona {
    @GET
    @Path("/todas")
    @Produces(MediaType.APPLICATION_JSON)
    public MyResponse getPersonas() {
        MyResponse resp = new MyResponse();
        List<Persona> list = (new PersonaDAO()).getPersonas();
        if (list.get(0).getIdPersona() != 0) {
            resp.setCode(200);
            resp.setMessage("Personas");
            resp.setStatus("success");
            resp.setData(list);
        } else {
            resp.setCode(400);
            resp.setMessage("Error");
            resp.setStatus("error");
        }
        return resp;
    }

    @GET
    @Path("/persona/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public MyResponse getPersonaById(@PathParam("id") int id) {
        MyResponse resp = new MyResponse();
        Persona persona = (new PersonaDAO()).getPersonaById(id);
        if (persona.getIdPersona() != 0) {
            resp.setCode(200);
            resp.setMessage("Persona");
            resp.setStatus("success");
            resp.setData(persona);
        } else {
            resp.setCode(400);
            resp.setMessage("No existe");
            resp.setStatus("error");
        }
        return resp;
    }

    @POST
    @Path("/persona")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public MyResponse createPersona(Persona persona) throws SQLException {
        MyResponse resp = new MyResponse();

        Persona contacto2 = (new PersonaDAO()).createPersona(persona);
        if (contacto2.getIdPersona() != 0) {
            resp.setCode(200);
            resp.setMessage("Persona creada");
            resp.setStatus("success");
            resp.setData(contacto2);
        } else {
            resp.setCode(400);
            resp.setMessage("Error, no se creó.");
            resp.setStatus("error");
        }
        return resp;
    }

    @PUT
    @Path("/persona")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public MyResponse updatePersona(Persona persona) throws SQLException {
        MyResponse resp = new MyResponse();

        Persona actualizado = (new PersonaDAO()).updatePersona(persona);
        if (actualizado.getIdPersona() != 0) {
            resp.setCode(200);
            resp.setMessage("Persona actualizada");
            resp.setStatus("success");
            resp.setData(actualizado);
        } else {
            resp.setCode(400);
            resp.setMessage("Error, no se actualizó.");
            resp.setStatus("error");
        }
        return resp;
    }

    @DELETE
    @Path("/persona/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public MyResponse deletePersona(@PathParam("id") int id) throws SQLException {
        MyResponse resp = new MyResponse();

        boolean borrado = (new PersonaDAO()).deletePersona(id);
        if (!borrado) {
            resp.setCode(400);
            resp.setMessage("Error, no se eliminó");
            resp.setStatus("error");
        } else {
            resp.setCode(200);
            resp.setMessage("Persona eliminada");
            resp.setStatus("success");
        }
        return resp;
    }
}