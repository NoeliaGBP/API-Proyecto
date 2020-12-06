package mx.edu.utez.controllers;

import mx.edu.utez.ingrediente.model.Ingrediente;
import mx.edu.utez.ingrediente.model.IngredienteDao;
import mx.edu.utez.response.MyResponse;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.List;

@Path("/daily")
public class ServiceIngrediente {


    @GET
    @Path("/ingredientes")
    @Produces(MediaType.APPLICATION_JSON)
    public MyResponse readIngrediente() throws SQLException {

        MyResponse resp = new MyResponse();

        IngredienteDao ingredienteDao = new IngredienteDao();
        List<Ingrediente> list = (ingredienteDao.getIngrediente());
        resp.setCode(200);
        resp.setStatus("success");
        resp.setMessage("Read Ingredientes");
        resp.setData(list);

        return resp;
    }

    @GET
    @Path("/ingredientes/{code}")
    @Produces(MediaType.APPLICATION_JSON)
    public Ingrediente readIngredienteById(@PathParam("code") int code) throws SQLException{
        Ingrediente ingrediente = (new IngredienteDao().getIngredienteById(code));

        return ingrediente;
    }

    @POST
    @Path("/ingredientes")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public MyResponse createIngrediente(Ingrediente ingrediente) throws SQLException{
        MyResponse response = new MyResponse();
        Ingrediente ingredienteInsert = (new IngredienteDao().createIngrediente(ingrediente));
        if(ingredienteInsert.getIdIngrediente() > 0){
            response.setCode(200);
            response.setStatus("success");
            response.setMessage("Ingrediente create");
            response.setData(ingredienteInsert);
        }else{
            response.setCode(400);
            response.setStatus("error");
            response.setMessage("Error Ingrediente Not Create");
            response.setData(null);
        }
        return response;
    }

    @PUT
    @Path("/ingredientes")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public MyResponse updateIngrediente(Ingrediente ingrediente) throws SQLException{
        MyResponse response = new MyResponse();

        boolean flag  = (new IngredienteDao().updateIngrediente(ingrediente));

        //Validar si mi Ingrediente fue actualizado
        if(flag){
            response.setCode(200);
            response.setStatus("success");
            response.setMessage("Ingrediente update");
            response.setData(ingrediente);
        }else{
            response.setCode(400);
            response.setStatus("error");
            response.setMessage("Error Ingrediente update");
            response.setData(null);
        }

        return response;
    }

    @DELETE
    @Path("/ingredientes/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public MyResponse deleteIngrediente(@PathParam("id") int id) throws SQLException{
        MyResponse response = new MyResponse();
        boolean flag = (new IngredienteDao().deleteIngrediente(id));
        //Validar si mi Ingrediente fue actualizado
        if(flag){
            response.setCode(200);
            response.setStatus("success");
            response.setMessage("Ingrediente Delete");
        }else{
            response.setCode(400);
            response.setStatus("error");
            response.setMessage("Error Ingrediente Delete");
        }

        return response;
    }
}
