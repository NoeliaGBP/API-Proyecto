package mx.edu.utez.controllers;

import mx.edu.utez.ponderacion.model.Ponderacion;
import mx.edu.utez.ponderacion.model.PonderacionDao;
import mx.edu.utez.response.MyResponse;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.List;

@Path("/daily")
public class ServicePonderacion {

    @GET
    @Path("/ponderaciones")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public MyResponse getPonderacion() throws SQLException {
        MyResponse response = new MyResponse();
        List list = (new PonderacionDao().getPonderacion());
        if(list.size() > 0){
            response.setCode(200);
            response.setStatus("success");
            response.setMessage("READ PONDERACIONES");
            response.setData(list);
        }else{
            response.setCode(400);
            response.setStatus("error");
            response.setMessage("ERROR PONDERACIONES READ");
            response.setData(null);
        }
        return response;
    }

    @GET
    @Path("/ponderaciones/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public MyResponse getPonderacionById(@PathParam("id") int id) throws SQLException{
        MyResponse response = new MyResponse();
        Ponderacion ponderacion = (new PonderacionDao().getPonderacionById(id));

        if(ponderacion.getId() != 0){
            response.setCode(200);
            response.setStatus("success");
            response.setMessage("READ PONDERACION BY ID");
            response.setData(ponderacion);
        }else{
            response.setCode(400);
            response.setStatus("error");
            response.setMessage("ERROR READ PONDERACION BY ID");
            response.setData(null);
        }

        return response;
    }

    @POST
    @Path("/ponderaciones")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public MyResponse createPonderacion(Ponderacion ponderacion) throws SQLException{
        MyResponse response = new MyResponse();

        Ponderacion ponderacionInsert = (new PonderacionDao().createPonderacion(ponderacion));

        if(ponderacionInsert.getId() != 0){
            response.setCode(200);
            response.setStatus("success");
            response.setMessage("PONDERACION CREATED");
            response.setData(ponderacionInsert);
        }else{
            response.setCode(400);
            response.setStatus("error");
            response.setMessage("ERROR PONDERACION CREATED");
            response.setData(null);
        }

        return response;
    }

    @PUT
    @Path("/ponderaciones")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public MyResponse updatePonderacion (Ponderacion ponderacion) throws SQLException{
        MyResponse response = new MyResponse();

        boolean flag = (new PonderacionDao().updatePonderacion(ponderacion));

        if(flag){
            response.setCode(200);
            response.setStatus("success");
            response.setMessage("PONDERACION UPDATE");
            response.setData(ponderacion);
        }else{
            response.setCode(400);
            response.setStatus("error");
            response.setMessage("ERROR PONDERACION UPDATE");
            response.setData(null);
        }

        return response;
    }

    @DELETE
    @Path("/ponderaciones/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public MyResponse deletePonderacion(@PathParam("id") int id) throws SQLException{
        MyResponse response = new MyResponse();

        boolean flag = (new PonderacionDao().deletePonderacion(id));

        if(flag){
            response.setCode(200);
            response.setStatus("success");
            response.setMessage("PONDERACION DELETE");
            response.setData(flag);
        }else{
            response.setCode(400);
            response.setStatus("error");
            response.setMessage("ERROR PONDERACION DELETE");
            response.setData(null);
        }

        return response;
    }

}
