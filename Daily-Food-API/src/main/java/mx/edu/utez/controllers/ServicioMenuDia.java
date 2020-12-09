package mx.edu.utez.controllers;

import mx.edu.utez.menudia.model.MenuDia;
import mx.edu.utez.menudia.model.MenuDiaDao;
import mx.edu.utez.response.MyResponse;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.List;

@Path("/menuDia")
public class ServicioMenuDia {

    @GET
    @Path("/menuDia")
    @Produces(MediaType.APPLICATION_JSON)
    public MyResponse getMenusDia(){
        MyResponse response = new MyResponse();
        List<MenuDia> menusDia = (new MenuDiaDao().getMenusDia());
        response.setData(menusDia);
        if(menusDia.size() > 0){
            response.setStatus("success");
            response.setCode(200);
            response.setMessage("Se recuperaron con exito los menusDia");
        }else{
            response.setStatus("error");
            response.setCode(400);
            response.setMessage("No se encontraron registros");
        }
        return response;
    }

    @GET
    @Path("/menuDia/{idMenuDia}")
    @Produces(MediaType.APPLICATION_JSON)
    public MyResponse getMenuDia(@PathParam("idMenuDia") int idMenuD){
        MyResponse response = new MyResponse();
        MenuDia menuDia = (new MenuDiaDao().getMenuDiaById(idMenuD));
        response.setData(menuDia);
        if(response.getData() != null){
            response.setStatus("success");
            response.setCode(200);
            response.setMessage("Se realizo la consulta con éxito");
        }else{
            response.setStatus("error");
            response.setCode(400);
            response.setMessage("No se encontró registro con tal id");
        }
        return response;
    }

    @POST
    @Path("/menuDia")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public MyResponse createMenuDia(MenuDia menuDia) throws SQLException {
        MyResponse response = new MyResponse();
        MenuDia menuDiaR = (new MenuDiaDao().createMenuDia(menuDia));
        response.setData(menuDiaR);
        if(menuDiaR.getIdMenuDia() != 0){
            response.setStatus("success");
            response.setCode(200);
            response.setMessage("Se insertó correctamente");
        }else{
            response.setStatus("error");
            response.setCode(400);
            response.setMessage("Error en la inserción");
        }
        return response;
    }

    @PUT
    @Path("/menuDia")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public MyResponse updateMenuDia(MenuDia menuDiaNew) throws SQLException{
        MyResponse response = new MyResponse();
        boolean resp = (new MenuDiaDao().updateMenuDia(menuDiaNew));
        response.setData(resp);
        if(resp){
            response.setStatus("success");
            response.setCode(200);
            response.setMessage("Se actualizó correctamente");
        }else{
            response.setStatus("error");
            response.setCode(400);
            response.setMessage("Error o falla en actualización");
        }
        return response;
    }

    @DELETE
    @Path("/menuDia/{idMenuDia}")
    @Produces(MediaType.APPLICATION_JSON)
    public MyResponse deleteMenuDia(@PathParam("idMenuDia") int idMenuDia) throws SQLException{
        MyResponse response = new MyResponse();
        boolean x = (new MenuDiaDao().deleteMenuDia(idMenuDia));
        response.setData(x);
        if(x){
            response.setStatus("success");
            response.setCode(200);
            response.setMessage("Se eliminó el registro exitosamente");
        }else{
            response.setStatus("error");
            response.setCode(400);
            response.setMessage("Hubo fallas al eliminar el MenuDia");
        }
        return response;
    }

}

