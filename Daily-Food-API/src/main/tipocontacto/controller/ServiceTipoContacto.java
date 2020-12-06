package mx.edu.utez.tipocontacto.controller;

import mx.edu.utez.response.MyResponse;
import mx.edu.utez.tipocontacto.model.TipoContacto;
import mx.edu.utez.tipocontacto.model.TipoContactoDAO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.List;

@Path("/tiposcontacto")
public class ServiceTipoContacto {
    @GET
    @Path("/todos")
    @Produces(MediaType.APPLICATION_JSON)
    public MyResponse getTiposContacto() {
        MyResponse resp = new MyResponse();
        List<TipoContacto> list = (new TipoContactoDAO()).getTiposContacto();
        if (list.get(0).getIdTipoContacto() != 0) {
            resp.setCode(200);
            resp.setMessage("Tipos de Contacto");
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
    @Path("/tipocontacto/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public MyResponse getTipoContactoById(@PathParam("id") int id) {
        MyResponse resp = new MyResponse();
        TipoContacto tipoContacto = (new TipoContactoDAO()).getTipoContactoById(id);
        if (tipoContacto.getIdTipoContacto() != 0) {
            resp.setCode(200);
            resp.setMessage("TipoContacto");
            resp.setStatus("success");
            resp.setData(tipoContacto);
        } else {
            resp.setCode(400);
            resp.setMessage("No existe");
            resp.setStatus("error");
        }
        return resp;
    }

    @POST
    @Path("/tipocontacto")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public MyResponse createTipoContacto(TipoContacto tipoContacto) throws SQLException {
        MyResponse resp = new MyResponse();

        TipoContacto creado = (new TipoContactoDAO()).createTipoContacto(tipoContacto);
        if (creado.getIdTipoContacto() != 0) {
            resp.setCode(200);
            resp.setMessage("Tipo de Contacto creado");
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
    @Path("/tipocontacto")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public MyResponse updateTipoContacto(TipoContacto tipoContacto) throws SQLException {
        MyResponse resp = new MyResponse();

        TipoContacto actualizado = (new TipoContactoDAO()).updateTipoContacto(tipoContacto);
        if (actualizado.getIdTipoContacto() != 0) {
            resp.setCode(200);
            resp.setMessage("Tipo de Contacto actualizado");
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
    @Path("/tipocontacto/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public MyResponse deleteTipoContacto(@PathParam("id") int id) throws SQLException {
        MyResponse resp = new MyResponse();

        boolean borrado = (new TipoContactoDAO()).deleteTipoContacto(id);
        if (!borrado) {
            resp.setCode(400);
            resp.setMessage("Error, no se eliminó");
            resp.setStatus("error");
        } else {
            resp.setCode(200);
            resp.setMessage("Tipo de Contacto eliminado");
            resp.setStatus("success");
        }
        return resp;
    }
}