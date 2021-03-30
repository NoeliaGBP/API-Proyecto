package mx.edu.utez.controllers;

import mx.edu.utez.persona.model.Persona;
import mx.edu.utez.persona.model.PersonaDAO;
import mx.edu.utez.promocion.model.Promocion;
import mx.edu.utez.promocion.model.PromocionDao;
import mx.edu.utez.response.MyResponse;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.List;

@Path("/daily")
public class ServicePromocion {

    @GET
    @Path("/promociones")
    @Produces(MediaType.APPLICATION_JSON)
    public MyResponse getPersonas() throws SQLException {
        MyResponse resp = new MyResponse();
        List<Promocion> list = (new PromocionDao().readAllPromocion() );
        if (list.size() > 0 ) {
            resp.setCode(200);
            resp.setMessage("Promociones");
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
    @Path("/promociones/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public MyResponse getPromocionById(@PathParam("id") int id) throws  SQLException{
        MyResponse resp = new MyResponse();
        Promocion promocion = (new PromocionDao().readPromocionById(id) );
        if (promocion.getIdPromocion() != 0) {
            resp.setCode(200);
            resp.setMessage("Promoción");
            resp.setStatus("success");
            resp.setData(promocion);
        } else {
            resp.setCode(400);
            resp.setMessage("No existe promoción");
            resp.setStatus("error");
        }
        return resp;
    }

    @POST
    @Path("/promociones")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public MyResponse createPromocion(Promocion promocion) throws SQLException {
        MyResponse resp = new MyResponse();

        Promocion promocionInsert = (new PromocionDao().createPromocion(promocion) );

        if (promocionInsert.getIdPromocion()  != 0) {
            resp.setCode(200);
            resp.setMessage("Promoción creada");
            resp.setStatus("success");
            resp.setData(promocionInsert);
        } else {
            resp.setCode(400);
            resp.setMessage("Error, no se creó.");
            resp.setStatus("error");
        }

        return resp;
    }

    @PUT
    @Path("/promociones")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public MyResponse updatePromocion(Promocion promocion) throws SQLException {
        MyResponse resp = new MyResponse();

        boolean flag = (new PromocionDao().updatePromocion(promocion));

        if (flag) {
            resp.setCode(200);
            resp.setMessage("Promoción actualizada");
            resp.setStatus("success");
            resp.setData(promocion);
        } else {
            resp.setCode(400);
            resp.setMessage("Error, no se actualizó.");
            resp.setStatus("error");
        }
        return resp;
    }

    @DELETE
    @Path("/promociones/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public MyResponse deletePromocion(@PathParam("id") int id) throws SQLException {
        MyResponse resp = new MyResponse();

        boolean borrado = (new PromocionDao().deletePromocion(id) ) ;
        if (!borrado) {
            resp.setCode(400);
            resp.setMessage("Error, no se eliminó");
            resp.setStatus("error");
        } else {
            resp.setCode(200);
            resp.setMessage("Promocion eliminada");
            resp.setStatus("success");

        }
        return resp;
    }

}
