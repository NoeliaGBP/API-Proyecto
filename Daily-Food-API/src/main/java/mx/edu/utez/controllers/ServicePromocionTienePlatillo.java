package mx.edu.utez.controllers;

import mx.edu.utez.promocion.model.Promocion;
import mx.edu.utez.promocion.model.PromocionDao;
import mx.edu.utez.promociontieneplatillo.model.PromocionTienePlatillo;
import mx.edu.utez.promociontieneplatillo.model.PromocionTienePlatilloDao;
import mx.edu.utez.response.MyResponse;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.List;

@Path("/daily")
public class ServicePromocionTienePlatillo {

    @GET
    @Path("/promociones.tienen.platillos/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public MyResponse getPersonas(@PathParam("id") int id) throws SQLException {
        MyResponse resp = new MyResponse();
        List<PromocionTienePlatillo> list = (new PromocionTienePlatilloDao().readPromocionTienePlatilloByPromocion(id));
        if (list.size() > 0 ) {
            resp.setCode(200);
            resp.setMessage("Promocion tienen platillo en menú");
            resp.setStatus("success");
            resp.setData(list);
        } else {
            resp.setCode(400);
            resp.setMessage("Error");
            resp.setStatus("error");
        }
        return resp;
    }


    @POST
    @Path("/promociones.tienen.platillos")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public MyResponse createPromocion(PromocionTienePlatillo promocionTienePlatillo) throws SQLException {
        MyResponse resp = new MyResponse();

        boolean flag = (new PromocionTienePlatilloDao().createPromocionTienePlatillo(promocionTienePlatillo) );

        if (flag) {
            resp.setCode(200);
            resp.setMessage("Promoción tiene platillo creada");
            resp.setStatus("success");
            resp.setData(promocionTienePlatillo);
        } else {
            resp.setCode(400);
            resp.setMessage("Error, no se creó.");
            resp.setStatus("error");
        }

        return resp;
    }

    @DELETE
    @Path("/promociones.tienen.platillos")
    @Produces(MediaType.APPLICATION_JSON)
    public MyResponse deletePromocion(PromocionTienePlatillo promocion) throws SQLException {
        MyResponse resp = new MyResponse();

        boolean borrado = (new PromocionTienePlatilloDao().deletePromocionTienePlatillo(promocion) ) ;
        if (!borrado) {
            resp.setCode(400);
            resp.setMessage("Error, no se eliminó");
            resp.setStatus("error");
        } else {
            resp.setCode(200);
            resp.setMessage("Platillo eliminado de la promoción");
            resp.setStatus("success");

        }
        return resp;
    }

}
