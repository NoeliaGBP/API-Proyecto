package mx.edu.utez.rol.controller;

import mx.edu.utez.response.MyResponse;
import mx.edu.utez.rol.model.Rol;
import mx.edu.utez.rol.model.RolDAO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.List;

@Path("/roles")
public class ServiceRol {
    @GET
    @Path("/todos")
    @Produces(MediaType.APPLICATION_JSON)
    public MyResponse getRoles() {
        MyResponse resp = new MyResponse();
        List<Rol> list = (new RolDAO()).getRoles();
        if (list.get(0).getIdRol() != 0) {
            resp.setCode(200);
            resp.setMessage("Roles");
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
    @Path("/rol/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public MyResponse getRolById(@PathParam("id") int id) {
        MyResponse resp = new MyResponse();
        Rol rol = (new RolDAO()).getRolById(id);
        if (rol.getIdRol() != 0) {
            resp.setCode(200);
            resp.setMessage("Rol");
            resp.setStatus("success");
            resp.setData(rol);
        } else {
            resp.setCode(400);
            resp.setMessage("No existe");
            resp.setStatus("error");
        }
        return resp;
    }

    @POST
    @Path("/rol")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public MyResponse createRol(Rol rol) throws SQLException {
        MyResponse resp = new MyResponse();

        Rol creado = (new RolDAO()).createRol(rol);
        if (creado.getIdRol() != 0) {
            resp.setCode(200);
            resp.setMessage("Rol creado");
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
    @Path("/rol")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public MyResponse updateRol(Rol rol) throws SQLException {
        MyResponse resp = new MyResponse();

        Rol actualizado = (new RolDAO()).updateRol(rol);
        if (actualizado.getIdRol() != 0) {
            resp.setCode(200);
            resp.setMessage("Rol actualizado");
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
    @Path("/rol/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public MyResponse deleteRol(@PathParam("id") int id) throws SQLException {
        MyResponse resp = new MyResponse();

        boolean borrado = (new RolDAO()).deleteRol(id);
        if (!borrado) {
            resp.setCode(400);
            resp.setMessage("Error, no se eliminó");
            resp.setStatus("error");
        } else {
            resp.setCode(200);
            resp.setMessage("Rol eliminado");
            resp.setStatus("success");
        }
        return resp;
    }
}