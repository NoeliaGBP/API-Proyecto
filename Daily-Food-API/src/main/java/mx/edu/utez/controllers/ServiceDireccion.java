package mx.edu.utez.controllers;

import mx.edu.utez.direccion.model.Direccion;
import mx.edu.utez.direccion.model.DireccionDao;
import mx.edu.utez.response.MyResponse;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.List;

@Path("/daily")
public class ServiceDireccion {


    @GET
    @Path("/direcciones")
    @Produces(MediaType.APPLICATION_JSON)
    public MyResponse readDireccion() throws SQLException {
        MyResponse response = new MyResponse();
        List list = (new DireccionDao().getDireccion());

        if (list.size() > 0) {
            response.setCode(200);
            response.setStatus("success");
            response.setMessage("READ LIST DIRECCION");
            response.setData(list);
        } else {
            response.setCode(400);
            response.setStatus("error");
            response.setMessage("ERROR READ LIST DIRECCION");
            response.setData(null);
        }

        return response;
    }

    @GET
    @Path("/direcciones/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public MyResponse readDireccionById(@PathParam("id") int id) throws SQLException {
        MyResponse response = new MyResponse();

        Direccion direccion = (new DireccionDao().getDireccionById(id));

        if (direccion.getId() > 0) {
            response.setCode(200);
            response.setStatus("success");
            response.setMessage("READ DIRECCION IS OK");
            response.setData(direccion);
        } else {
            response.setCode(400);
            response.setStatus("error");
            response.setMessage("ERROR READ DIRECCION BY ID");
            response.setData(null);
        }

        return response;
    }


    @POST
    @Path("/direcciones")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public MyResponse createDireccion(Direccion direccion) throws SQLException {
        MyResponse response = new MyResponse();

        Direccion direccionInsert = (new DireccionDao().createDireccion(direccion));

        if (direccion.getId() > 0) {
            response.setCode(200);
            response.setStatus("success");
            response.setMessage("DIRECCION CREATED");
            response.setData(direccionInsert);
        } else {
            response.setCode(400);
            response.setStatus("error");
            response.setMessage("ERROR DIRECCION CREATED");
            response.setData(null);
        }

        return response;
    }

    @PUT
    @Path("/direcciones")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public MyResponse updateDireccion(Direccion direccion) throws SQLException {
        MyResponse response = new MyResponse();

        boolean flag = (new DireccionDao().updateDireccion(direccion));

        if (flag) {
            response.setCode(200);
            response.setStatus("success");
            response.setMessage("DIRECCION UPDATE");
            response.setData(direccion);
        } else {
            response.setCode(400);
            response.setStatus("error");
            response.setMessage("ERROR DIRECCION UPDATE");
            response.setData(null);
        }

        return response;
    }

    @DELETE
    @Path("/direcciones/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public MyResponse deleteDireccion(@PathParam("id") int id) throws SQLException {
        MyResponse response = new MyResponse();

        boolean flag = (new DireccionDao().deleteDireccion(id));

        if (flag) {
            response.setCode(200);
            response.setStatus("success");
            response.setMessage("DIRECCION DELETED");
            response.setData(flag);
        } else {
            response.setCode(400);
            response.setStatus("error");
            response.setMessage("ERROR DIRECCION DELETED");
            response.setData(null);
        }

        return response;
    }


}
