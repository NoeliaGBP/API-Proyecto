package mx.edu.utez.controllers;

import mx.edu.utez.persona.model.Persona;
import mx.edu.utez.persona.model.PersonaDAO;
import mx.edu.utez.request.MyRequest;
import mx.edu.utez.response.MyResponse;
import mx.edu.utez.token.Token;
import mx.edu.utez.usuario.model.Usuario;
import mx.edu.utez.usuario.model.UsuarioDAO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.List;

@Path("/daily")
public class ServiceUsuario {

    @GET
    @Path("/usuarios")
    @Produces(MediaType.APPLICATION_JSON)
    public MyResponse getUsuarios() {
        MyResponse resp = new MyResponse();
        List<Usuario> list = (new UsuarioDAO()).getUsuarios();
        if (list.size() != 0) {
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
    @Path("/usuarios/{user}")
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
    @Path("/usuarios")
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

    @POST
    @Path("/singin")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public MyResponse signin(Usuario usuario) throws SQLException {
        MyResponse resp = new MyResponse();

        //Primero se crea la persona
        Persona personaCreated = (new PersonaDAO().createPersona(usuario.getIdPersona()));

        if(personaCreated.getIdPersona() != 0){
            //Se asigna la persona creada al usuario, esto con  el objetivo de insertar el idPersona en la entidad usuario
            usuario.setIdPersona(personaCreated );
            Usuario creado = (new UsuarioDAO()).createUsuario(usuario);
            //Se comprueba que el usuario se creo
            if (creado.getNombreUsuario() != null && !creado.getNombreUsuario().isEmpty()) {
                resp.setCode(200);
                resp.setMessage("Usuario creado");
                resp.setStatus("success");
                resp.setData(creado);
            } else {
                //En caso de que el usuario no se haya creado, se elimina la persona
                boolean flag = (new PersonaDAO().deletePersona(personaCreated.getIdPersona()));
                resp.setCode(400);
                resp.setMessage("Error, no se creó.");
                resp.setStatus("error");
            }
        }

        return resp;
    }

    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public MyResponse login (Usuario usuario) throws SQLException {
        MyResponse resp = new MyResponse();

        Usuario login = (new UsuarioDAO().login(usuario)) ;
        if (login != null){
            Token myToken = new Token();

            resp.setCode(200);
            resp.setMessage("Sesión iniciada");
            resp.setStatus("success");
            resp.setData(myToken.createToken(login));
        } else {
            resp.setCode(400);
            resp.setMessage("Usuario o contraseña erroneos");
            resp.setStatus("error");
        }
        return resp;
    }

    @POST
    @Path("/login2")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public MyResponse login2 (Usuario usuario) throws SQLException {
        MyResponse resp = new MyResponse();

        Usuario user = (new UsuarioDAO().login(usuario)) ;
        if (user != null){
            user.setToken(0);
            user.setContrasenia("");

            resp.setCode(200);
            resp.setMessage("Sesión iniciada");
            resp.setStatus("success");
            resp.setData(user);
        } else {
            resp.setCode(400);
            resp.setMessage("Usuario o contraseña erroneos");
            resp.setStatus("error");
        }
        return resp;
    }



    @PUT
    @Path("/usuarios")
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
    @Path("/usuarios/{user}")
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