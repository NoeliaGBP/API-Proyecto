package mx.edu.utez.controllers;

import mx.edu.utez.contacto.model.Contacto;
import mx.edu.utez.contacto.model.ContactoDAO;
import mx.edu.utez.ingrediente.model.IngredienteDao;
import mx.edu.utez.ingredientePlatillo.model.IngredientePlatillo;
import mx.edu.utez.ingredientePlatillo.model.IngredientePlatilloDao;
import mx.edu.utez.response.MyResponse;
import mx.edu.utez.tipoPlatillo.model.TipoPlatilloDao;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.List;

@Path("/daily")
public class ServiceIngredientePlatillo {

    @GET
    @Path("/ingredientes.platillo")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public MyResponse readIngredientePlatillo() throws SQLException {
        MyResponse response = new MyResponse();
        List list = (new IngredientePlatilloDao().getIngredientePlatillo());
        if(list.size() > 0){
            response.setCode(200);
            response.setStatus("success");
            response.setMessage("READ INGREDIENTE PLATILLOS");
            response.setData(list);
        }else{
            response.setCode(400);
            response.setStatus("error");
            response.setMessage("ERROR READ INGREDIENTE PLATILLOS");
            response.setData(null);
        }
        return response;
    }

    @GET
    @Path("/ingredientes.platillo/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public MyResponse readIngredientesByPlatillo(@PathParam("id") int id) throws SQLException {
        MyResponse response = new MyResponse();
        List list = (new IngredientePlatilloDao().getIngredientesByPlatillo(id));
        if(list.size() > 0){
            response.setCode(200);
            response.setStatus("success");
            response.setMessage("READ INGREDIENTES PLATILLO");
            response.setData(list);
        }else{
            response.setCode(400);
            response.setStatus("error");
            response.setMessage("ERROR READ INGREDIENTES PLATILLO");
            response.setData(null);
        }
        return response;
    }

    @POST
    @Path("/ingredientes.platillo")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public MyResponse createIngredientePlatillo(IngredientePlatillo ingredientePlatillo) throws SQLException {
        MyResponse resp = new MyResponse();

        boolean creado = (new IngredientePlatilloDao().createIngredientePlatillo(ingredientePlatillo));
        if (creado) {
            resp.setCode(200);
            resp.setMessage("Ingrediente Platillo creado");
            resp.setStatus("success");
            resp.setData(ingredientePlatillo);
        } else {
            resp.setCode(400);
            resp.setMessage("Error, no se creó.");
            resp.setStatus("error");
        }
        return resp;
    }

    @PUT
    @Path("/ingredientes.platillo")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public MyResponse updateIngredientePlatillo(IngredientePlatillo ingredientePlatillo) throws SQLException {
        MyResponse resp = new MyResponse();

        boolean creado = (new IngredientePlatilloDao().updateIngredientePlatillo(ingredientePlatillo));
        if (creado) {
            resp.setCode(200);
            resp.setMessage("Ingrediente Platillo  actualizado");
            resp.setStatus("success");
            resp.setData(creado);
        } else {
            resp.setCode(400);
            resp.setMessage("Error, no se actualizó.");
            resp.setStatus("error");
        }
        return resp;
    }

    @DELETE
    @Path("/ingredientes.platillo")
    @Produces(MediaType.APPLICATION_JSON)
    public MyResponse deleteIngredientePlatillo(IngredientePlatillo ingredientePlatillo) throws SQLException {
        MyResponse resp = new MyResponse();

        boolean eliminado = (new IngredientePlatilloDao().deleteIngredientePlatillo(ingredientePlatillo));
        if (!eliminado) {
            resp.setCode(400);
            resp.setMessage("Error, no se eliminó");
            resp.setStatus("error");
        } else {
            resp.setCode(200);
            resp.setMessage("Ingrediente Platillo eliminado");
            resp.setStatus("success");
        }
        return resp;
    }

}
