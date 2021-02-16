package mx.edu.utez.controllers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import mx.edu.utez.persona.model.request.MyRequestPersona;
import mx.edu.utez.request.MyToken;
import mx.edu.utez.response.MyResponse;
import mx.edu.utez.persona.model.Persona;
import mx.edu.utez.persona.model.PersonaDAO;
import mx.edu.utez.rol.model.Rol;
import mx.edu.utez.usuario.model.Usuario;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.List;

@Path("/daily")
public class ServicePersona {
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
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            return false;
        }
    }

    @GET
    @Path("/personas")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public MyResponse getPersonas(MyToken token) {
        MyResponse resp = new MyResponse();
        boolean valido = (boolean) this.checkToken(token.getToken(), false);
        if (valido) {
            List<Persona> list = (new PersonaDAO()).getPersonas();
            Usuario validado = (Usuario) this.checkToken(token.getToken(), true);
            if (validado.getIdRol().getNombreRol().equals("Administrador")) {
                if (list.size() != 0) {
                    resp.setCode(200);
                    resp.setMessage("Personas");
                    resp.setStatus("success");
                    resp.setData(list);
                } else {
                    resp.setCode(400);
                    resp.setMessage("Error");
                    resp.setStatus("error");
                }
            } else {
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
    @Path("/personas/coincidencias")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public MyResponse getPersona(MyRequestPersona myRequestPersona) {
        MyResponse resp = new MyResponse();
        boolean valido = (boolean) this.checkToken(myRequestPersona.getToken().getToken(), false);
        if (valido) {
            List<Persona> list = (new PersonaDAO()).getPersona(myRequestPersona.getPersona());
            if (list.size() != 0) {
                resp.setCode(200);
                resp.setMessage("Personas");
                resp.setStatus("success");
                resp.setData(list);
            } else {
                resp.setCode(400);
                resp.setMessage("Error");
                resp.setStatus("error");
            }
        } else {
            resp.setCode(400);
            resp.setMessage("ERROR: Token inválido");
            resp.setStatus("Error");
        }
        return resp;
    }

    @GET
    @Path("/personas/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public MyResponse getPersonaById(@PathParam("id") int id, MyToken token) {
        MyResponse resp = new MyResponse();
        boolean valido = (boolean) this.checkToken(token.getToken(), false);
        if (valido) {
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
        } else {
            resp.setCode(400);
            resp.setMessage("ERROR: Token inválido");
            resp.setStatus("Error");
        }
        return resp;
    }

    @POST
    @Path("/personas")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public MyResponse createPersona(MyRequestPersona myRequestPersona) throws SQLException {
        MyResponse resp = new MyResponse();
        boolean valido = (boolean) this.checkToken(myRequestPersona.getToken().getToken(), false);
        if (valido) {
            Persona contacto2 = (new PersonaDAO()).createPersona(myRequestPersona.getPersona());
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
        } else {
            resp.setCode(400);
            resp.setMessage("ERROR: Token inválido");
            resp.setStatus("Error");
        }
        return resp;
    }

    @PUT
    @Path("/personas")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public MyResponse updatePersona(MyRequestPersona myRequestPersona) throws SQLException {
        MyResponse resp = new MyResponse();
        boolean valido = (boolean) this.checkToken(myRequestPersona.getToken().getToken(), false);
        if (valido) {
            Persona actualizado = (new PersonaDAO()).updatePersona(myRequestPersona.getPersona());
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
        } else {
            resp.setCode(400);
            resp.setMessage("ERROR: Token inválido");
            resp.setStatus("Error");
        }
        return resp;
    }

    @DELETE
    @Path("/personas/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public MyResponse deletePersona(@PathParam("id") int id, MyToken token) throws SQLException {
        MyResponse resp = new MyResponse();
        boolean valido = (boolean) this.checkToken(token.getToken(), false);
        if (valido) {
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
        } else {
            resp.setCode(400);
            resp.setMessage("ERROR: Token inválido");
            resp.setStatus("Error");
        }
        return resp;
    }
}