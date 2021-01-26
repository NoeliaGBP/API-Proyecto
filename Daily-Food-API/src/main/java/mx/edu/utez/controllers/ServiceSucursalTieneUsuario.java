package mx.edu.utez.controllers;

import mx.edu.utez.response.MyResponse;
import mx.edu.utez.usuariosucursal.model.SucursalTieneUsuario;
import mx.edu.utez.usuariosucursal.model.SucursalTieneUsuarioDao;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.List;

@Path("/daily")
public class ServiceSucursalTieneUsuario {

    @GET
    @Path("/sucursales.usuarios")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public MyResponse getSucursalTieneUsuario() throws SQLException {
        MyResponse response = new MyResponse();
        List list = (new SucursalTieneUsuarioDao().getSucursalTieneUsuario());

        if (list.size() > 0) {
            response.setCode(200);
            response.setStatus("success");
            response.setMessage("READ LIST USUARIO SUCURSAL");
            response.setData(list);
        } else {
            response.setCode(400);
            response.setStatus("error");
            response.setMessage("ERROR READ LIST USUARIO SUCURSAL");
            response.setData(null);
        }

        return response;
    }

    @GET
    @Path("/sucursales.usuarios/{user}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public MyResponse getSucursalTieneUsuarioByUser(@PathParam("user") String user) throws SQLException {
        MyResponse response = new MyResponse();
        SucursalTieneUsuario object = (new SucursalTieneUsuarioDao().getSucursalTieneUsuarioByUser(user));

        if (object.getNombreUsuario() != null )  {
            response.setCode(200);
            response.setStatus("success");
            response.setMessage("READ USUARIO SUCURSAL");
            response.setData(object);
        } else {
            response.setCode(400);
            response.setStatus("error");
            response.setMessage("ERROR READ USUARIO SUCURSAL");
            response.setData(null);
        }

        return response;
    }

    @POST
    @Path("/sucursales.usuarios")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public MyResponse getSucursalTieneUsuarioByUser(SucursalTieneUsuario object) throws SQLException {
        MyResponse response = new MyResponse();
        boolean flag = (new SucursalTieneUsuarioDao().createSucursalTieneUsuario(object));

        if (flag)  {
            response.setCode(200);
            response.setStatus("success");
            response.setMessage("USUARIO SUCURSAL CREATED");
            response.setData(object);
        } else {
            response.setCode(400);
            response.setStatus("error");
            response.setMessage("ERROR USUARIO SUCURSAL NOT CREATED");
            response.setData(null);
        }

        return response;
    }

    @PUT
    @Path("/sucursales.usuarios")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public MyResponse updateSucursalTieneUsuario(SucursalTieneUsuario object) throws SQLException {
        MyResponse response = new MyResponse();
        boolean flag = (new SucursalTieneUsuarioDao().updateSucursalTieneUsuario(object));

        if (flag)  {
            response.setCode(200);
            response.setStatus("success");
            response.setMessage("USUARIO SUCURSAL UPDATE");
            response.setData(object);
        } else {
            response.setCode(400);
            response.setStatus("error");
            response.setMessage("ERROR USUARIO SUCURSAL NOT UPDATE");
            response.setData(null);
        }

        return response;
    }

    @DELETE
    @Path("/sucursales.usuarios/{user}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public MyResponse updateSucursalTieneUsuario(@PathParam("user") String user) throws SQLException {
        MyResponse response = new MyResponse();
        boolean flag = (new SucursalTieneUsuarioDao().deleteSucursalTieneUsuario(user));

        if (flag)  {
            response.setCode(200);
            response.setStatus("success");
            response.setMessage("USUARIO SUCURSAL DELETE");
            response.setData(flag);
        } else {
            response.setCode(400);
            response.setStatus("error");
            response.setMessage("ERROR USUARIO SUCURSAL NOT DELETE");
            response.setData(null);
        }

        return response;
    }




}
