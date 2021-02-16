package mx.edu.utez.controllers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import mx.edu.utez.persona.model.Persona;
import mx.edu.utez.request.MyToken;
import mx.edu.utez.response.MyResponse;
import mx.edu.utez.rol.model.Rol;
import mx.edu.utez.usuario.model.Usuario;
import mx.edu.utez.usuario.model.UsuarioDAO;
import mx.edu.utez.usuario.model.request.Contrasenia;
import mx.edu.utez.usuario.model.request.MyRequestUsuario;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.List;

@Path("/daily")
public class ServiceUsuario {
    public java.lang.Object checkToken(String token, boolean identity) {
        try {
            Algorithm algorithm = Algorithm.HMAC256("secretkey");
            JWTVerifier verifier = JWT.require(algorithm).withIssuer("auth0").build();
            DecodedJWT jwt = verifier.verify(token);
            if (identity) {
                Usuario usuario = new Usuario();
                Persona persona = new Persona();
                Rol rol = new Rol();
                persona.setIdPersona(jwt.getClaim("id").asInt());
                rol.setNombreRol(jwt.getClaim("rol").asString());
                usuario.setIdPersona(persona);
                usuario.setIdRol(rol);
                usuario.setNombreUsuario(jwt.getClaim("user").asString());
                return usuario;
            } else {
                return true;
            }
        } catch (JWTVerificationException jwtE) {
            System.err.println(jwtE.getMessage());
            return false;
        }
    }

    @POST
    @Path("/usuarios/login")
    @Produces(MediaType.APPLICATION_JSON)
    public MyResponse login(Usuario user) throws SQLException {
        MyResponse resp = new MyResponse();
        if (user.getContrasenia() != null && user.getNombreUsuario() != null) {
            Usuario usuarioL = (new UsuarioDAO()).login(user.getNombreUsuario(), user.getContrasenia());
            if (usuarioL.getNombreUsuario() != null) {
                try {
                    Algorithm algorithm = Algorithm.HMAC256("secretkey");
                    String token = JWT.create().withIssuer("auth0").withClaim("user", usuarioL.getNombreUsuario())
                            .withClaim("rol", usuarioL.getIdRol().getNombreRol()).withClaim("id", usuarioL.getIdPersona().getIdPersona()).sign(algorithm);
                    resp.setCode(200);
                    resp.setMessage("Inicio de sesión exitoso.");
                    resp.setStatus("success");
                    MyToken tokenD = new MyToken();
                    tokenD.setToken(token);
                    resp.setData(tokenD);
                } catch (JWTCreationException e) {
                    String message = "ERROR: No se generó el token";
                    resp.setCode(400);
                    resp.setMessage("No se inició sesión correctamente");
                    resp.setStatus("Error");
                    resp.setData(message);
                }
            } else {
                resp.setCode(400);
                resp.setMessage("Error, no se inició sesión");
                resp.setStatus("error");
            }
        } else {
            resp.setCode(400);
            resp.setMessage("Datos incompletos");
            resp.setStatus("error");
        }
        return resp;
    }

    @GET
    @Path("/usuarios")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public MyResponse getUsuarios(MyToken token) {
        MyResponse resp = new MyResponse();
        boolean valido = (boolean) this.checkToken(token.getToken(), false);
        if (valido) {
            Usuario validado = (Usuario) this.checkToken(token.getToken(), true);
            if(validado.getIdRol().getNombreRol().equals("Administrador")){
                List<Usuario> list = (new UsuarioDAO()).getUsuarios();
                if (list.size() != 0) {
                    resp.setCode(200);
                    resp.setMessage("Usuarios");
                    resp.setStatus("success");
                    resp.setData(list);
                } else {
                    resp.setCode(400);
                    resp.setMessage("Lista vacía");
                    resp.setStatus("Error");
                }
            }else {
                resp.setCode(400);
                resp.setMessage("ERROR: Permisos denegados");
                resp.setStatus("Error");
            }
        } else {
            resp.setCode(400);
            resp.setMessage("ERROR: Token inválido");
            resp.setStatus("Error");
        }
        return resp;
    }

    @GET
    @Path("/usuarios/rol/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public MyResponse getUsuariosByRol(@PathParam("id") int id, MyToken token) {
        MyResponse resp = new MyResponse();
        boolean valido = (boolean) this.checkToken(token.getToken(), false);
        if (valido) {
            List<Usuario> list = (new UsuarioDAO()).getUsuariosByRol(id);
            if (list.size() != 0) {
                resp.setCode(200);
                resp.setMessage("Usuarios");
                resp.setStatus("success");
                resp.setData(list);
            } else {
                resp.setCode(400);
                resp.setMessage("Lista vacía");
                resp.setStatus("Error");
            }
        } else {
            resp.setCode(400);
            resp.setMessage("ERROR: Token inválido");
            resp.setStatus("Error");
        }
        return resp;
    }

    @GET
    @Path("/usuarios/persona/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public MyResponse getUsuariosByPersona(@PathParam("id") int id, MyToken token) {
        MyResponse resp = new MyResponse();
        boolean valido = (boolean) this.checkToken(token.getToken(), false);
        if (valido) {
            List<Usuario> list = (new UsuarioDAO()).getUsuariosByPersona(id);
            if (list.size() != 0) {
                resp.setCode(200);
                resp.setMessage("Usuarios");
                resp.setStatus("success");
                resp.setData(list);
            } else {
                resp.setCode(400);
                resp.setMessage("Lista vacía");
                resp.setStatus("Error");
            }
        } else {
            resp.setCode(400);
            resp.setMessage("ERROR: Token inválido");
            resp.setStatus("Error");
        }
        return resp;
    }

    @GET
    @Path("/usuarios/{user}")
    @Produces(MediaType.APPLICATION_JSON)
    public MyResponse getUsuarioByUser(@PathParam("user") String user, MyToken token) {
        MyResponse resp = new MyResponse();
        boolean valido = (boolean) this.checkToken(token.getToken(), false);
        if (valido) {
            Usuario usuario = (new UsuarioDAO()).getUsuarioByUser(user);
            if (usuario.getNombreUsuario() != null) {
                resp.setCode(200);
                resp.setMessage("Usuario");
                resp.setStatus("success");
                resp.setData(usuario);
            } else {
                resp.setCode(400);
                resp.setMessage("No existe el usuario");
                resp.setStatus("error");
            }
        } else {
            resp.setCode(400);
            resp.setMessage("ERROR: Token inválido");
            resp.setStatus("Error");
        }
        return resp;
    }

    @POST
    @Path("/usuarios/registro")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public MyResponse createUsuario(Usuario req) throws SQLException {
        MyResponse resp = new MyResponse();
        Usuario usuario = req;
        Usuario creado = (new UsuarioDAO()).createUsuario(usuario);
        if (creado.getNombreUsuario() != null) {
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
    @Path("/usuarios/registro-por-rol")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public MyResponse createUsuarioByRol(MyRequestUsuario req) throws SQLException {
        MyResponse resp = new MyResponse();
        Usuario usuario = req.getUsuario();
        MyToken token = req.getToken();
        boolean valido = (boolean) this.checkToken(token.getToken(), false);
        if (valido) {
            Usuario creado = (new UsuarioDAO()).createUsuarioByRol(usuario);
            if (creado.getNombreUsuario() != null) {
                resp.setCode(200);
                resp.setMessage("Usuario creado");
                resp.setStatus("success");
                resp.setData(creado);
            } else {
                resp.setCode(400);
                resp.setMessage("Error, no se creó.");
                resp.setStatus("error");
            }
        } else {
            resp.setCode(400);
            resp.setMessage("ERROR: Token inválido");
            resp.setStatus("Error");
        }
        return resp;
    }

    @PUT
    @Path("/usuarios")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public MyResponse updateUsuario(MyRequestUsuario req) throws SQLException {
        MyResponse resp = new MyResponse();
        Usuario usuario = req.getUsuario();
        MyToken token = req.getToken();
        boolean valido = (boolean) this.checkToken(token.getToken(), false);
        if (valido) {
            Usuario actualizado = (new UsuarioDAO()).updateUsuario(usuario);
            if (actualizado.getNombreUsuario() != null) {
                resp.setCode(200);
                resp.setMessage("Usuario actualizado");
                resp.setStatus("success");
                resp.setData(actualizado);
            } else {
                resp.setCode(400);
                resp.setMessage("Error, no se actualizó.");
                resp.setStatus("error");
            }
        } else {
            resp.setCode(400);
            resp.setMessage("ERROR: Token inválido");
            resp.setStatus("Error");
        }
        return resp;
    }

    @PUT
    @Path("/usuarios/rol")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public MyResponse updateRolUsuario(MyRequestUsuario req) throws SQLException {
        MyResponse resp = new MyResponse();
        Usuario usuario = req.getUsuario();
        MyToken token = req.getToken();
        boolean valido = (boolean) this.checkToken(token.getToken(), false);
        if (valido) {
            Usuario actualizado = (new UsuarioDAO()).updateRolUsuario(usuario);
            if (actualizado.getNombreUsuario() != null) {
                resp.setCode(200);
                resp.setMessage("Usuario actualizado");
                resp.setStatus("success");
                resp.setData(actualizado);
            } else {
                resp.setCode(400);
                resp.setMessage("Error, no se actualizó.");
                resp.setStatus("error");
            }
        } else {
            resp.setCode(400);
            resp.setMessage("ERROR: Token inválido");
            resp.setStatus("Error");
        }
        return resp;
    }

    @PUT
    @Path("/usuarios/contraseña")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public MyResponse updateContrasenia(MyRequestUsuario req) throws SQLException {
        MyResponse resp = new MyResponse();
        Usuario usuario = req.getUsuario();
        MyToken token = req.getToken();
        boolean valido = (boolean) this.checkToken(token.getToken(), false);
        if (valido) {
            Usuario actualizado = (new UsuarioDAO()).updateContrasenia(usuario);
            if (actualizado.getNombreUsuario() != null) {
                resp.setCode(200);
                resp.setMessage("Contraseña actualizada");
                resp.setStatus("success");
                resp.setData(usuario);
            } else {
                resp.setCode(400);
                resp.setMessage("Error, no se actualizó.");
                resp.setStatus("error");
            }
        } else {
            resp.setCode(400);
            resp.setMessage("ERROR: Token inválido");
            resp.setStatus("Error");
        }
        return resp;
    }

    @PUT
    @Path("/usuarios/contraseña2")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public MyResponse updateContrasenia2(Contrasenia contrasenia) throws SQLException {
        MyResponse resp = new MyResponse();
        boolean valido = (boolean) this.checkToken(contrasenia.getToken().getToken(), false);
        if (valido) {
            Usuario actualizado = (new UsuarioDAO()).updateContrasenia2(contrasenia);
            if (actualizado.getNombreUsuario() != null) {
                resp.setCode(200);
                resp.setMessage("Contraseña actualizada");
                resp.setStatus("success");
                resp.setData(actualizado);
            } else {
                resp.setCode(400);
                resp.setMessage("Error, no se actualizó.");
                resp.setStatus("error");
            }
        } else {
            resp.setCode(400);
            resp.setMessage("ERROR: Token inválido");
            resp.setStatus("Error");
        }
        return resp;
    }

    @DELETE
    @Path("/usuarios/{user}")
    @Produces(MediaType.APPLICATION_JSON)
    public MyResponse deleteUsuario(@PathParam("user") String user, MyToken token) throws SQLException {
        MyResponse resp = new MyResponse();
        boolean valido = (boolean) this.checkToken(token.getToken(), false);
        if (valido) {
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
        } else {
            resp.setCode(400);
            resp.setMessage("ERROR: Token inválido");
            resp.setStatus("Error");
        }
        return resp;
    }
}