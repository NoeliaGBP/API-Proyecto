package mx.edu.utez.controllers;

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

}
