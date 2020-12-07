package mx.edu.utez.controllers;

import mx.edu.utez.response.MyResponse;
import mx.edu.utez.contacto.model.Contacto;
import mx.edu.utez.contacto.model.ContactoDAO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.List;

@Path("/daily")
public class ServiceContacto {
    @GET
    @Path("/contactos")
    @Produces(MediaType.APPLICATION_JSON)
    public MyResponse getContactos() {
        MyResponse resp = new MyResponse();
        List<Contacto> list = (new ContactoDAO()).getContactos();
        if (list.get(0).getIdContacto() != 0) {

            resp.setCode(200);
            resp.setMessage("Contactos");
            resp.setStatus("success");
            resp.setData(list);
        } else {
            resp.setCode(400);
            resp.setMessage("Error de autorización");
            resp.setStatus("error");
        }
        return resp;
    }

    @GET
    @Path("/contactos/{code}")
    @Produces(MediaType.APPLICATION_JSON)
    public MyResponse getContactoByCode(@PathParam("code") int code) {
        MyResponse resp = new MyResponse();
        Contacto contacto = (new ContactoDAO()).getContactoByCode(code);
        if (contacto.getIdContacto() != 0) {
            resp.setCode(200);
            resp.setMessage("Contacto obtenido");
            resp.setStatus("success");
            resp.setData(contacto);
        } else {
            resp.setCode(400);
            resp.setMessage("Contacto no obtenido");
            resp.setStatus("error");
        }
        return resp;
    }

    @POST
    @Path("/contactos")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public MyResponse createContacto(Contacto contacto) throws SQLException {
        MyResponse resp = new MyResponse();

        Contacto creado = (new ContactoDAO()).createContacto(contacto);
        if (creado.getIdContacto() != 0) {
            resp.setCode(200);
            resp.setMessage("Contacto creado");
            resp.setStatus("success");
            resp.setData(creado);
        } else {
            resp.setCode(400);
            resp.setMessage("Error, no se creó.");
            resp.setStatus("error");
        }
        return resp;
    }

    @PUT
    @Path("/contactos")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public MyResponse updateContacto(Contacto contacto) throws SQLException {
        MyResponse resp = new MyResponse();

        Contacto creado = (new ContactoDAO().updateContacto(contacto));
        if (creado.getIdContacto() != 0) {
            resp.setCode(200);
            resp.setMessage("Contacto actualizado");
            resp.setStatus("success");
            resp.setData(creado);
        } else {
            resp.setCode(400);
            resp.setMessage("Error, no se actualizó.");
            resp.setStatus("error");
        }
        return resp;
    }

    @DELETE
    @Path("/contactos/{code}")
    @Produces(MediaType.APPLICATION_JSON)
    public MyResponse deleteContacto(@PathParam("code") int id) throws SQLException {
        MyResponse resp = new MyResponse();

        boolean eliminado = (new ContactoDAO()).deleteContacto(id);
        if (!eliminado) {
            resp.setCode(400);
            resp.setMessage("Error, no se eliminó");
            resp.setStatus("error");
        } else {
            resp.setCode(200);
            resp.setMessage("Contacto eliminado");
            resp.setStatus("success");
        }
        return resp;
    }
}