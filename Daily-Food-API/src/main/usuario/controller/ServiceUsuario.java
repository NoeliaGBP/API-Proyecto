package mx.edu.utez.usuario.controller;

import mx.edu.utez.response.MyResponse;
import mx.edu.utez.usuario.model.Usuario;
import mx.edu.utez.usuario.model.UsuarioDAO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.List;

@Path("/usuarios")
public class ServiceUsuario {

    @GET
    @Path("/todos")
    @Produces(MediaType.APPLICATION_JSON)
    public MyResponse getUsuarios() {
        MyResponse resp = new MyResponse();
        List<Usuario> list = (new UsuarioDAO()).getUsuarios();
        if (list.get(0).getIdPersona() != 0) {
            resp.setCode(200);
            resp.setMessage("Usuarios");
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
    @Path("/usuario/{user}")
    @Produces(MediaType.APPLICATION_JSON)
    public MyResponse getUsuarioByUser(@PathParam("user") String user) {
        MyResponse resp = new MyResponse();
        Usuario usuario = (new UsuarioDAO()).getUsuarioByUser(user);
        if (usuario.getNombreUsuario() != null) {
            resp.setCode(200);
            resp.setMessage("Usuario");
            resp.setStatus("success");
            resp.setData(usuario);
        } else {
            resp.setCode(400);
            resp.setMessage("No existe");
            resp.setStatus("error");
        }
        return resp;
    }

    @POST
    @Path("/usuario")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public MyResponse createUsuario(Usuario usuario) throws SQLException {
        MyResponse resp = new MyResponse();

        Usuario creado = (new UsuarioDAO()).createUsuario(usuario);
        if ((!usuario.getNombreUsuario().isEmpty()) || usuario.getNombreUsuario() != null) {
            resp.setCode(200);
            resp.setMessage("Usuario creado");
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
    @Path("/usuario")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public MyResponse updateUsuario(Usuario usuario) throws SQLException {
        MyResponse resp = new MyResponse();

        Usuario actualizado = (new UsuarioDAO()).updateUsuario(usuario);
        if ((!usuario.getNombreUsuario().isEmpty()) || usuario.getNombreUsuario() != null) {
            resp.setCode(200);
            resp.setMessage("Usuario actualizado");
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
    @Path("/usuario/{user}")
    @Produces(MediaType.APPLICATION_JSON)
    public MyResponse deleteUsuario(@PathParam("user") String user) throws SQLException {
        MyResponse resp = new MyResponse();

        boolean borrado = (new UsuarioDAO()).deleteUsuario(user);
        if (!borrado) {
            resp.setCode(400);
            resp.setMessage("Error, no se eliminó");
            resp.setStatus("error");
        } else {
            resp.setCode(200);
            resp.setMessage("Usuario eliminado");
            resp.setStatus("success");
        }
        return resp;
    }
}