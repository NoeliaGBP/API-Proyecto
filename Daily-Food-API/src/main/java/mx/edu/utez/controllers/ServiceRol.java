package mx.edu.utez.controllers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import mx.edu.utez.persona.model.Persona;
import mx.edu.utez.request.MyToken;
import mx.edu.utez.response.MyResponse;
import mx.edu.utez.rol.model.Rol;
import mx.edu.utez.rol.model.RolDAO;
import mx.edu.utez.rol.model.request.MyRequestRol;
import mx.edu.utez.usuario.model.Usuario;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.List;

@Path("/daily")
public class ServiceRol {
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
    @Path("/roles")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public MyResponse getRoles(MyToken token) {
        MyResponse resp = new MyResponse();
        boolean valido = (boolean) this.checkToken(token.getToken(), false);
        if (valido) {
            Usuario validado = (Usuario) this.checkToken(token.getToken(), true);
            if(validado.getIdRol().getNombreRol().equals("Administrador")) {
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
            }else{
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
    @Path("/roles/coincidencia")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public MyResponse getRol(MyRequestRol requestRol) {
        MyResponse resp = new MyResponse();
        boolean valido = (boolean) this.checkToken(requestRol.getToken().getToken(), false);
        if (valido) {
            List<Rol> list = (new RolDAO()).getRol(requestRol.getRol().getNombreRol());
            if (list.size() != 0) {
                resp.setCode(200);
                resp.setMessage("Roles");
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
    @Path("/roles/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public MyResponse getRolById(@PathParam("id") int id, MyToken token) {
        MyResponse resp = new MyResponse();
        boolean valido = (boolean) this.checkToken(token.getToken(), false);
        if (valido) {
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
        } else {
            resp.setCode(400);
            resp.setMessage("ERROR: Token inválido");
            resp.setStatus("Error");
        }
        return resp;
    }

    @POST
    @Path("/roles")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public MyResponse createRol(MyRequestRol requestRol) throws SQLException {
        MyResponse resp = new MyResponse();
        boolean valido = (boolean) this.checkToken(requestRol.getToken().getToken(), false);
        if (valido) {
            Rol creado = (new RolDAO()).createRol(requestRol.getRol());
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
        } else {
            resp.setCode(400);
            resp.setMessage("ERROR: Token inválido");
            resp.setStatus("Error");
        }
        return resp;
    }

    @PUT
    @Path("/roles")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public MyResponse updateRol(MyRequestRol requestRol) throws SQLException {
        MyResponse resp = new MyResponse();
        boolean valido = (boolean) this.checkToken(requestRol.getToken().getToken(), false);
        if (valido) {
            Rol actualizado = (new RolDAO()).updateRol(requestRol.getRol());
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
        } else {
            resp.setCode(400);
            resp.setMessage("ERROR: Token inválido");
            resp.setStatus("Error");
        }
        return resp;
    }

    @DELETE
    @Path("/roles/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public MyResponse deleteRol(@PathParam("id") int id, MyToken token) throws SQLException {
        MyResponse resp = new MyResponse();
        boolean valido = (boolean) this.checkToken(token.getToken(), false);
        if (valido) {
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
        } else {
            resp.setCode(400);
            resp.setMessage("ERROR: Token inválido");
            resp.setStatus("Error");
        }
        return resp;
    }
}