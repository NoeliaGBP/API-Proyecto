package mx.edu.utez.controllers;

import com.sun.jersey.multipart.FormDataParam;
import mx.edu.utez.platillo.model.PlatilloDao;
import mx.edu.utez.response.MyResponse;
import mx.edu.utez.imagenplatillo.model.ImagenPlatillo;
import mx.edu.utez.imagenplatillo.model.ImagenPlatilloDAO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.File;
import java.sql.SQLException;
import java.util.List;

@Path("/daily")
public class ServiceImagenPlatillo {

    @GET
    @Path("/imagenes.platillo")
    @Produces(MediaType.APPLICATION_JSON)
    public MyResponse getImages() {
        MyResponse response = new MyResponse();
        List list = (new ImagenPlatilloDAO().getImagenes());

        if (list.size() > 0) {
            response.setCode(200);
            response.setStatus("success");
            response.setMessage("List of images on dishes");
            response.setData(list);
        } else {
            response.setCode(400);
            response.setStatus("error");
            response.setMessage("The list was not obtained");
            response.setData(null);
        }

        return response;
    }

    @GET
    @Path("/imagenes.platillo/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public MyResponse getImagenPlatilloById(@PathParam("id") int id) {
        MyResponse response = new MyResponse();

        ImagenPlatillo imagenPlatillo = (new ImagenPlatilloDAO().getImagenPlatilloById(id));

        if (imagenPlatillo.getIdImagenPlatillo() > 0) {
            response.setCode(200);
            response.setStatus("success");
            response.setMessage("Image on dish obtained");
            response.setData(imagenPlatillo);
        } else {
            response.setCode(400);
            response.setStatus("error");
            response.setMessage("Image on dish not obtained");
            response.setData(null);
        }

        return response;
    }

    @POST
    @Path("/imagenes.platillo/{idPlatillo}")
    @Produces(MediaType.APPLICATION_JSON)
    public MyResponse createImagenPlatillo(@FormDataParam("img") File img, @PathParam("idPlatillo") int idPlatillo) throws Exception  {
        MyResponse response = new MyResponse();
        System.out.println("Value"+img);
        ImagenPlatillo created = (new ImagenPlatilloDAO().createImagenPlatillo(img, idPlatillo));

        if (created.getIdImagenPlatillo() > 0) {
            response.setCode(200);
            response.setStatus("success");
            response.setMessage("Image created");
            response.setData(created);
        } else {
            response.setCode(400);
            response.setStatus("error");
            response.setMessage("Image not created");
            response.setData(null);
        }

        return response;
    }

    @PUT
    @Path("/imagenes.platillo/{idImagenPlatillo}/{idPlatillo}")
    @Produces(MediaType.APPLICATION_JSON)
    public MyResponse updateImagenPlatillo(@FormDataParam("img") File img, @PathParam("idImagenPlatillo") int idImagenPlatillo, @PathParam("idPlatillo") int idPlatillo) throws SQLException {
        MyResponse response = new MyResponse();
        ImagenPlatillo imagenPlatillo = new ImagenPlatillo();
        imagenPlatillo.setIdImagenPlatillo(idImagenPlatillo);
        imagenPlatillo.setIdPlatillo((new PlatilloDao()).getPlatilloById(idPlatillo));

        ImagenPlatillo updated = (new ImagenPlatilloDAO().updateImagenPlatillo(img, imagenPlatillo));

        if(updated.getIdImagenPlatillo() > 0){
            response.setCode(200);
            response.setStatus("success");
            response.setMessage("Image updated");
            response.setData(updated);
        }else{
            response.setCode(400);
            response.setStatus("error");
            response.setMessage("Image not updated");
        }

        return response;
    }

    @DELETE
    @Path("/imagenes.platillo/{id}")
    @Produces(MediaType.APPLICATION_JSON)

    public MyResponse deleteSucursal(@PathParam("id") int id) throws SQLException {
        MyResponse response = new MyResponse();

        boolean flag = (new ImagenPlatilloDAO().deleteImagenPlatillo(id));

        if(flag){
            response.setCode(200);
            response.setStatus("success");
            response.setMessage("Image deleted");
            response.setData(flag);
        }else{
            response.setCode(400);
            response.setStatus("error");
            response.setMessage("Image not deleted");
        }

        return response;
    }
}