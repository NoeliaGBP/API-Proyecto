package mx.edu.utez.controllers;

import mx.edu.utez.response.MyResponse;
import mx.edu.utez.tipomenu.model.TipoMenu;
import mx.edu.utez.tipomenu.model.TipoMenuDao;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.List;

@Path("/tipoMenu")
public class ServicioTipoMenu {

    @GET
    @Path("/tipoMenu")
    @Produces(MediaType.APPLICATION_JSON)
    public MyResponse getTiposMenu(){
        MyResponse response = new MyResponse();
        List<TipoMenu> tiposMenu = (new TipoMenuDao().getTiposMenu());
        response.setData(tiposMenu);
        if(tiposMenu.size() > 0){
            response.setStatus("success");
            response.setCode(200);
            response.setMessage("Se recuperaron con exito los tiposDia");
        }else{
            response.setStatus("error");
            response.setCode(400);
            response.setMessage("No se encontraron registros");
        }
        return response;
    }

    @GET
    @Path("/tipoMenu/{idTipoMenu}")
    @Produces(MediaType.APPLICATION_JSON)
    public MyResponse getTipoMenu(@PathParam("idTipoMenu") int idTipoMenu){
        MyResponse response = new MyResponse();
        TipoMenu tipoMenu = (new TipoMenuDao().getTipoMenuById(idTipoMenu));
        response.setData(tipoMenu);
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
    @Path("/tipoMenu")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public MyResponse createTipoMenu(TipoMenu tipoMenu) throws SQLException {
        MyResponse response = new MyResponse();
        TipoMenu tipoMenuR = (new TipoMenuDao().createTipoMenu(tipoMenu));
        response.setData(tipoMenuR);
        if(tipoMenuR.getIdTipoMenu() != 0){
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
    @Path("/tipoMenu")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public MyResponse updateTipoMenu(TipoMenu tipoMenu) throws SQLException{
        MyResponse response = new MyResponse();
        boolean resp = (new TipoMenuDao().updateTipoMenu(tipoMenu));
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
    @Path("/tipoMenu/{idTipoMenu}")
    @Produces(MediaType.APPLICATION_JSON)
    public MyResponse deleteTipoMenu(@PathParam("idTipoMenu") int idTipoMenu) throws SQLException{
        MyResponse response = new MyResponse();
        boolean x = (new TipoMenuDao().deleteTipoMenu(idTipoMenu));
        response.setData(x);
        if(x){
            response.setStatus("success");
            response.setCode(200);
            response.setMessage("Se eliminó el registro exitosamente");
        }else{
            response.setStatus("error");
            response.setCode(400);
            response.setMessage("Hubo fallas al eliminar el tipoMenu");
        }
        return response;
    }

}

