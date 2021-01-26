package mx.edu.utez.controllers;

import mx.edu.utez.platilloenmenu.model.PlatilloEnMenu;
import mx.edu.utez.platilloenmenu.model.PlatilloEnMenuDao;
import mx.edu.utez.response.MyResponse;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.List;

@Path("/daily")
public class ServicioPlatilloEnMenu {

    @GET
    @Path("/platillos.en.menu")
    @Produces(MediaType.APPLICATION_JSON)
    public MyResponse getPlatillosEnMenu(){
        MyResponse response = new MyResponse();
        List<PlatilloEnMenu> platillosEnMenu = (new PlatilloEnMenuDao().getPlatillosEnMenu());
        response.setData(platillosEnMenu);
        if(platillosEnMenu.size() > 0){
            response.setStatus("success");
            response.setCode(200);
            response.setMessage("Se recuperaron con exito los tiposDia");
        }else{
            response.setStatus("error");
            response.setCode(400);
            response.setMessage("No se encontraron registros o algo salio mal");
        }
        return response;
    }

    @GET
    @Path("/platillos.en.menu/{idPlatilloMenu}")
    @Produces(MediaType.APPLICATION_JSON)
    public MyResponse getPlatilloEnMenu(@PathParam("idPlatilloMenu") int idPlatilloEnMenu){
        MyResponse response = new MyResponse();
        PlatilloEnMenu platilloMenu = (new PlatilloEnMenuDao().getPlatilloEnMenuById(idPlatilloEnMenu));
        response.setData(platilloMenu);
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
    @Path("/platillos.en.menu")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public MyResponse createPlatilloEnMenu(PlatilloEnMenu platilloMenu) throws SQLException {
        MyResponse response = new MyResponse();
        PlatilloEnMenu platilloM = (new PlatilloEnMenuDao().createPlatilloEnMenu(platilloMenu));
        response.setData(platilloM);
        if(platilloM.getIdPlatilloMenu() != 0){
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
    @Path("/platillos.en.menu")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public MyResponse updatePlatilloEnMenu(PlatilloEnMenu platilloMenu) throws SQLException{
        MyResponse response = new MyResponse();
        boolean resp = (new PlatilloEnMenuDao().updatePlatilloEnMenu(platilloMenu));
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
    @Path("/platillos.en.menu/{idPlatilloMenu}")
    @Produces(MediaType.APPLICATION_JSON)
    public MyResponse deletePlatilloEnMenu(@PathParam("idPlatilloMenu") int idPlatilloMenu) throws SQLException{
        MyResponse response = new MyResponse();
        boolean x = (new PlatilloEnMenuDao().deletePlatilloEnMenu(idPlatilloMenu));
        response.setData(x);
        if(x){
            response.setStatus("success");
            response.setCode(200);
            response.setMessage("Se eliminó el registro exitosamente");
        }else{
            response.setStatus("error");
            response.setCode(400);
            response.setMessage("Hubo fallas al eliminar el PlatilloEnMenu");
        }
        return response;
    }

}
