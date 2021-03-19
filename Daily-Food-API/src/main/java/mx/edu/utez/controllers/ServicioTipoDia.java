package mx.edu.utez.controllers;

import mx.edu.utez.response.MyResponse;
import mx.edu.utez.tipodia.model.TipoDia;
import mx.edu.utez.tipodia.model.TipoDiaDao;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.List;

@Path("/daily")
public class ServicioTipoDia {

    @GET
    @Path("/tipos.dias")
    @Produces(MediaType.APPLICATION_JSON)
    public MyResponse getTiposDia(){
        MyResponse response = new MyResponse();
        List<TipoDia> tiposDia = (new TipoDiaDao().getTiposDia());
        response.setData(tiposDia);
        response.setStatus("success");
        response.setCode(200);
        if(tiposDia.size() > 0){
            response.setMessage("Se recuperaron con exito los tiposDia");
        }else{
            response.setMessage("No se encontraron registros o algo salio mal");
        }
        return response;
    }

    @GET
    @Path("/tipos.dias/{idTipoDia}")
    @Produces(MediaType.APPLICATION_JSON)
    public MyResponse getTipoDia(@PathParam("idTipoDia") int idTipoDia){
        MyResponse response = new MyResponse();
        TipoDia tipoDia = (new TipoDiaDao().getTipoDiaById(idTipoDia));
        response.setData(tipoDia);
        if(tipoDia.getIdTipoDia()>0){
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
    @Path("/tipos.dias")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public MyResponse createTipoDia(TipoDia tipoDia) throws SQLException {
        MyResponse response = new MyResponse();
        TipoDia tipoDiaR = (new TipoDiaDao().createTipoDia(tipoDia));
        response.setData(tipoDiaR);
        if(tipoDiaR.getIdTipoDia() != 0){
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
    @Path("/tipos.dias")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public MyResponse updateTipoDia(TipoDia tipoDia) throws SQLException{
        MyResponse response = new MyResponse();
        boolean resp = (new TipoDiaDao().updateTipoDia(tipoDia));
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
    @Path("/tipos.dias/{idTipoDia}")
    @Produces(MediaType.APPLICATION_JSON)
    public MyResponse deleteTipoDia(@PathParam("idTipoDia") int idTipoDia) throws SQLException{
        MyResponse response = new MyResponse();
        boolean x = (new TipoDiaDao().deleteTipoDia(idTipoDia));
        response.setData(x);
        if(x){
            response.setStatus("success");
            response.setCode(200);
            response.setMessage("Se eliminó el registro exitosamente");
        }else{
            response.setStatus("error");
            response.setCode(400);
            response.setMessage("Hubo fallas al eliminar el tipoDia");
        }
        return response;
    }

}

