package mx.edu.utez.controllers;

import mx.edu.utez.ponderacion.model.Ponderacion;
import mx.edu.utez.ponderacion.model.PonderacionDao;
import mx.edu.utez.response.MyResponse;
import mx.edu.utez.unidadMedida.model.UnidadMedida;
import mx.edu.utez.unidadMedida.model.UnidadMedidaDao;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.List;

@Path("/daily")
public class ServiceUnidadMedida {

    @GET
    @Path("/unidades.medida")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public MyResponse readtUnidadMedida() throws SQLException{
        MyResponse response = new MyResponse();
        List list = (new UnidadMedidaDao().getUnidadMedida());
        if(list.size() > 0){
            response.setCode(200);
            response.setStatus("success");
            response.setMessage("READ UNIDAD MEDIDA");
            response.setData(list);
        }else{
            response.setCode(400);
            response.setStatus("error");
            response.setMessage("ERROR READ UNIDAD MEDIDA");
            response.setData(null);
        }
        return response;
    }

    @GET
    @Path("/unidades.medida/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public MyResponse getUnidadMedida(@PathParam("id") int id) throws SQLException{
        MyResponse response = new MyResponse();
        UnidadMedida unidadMedida = (new UnidadMedidaDao().getUnidadMedidaById(id));

        if(unidadMedida.getIdUnidadMedida() != 0){
            response.setCode(200);
            response.setStatus("success");
            response.setMessage("READ UNIDAD MEDIDA BY ID");
            response.setData(unidadMedida);
        }else{
            response.setCode(400);
            response.setStatus("error");
            response.setMessage("ERROR READ UNIDAD MEDIDA BY ID");
            response.setData(null);
        }

        return response;
    }

    @POST
    @Path("/unidades.medida")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public MyResponse createUnidadMedida(UnidadMedida unidadMedida) throws SQLException{
        MyResponse response = new MyResponse();

        UnidadMedida unidadMedidaInsert = (new UnidadMedidaDao().createdUnidadMedida(unidadMedida));

        if(unidadMedidaInsert.getIdUnidadMedida() != 0){
            response.setCode(200);
            response.setStatus("success");
            response.setMessage("UNIDAD MEDIDA CREATED");
            response.setData(unidadMedidaInsert);
        }else{
            response.setCode(400);
            response.setStatus("error");
            response.setMessage("ERROR UNIDAD MEDIDA CREATED");
            response.setData(null);
        }

        return response;
    }

    @PUT
    @Path("/unidades.medida")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public MyResponse updateUnidadMedida (UnidadMedida unidadMedida) throws SQLException{
        MyResponse response = new MyResponse();

        boolean flag = (new UnidadMedidaDao().updateUnidadMedida(unidadMedida));

        if(flag){
            response.setCode(200);
            response.setStatus("success");
            response.setMessage("UNIDAD MEDIDA UPDATE");
            response.setData(unidadMedida);
        }else{
            response.setCode(400);
            response.setStatus("error");
            response.setMessage("ERROR UNIDAD MEDIDA UPDATE");
            response.setData(null);
        }

        return response;
    }

    @DELETE
    @Path("/unidades.medida/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public MyResponse deleteUnidadMedida(@PathParam("id") int id) throws SQLException{
        MyResponse response = new MyResponse();

        boolean flag = (new UnidadMedidaDao().deleteUnidadMedida(id));

        if(flag){
            response.setCode(200);
            response.setStatus("success");
            response.setMessage("UNIDAD MEDIDA DELETE");
            response.setData(flag);
        }else{
            response.setCode(400);
            response.setStatus("error");
            response.setMessage("ERROR UNIDAD MEDIDA DELETE");
            response.setData(null);
        }

        return response;
    }

}
