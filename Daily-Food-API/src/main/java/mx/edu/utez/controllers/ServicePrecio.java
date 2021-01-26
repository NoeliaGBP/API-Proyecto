package mx.edu.utez.controllers;


import mx.edu.utez.precio.model.Precio;
import mx.edu.utez.precio.model.PrecioDao;
import mx.edu.utez.response.MyResponse;
import mx.edu.utez.sucursal.model.Sucursal;
import mx.edu.utez.sucursal.model.SucursalDao;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.List;

@Path("/daily")
public class ServicePrecio {

    @GET
    @Path("/precios")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType .APPLICATION_JSON)
    public MyResponse readPrecios() throws SQLException{

        MyResponse response = new MyResponse();

        List list = (new PrecioDao().readPrecios());

        if(list.size() > 0){
            response.setCode(200);
            response.setStatus("success");
            response.setMessage("READ PRECIOS");
            response.setData(list);
        }else{
            response.setCode(400);
            response.setStatus("error");
            response.setMessage("ERROR READ PRECIOS");
            response.setData(null);
        }

        return response;
    }

    @POST
    @Path("/precios")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public MyResponse createPrecio(Precio precio) throws SQLException{

        MyResponse response = new MyResponse();

        Precio precioCreated = (new PrecioDao().createPrecio(precio));

        if(precioCreated.getIdPrecio() != 0){
            response.setCode(200);
            response.setStatus("success");
            response.setMessage("CREATED PRECIOS");
            response.setData(precioCreated);
        }else{
            response.setCode(400);
            response.setStatus("error");
            response.setMessage("ERROR CREATED PRECIOS");
            response.setData(null);
        }

        return response;
    }

    @PUT
    @Path("/precios")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public MyResponse updatePrecio (Precio precio) throws SQLException {
        MyResponse response = new MyResponse();

        boolean flag = (new PrecioDao().updatePrecio(precio));

        if(flag){
            response.setCode(200);
            response.setStatus("success");
            response.setMessage("PRECIOS UPDATE");
            response.setData(precio);
        }else{
            response.setCode(400);
            response.setStatus("error");
            response.setMessage("PRECIOS NOT UPDATE");
        }

        return response;
    }

    @DELETE
    @Path("/precios/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public MyResponse deletePrecio (@PathParam("id") int id) throws SQLException {
        MyResponse response = new MyResponse();

        boolean flag = (new PrecioDao().deletePrecio(id));

        if(flag){
            response.setCode(200);
            response.setStatus("success");
            response.setMessage("PRECIOS DELETE");
            response.setData(flag);
        }else{
            response.setCode(400);
            response.setStatus("error");
            response.setMessage("PRECIOS NOT DELETE");
        }

        return response;
    }

}
