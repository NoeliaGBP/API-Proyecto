package mx.edu.utez.controllers;

import mx.edu.utez.dia.model.Dia;
import mx.edu.utez.dia.model.DiaDao;
import mx.edu.utez.response.MyResponse;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.List;

@Path("/daily")
public class ServicioDia {

    @GET
    @Path("/dias")
    @Produces(MediaType.APPLICATION_JSON)
    public MyResponse getDias(){
        MyResponse response = new MyResponse();
        List<Dia> dias = (new DiaDao().getDias());
        response.setData(dias);
        if(dias.size() > 0){
            response.setStatus("success");
            response.setCode(200);
            response.setMessage("Se recuperaron con exito los Días");
        }else{
            response.setStatus("error");
            response.setCode(400);
            response.setMessage("No se encontraron registros");
        }
        return response;
    }

    @GET
    @Path("/dias/{idDia}")
    @Produces(MediaType.APPLICATION_JSON)
    public MyResponse getDia(@PathParam("idDia") int idDia){
        MyResponse response = new MyResponse();
        Dia dia = (new DiaDao().getDiaById(idDia));
        response.setData(dia);
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
    @Path("/dias")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public MyResponse createDia(Dia dia) throws SQLException {
        MyResponse response = new MyResponse();
        Dia diaR = (new DiaDao().createDia(dia));
        response.setData(diaR);
        if(diaR.getIdDia() != 0){
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
    @Path("/dias")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public MyResponse updateDia(Dia diaNew) throws SQLException{
        MyResponse response = new MyResponse();
        boolean resp = (new DiaDao().updateDia(diaNew));
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
    @Path("/dias/{idDia}")
    @Produces(MediaType.APPLICATION_JSON)
    public MyResponse deleteDia(@PathParam("idDia") int idDia) throws SQLException{
        MyResponse response = new MyResponse();
        boolean x = (new DiaDao().deleteDia(idDia));
        response.setData(x);
        if(x){
            response.setStatus("success");
            response.setCode(200);
            response.setMessage("Se eliminó el registro exitosamente");
        }else{
            response.setStatus("error");
            response.setCode(400);
            response.setMessage("Hubo fallas al eliminar el Dia");
         }
        return response;
    }

}

