package mx.edu.utez.controllers;

import mx.edu.utez.preparacion.model.Preparacion;
import mx.edu.utez.preparacion.model.PreparacionDao;
import mx.edu.utez.response.MyResponse;
import mx.edu.utez.unidadMedida.model.UnidadMedida;
import mx.edu.utez.unidadMedida.model.UnidadMedidaDao;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.List;

@Path("/daily")
public class ServicePreparacion {

    @GET
    @Path("/preparaciones")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public MyResponse readtPreparacion() throws SQLException {
        MyResponse response = new MyResponse();
        List list = (new PreparacionDao().readPreparacion());
        if(list.size() > 0){
            response.setCode(200);
            response.setStatus("success");
            response.setMessage("READ PREPARACION");
            response.setData(list);
        }else{
            response.setCode(400);
            response.setStatus("error");
            response.setMessage("ERROR READ PREPARACION");
            response.setData(null);
        }
        return response;
    }

    @GET
    @Path("/preparaciones/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public MyResponse getPreparacion(@PathParam("id") int id) throws SQLException{
        MyResponse response = new MyResponse();
        Preparacion preparacion = (new PreparacionDao().readPreparacionById(id));

        if(preparacion.getIdPreparacion()> 0){
            response.setCode(200);
            response.setStatus("success");
            response.setMessage("READ PREPARACION BY ID");
            response.setData(preparacion);
        }else{
            response.setCode(400);
            response.setStatus("error");
            response.setMessage("ERROR READ PREPARACION BY ID");
            response.setData(null);
        }

        return response;
    }

    @POST
    @Path("/preparaciones")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public MyResponse createPreparacion(Preparacion preparacion ) throws SQLException{
        MyResponse response = new MyResponse();

        Preparacion preparacionInsert = (new PreparacionDao().createPreparacion(preparacion));

        if(preparacionInsert.getIdPreparacion() != 0){
            response.setCode(200);
            response.setStatus("success");
            response.setMessage("PREPARACION CREATED");
            response.setData(preparacionInsert);
        }else{
            response.setCode(400);
            response.setStatus("error");
            response.setMessage("ERROR PREPARACION CREATED");
            response.setData(null);
        }

        return response;
    }

    @PUT
    @Path("/preparaciones")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public MyResponse updatePreparacion (Preparacion preparacion) throws SQLException{
        MyResponse response = new MyResponse();

        boolean flag = (new PreparacionDao().updatePreparacion(preparacion));

        if(flag){
            response.setCode(200);
            response.setStatus("success");
            response.setMessage("PREPARACION UPDATE");
            response.setData(preparacion);
        }else{
            response.setCode(400);
            response.setStatus("error");
            response.setMessage("ERROR PREPARACION UPDATE");
            response.setData(null);
        }

        return response;
    }

    @DELETE
    @Path("/preparaciones/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public MyResponse deletePreparacion(@PathParam("id") int id) throws SQLException{
        MyResponse response = new MyResponse();

        boolean flag = (new PreparacionDao().deletePreparacion(id));

        if(flag){
            response.setCode(200);
            response.setStatus("success");
            response.setMessage("PREPARACION DELETE");
            response.setData(flag);
        }else{
            response.setCode(400);
            response.setStatus("error");
            response.setMessage("ERROR PREPARACION DELETE");
            response.setData(null);
        }

        return response;
    }
}
