package mx.edu.utez.controllers;

import mx.edu.utez.direccion.model.Direccion;
import mx.edu.utez.direccion.model.DireccionDao;
import mx.edu.utez.response.MyResponse;
import mx.edu.utez.sucursal.model.Sucursal;
import mx.edu.utez.sucursal.model.SucursalDao;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.List;

@Path("/daily")
public class ServiceSucursal {


    @GET
    @Path("/sucursales")
    @Produces(MediaType.APPLICATION_JSON)
    public MyResponse readSucursal() throws SQLException {
        MyResponse response = new MyResponse();
        List list = (new SucursalDao().getSucursal());

        if (list.size() > 0) {
            response.setCode(200);
            response.setStatus("success");
            response.setMessage("READ LIST SUCURSAL");
            response.setData(list);
        } else {
            response.setCode(400);
            response.setStatus("error");
            response.setMessage("ERROR READ LIST SUCURSAL");
            response.setData(null);
        }

        return response;
    }

    @GET
    @Path("/sucursales/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public MyResponse readSucursalById(@PathParam("id") int id) throws SQLException {
        MyResponse response = new MyResponse();

        Sucursal sucursal = (new SucursalDao().getSucursalById(id));

        if (sucursal.getIdSucursal() > 0) {
            response.setCode(200);
            response.setStatus("success");
            response.setMessage("READ SUCURSAL IS OK");
            response.setData(sucursal);
        } else {
            response.setCode(400);
            response.setStatus("error");
            response.setMessage("ERROR READ SUCURSAL BY ID");
            response.setData(null);
        }

        return response;
    }

    @POST
    @Path("/sucursales")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public MyResponse createSucursal(Sucursal sucursal) throws SQLException {
        MyResponse response = new MyResponse();

        Sucursal sucursalInsert = (new SucursalDao().createSucursal(sucursal));

        if (sucursalInsert.getIdSucursal() > 0) {
            response.setCode(200);
            response.setStatus("success");
            response.setMessage("SUCURSAL CREATED");
            response.setData(sucursal);
        } else {
            response.setCode(400);
            response.setStatus("error");
            response.setMessage("ERROR SUCURSAL CREATED");
            response.setData(null);
        }

        return response;
    }

    @PUT
    @Path("/sucursales")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public MyResponse updateSucursal(Sucursal sucursal) throws SQLException {
        MyResponse response = new MyResponse();

        boolean flag = (new SucursalDao().updateSucursal(sucursal));

        if(flag){
            response.setCode(200);
            response.setStatus("success");
            response.setMessage("SUCURSAL UPDATE");
            response.setData(sucursal);
        }else{
            response.setCode(400);
            response.setStatus("error");
            response.setMessage("SUCURSAL NOT UPDATE");
        }

        return response;
    }

    @DELETE
    @Path("/sucursales/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public MyResponse deleteSucursal(@PathParam("id") int id) throws SQLException {
        MyResponse response = new MyResponse();

        boolean flag = (new SucursalDao().deleteSucursal(id));

        if(flag){
            response.setCode(200);
            response.setStatus("success");
            response.setMessage("SUCURSAL DELETE");
            response.setData(flag);
        }else{
            response.setCode(400);
            response.setStatus("error");
            response.setMessage("SUCURSAL NOT DELETE");
        }

        return response;
    }

}
