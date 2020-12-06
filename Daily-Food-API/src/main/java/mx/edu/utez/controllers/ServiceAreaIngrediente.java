package mx.edu.utez.controllers;

import mx.edu.utez.areaingrediente.model.AreaIngrediente;
import mx.edu.utez.areaingrediente.model.AreaIngredienteDao;
import mx.edu.utez.ingrediente.model.Ingrediente;
import mx.edu.utez.ingrediente.model.IngredienteDao;
import mx.edu.utez.response.MyResponse;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.List;

@Path("/daily")
public class ServiceAreaIngrediente {

    @GET
    @Path("/areas.ingredientes")
    @Produces(MediaType.APPLICATION_JSON)
    public MyResponse readAreaIngrediente() throws SQLException {
        MyResponse response = new MyResponse();
        AreaIngredienteDao areaIngredienteDao =  new AreaIngredienteDao();
        List list = areaIngredienteDao.getAreaIngrediente();

        if(list.size() > 0){
            response.setCode(200);
            response.setStatus("success");
            response.setMessage("Read Area Ingredientes");
            response.setData(list);
        }else{
            response.setCode(400);
            response.setStatus("error");
            response.setMessage("No hay registros en Area Ingredientes");
            response.setData(null);
        }

        return response;
    }

    @GET
    @Path("/areas.ingredientes/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public MyResponse readAreaIngredienteById(@PathParam("id") int id) throws SQLException{
        MyResponse response =  new MyResponse();

        AreaIngrediente areaIngrediente = (new AreaIngredienteDao().getAreaIngredienteById(id));

        if(areaIngrediente.getId() > 0){
            response.setCode(200);
            response.setStatus("success");
            response.setMessage("Area Ingrediente Encontrada");
            response.setData(areaIngrediente);
        }else{
            response.setCode(400);
            response.setStatus("error");
            response.setMessage("Area Ingrediente No Encontrada");
            response.setData(null);
        }

        return response;
    }


    @POST
    @Path("/areas.ingredientes")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public MyResponse createAreaIngrediente(AreaIngrediente areaIngrediente) throws SQLException{
        MyResponse response = new MyResponse();
        AreaIngrediente areaIngredienteInsert = (new AreaIngredienteDao().createAreaIngrediente(areaIngrediente));

        if (areaIngredienteInsert.getId() != 0){
            response.setCode(200);
            response.setStatus("success");
            response.setMessage("Area Ingrediente Create");
            response.setData(areaIngredienteInsert);
        }else{
            response.setCode(400);
            response.setStatus("error");
            response.setMessage("Error Area Ingrediente Not Create");
            response.setData(null);
        }
        return response;
    }

    @PUT
    @Path("/areas.ingredientes")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public MyResponse updateAreaIngrediente(AreaIngrediente areaIngrediente) throws SQLException{
        MyResponse response = new MyResponse();
        boolean flag = (new AreaIngredienteDao().updateAreaIngrediente(areaIngrediente));

        if(flag){
            response.setCode(200);
            response.setStatus("success");
            response.setMessage("Area Ingrediente Update");
            response.setData(areaIngrediente);
        }else{
            response.setCode(400);
            response.setStatus("error");
            response.setMessage("Error Area Ingrediente Not Update");
            response.setData(null);
        }

        return response;
    }

    @DELETE
    @Path("/areas.ingrediente/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public MyResponse deleteAreaIngrediente(@PathParam("id") int id) throws SQLException {
        MyResponse response = new MyResponse();

        boolean flag = (new AreaIngredienteDao().deleteAreaIngrediente(id));

        if(flag){
            response.setCode(200);
            response.setStatus("success");
            response.setMessage("Area Ingrediente Delete");
        }else{
            response.setCode(400);
            response.setStatus("error");
            response.setMessage("Error Ingrediente Delete");
        }

        return response;
    }
}
