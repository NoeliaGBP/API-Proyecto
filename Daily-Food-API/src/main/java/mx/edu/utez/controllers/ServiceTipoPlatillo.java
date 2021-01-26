package mx.edu.utez.controllers;

import mx.edu.utez.response.MyResponse;
import mx.edu.utez.tipoPlatillo.model.TipoPlatillo;
import mx.edu.utez.tipoPlatillo.model.TipoPlatilloDao;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.List;

@Path("/daily")
public class ServiceTipoPlatillo{
    @GET
    @Path("/tipo.platillos")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public MyResponse getTipoPlatillos() throws SQLException {
        MyResponse response = new MyResponse();
        List list = (new TipoPlatilloDao().getTipoPlatillo());
        if(list.size() > 0){
            response.setCode(200);
            response.setStatus("success");
            response.setMessage("READ  TIPO PLATILLOS");
            response.setData(list);
        }else{
            response.setCode(400);
            response.setStatus("error");
            response.setMessage("ERROR READ TIPO PLATILLOS");
            response.setData(null);
        }
        return response;
    }

    @GET
    @Path("/tipo.platillos/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public MyResponse getTipoPlatillosById(@PathParam("id") int id) throws SQLException{
        MyResponse response = new MyResponse();
        TipoPlatillo platillo = (new TipoPlatilloDao().getTipoPlatilloById(id));
        if(platillo.getIdTipoPlatillo() > 0){
            response.setCode(200);
            response.setStatus("success");
            response.setMessage("READ TIPO PLATILLO BY ID");
            response.setData(platillo);
        }else{
            response.setCode(400);
            response.setStatus("error");
            response.setMessage("ERROR READ TIPO PLATILLO BY ID");
            response.setData(null);
        }
        return response;
    }

    @POST
    @Path("/tipo.platillos")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public MyResponse  createPlatillo(TipoPlatillo platillo) throws SQLException{
        MyResponse response = new MyResponse();
        TipoPlatillo platilloInsert = (new TipoPlatilloDao().createTipoPlatillo(platillo));
        if(platilloInsert.getIdTipoPlatillo() > 0){
            response.setCode(200);
            response.setStatus("success");
            response.setMessage("TIPO PLATILLO CREATED");
            response.setData(platilloInsert);
        }else{
            response.setCode(400);
            response.setStatus("error");
            response.setMessage("ERROR TIPO PLATILLO CREATED");
            response.setData(null);
        }
        return response;
    }


    @PUT
    @Path("/tipo.platillos")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public MyResponse updatePlatillo(TipoPlatillo platillo) throws SQLException{
        MyResponse response = new MyResponse();
        boolean flag = (new TipoPlatilloDao().updateTipoPlatillo(platillo));
        if(flag){
            response.setCode(200);
            response.setStatus("success");
            response.setMessage("TIPO PLATILLO UPDATE");
            response.setData(platillo);
        }else{
            response.setCode(400);
            response.setStatus("error");
            response.setMessage("ERROR TIPO PLATILLO UPDATE");
            response.setData(null);
        }
        return response;
    }


    @DELETE
    @Path("/tipo.platillos/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public MyResponse deletePlatillo(@PathParam("id") int id) throws SQLException{
        MyResponse response = new MyResponse();
        boolean flag = (new TipoPlatilloDao().deleteTipoPlatillo(id));
        if(flag){
            response.setCode(200);
            response.setStatus("success");
            response.setMessage("TIPO PLATILLO DELETE");
            response.setData(flag);
        }else{
            response.setCode(400);
            response.setStatus("error");
            response.setMessage("ERROR  TIPO PLATILLO DELETE");
            response.setData(null);
        }
        return response;
    }
}