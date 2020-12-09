package mx.edu.utez.controllers;

import mx.edu.utez.menu.model.Menu;
import mx.edu.utez.menu.model.MenuDao;
import mx.edu.utez.response.MyResponse;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.List;

@Path("/menu")
public class ServicioMenu {

    @GET
    @Path("/menu")
    @Produces(MediaType.APPLICATION_JSON)
    public MyResponse getMenus(){
        MyResponse response = new MyResponse();
        List<Menu> menus = (new MenuDao().getMenus());
        response.setData(menus);
        if(menus.size() > 0){
            response.setStatus("success");
            response.setCode(200);
            response.setMessage("Consulta exitosa");
        }else{
            response.setCode(400);
            response.setStatus("error");
            response.setMessage("Sin registros o falla");
        }
        return response;
    }

    @GET
    @Path("/menu/{idMenu}")
    @Produces(MediaType.APPLICATION_JSON)
    public MyResponse getMenu(@PathParam("idMenu") int idM){
        MyResponse response = new MyResponse();
        Menu menu = (new MenuDao().getMenuById(idM));
        response.setData(menu);
        if(menu != null){
            response.setCode(200);
            response.setStatus("success");
            response.setMessage("Consulta exitosa");
        }else {
            response.setCode(400);
            response.setStatus("error");
            response.setMessage("Error en la consulta");
        }
        return response;
    }

    @POST
    @Path("/menu")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public MyResponse createMenu(Menu menuN) throws SQLException {
        MyResponse response = new MyResponse();
        Menu menuR = (new MenuDao().createMenu(menuN));
        response.setData(menuR);
        if(menuR.getIdMenu() != 0){
            response.setCode(200);
            response.setStatus("success");
            response.setMessage("Se ha creado con exito");
        }else {
            response.setCode(400);
            response.setStatus("error");
            response.setMessage("Error en la creación");
        }
        return response;
    }

    @PUT
    @Path("/menu")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public MyResponse updateMenu(Menu menuN) throws SQLException{
        MyResponse response = new MyResponse();
        boolean m = (new MenuDao().updateMenu(menuN));
        response.setData(m);
        if(m){
            response.setCode(200);
            response.setStatus("success");
            response.setMessage("La actualización se realizó con éxito");
        }else {
            response.setCode(400);
            response.setStatus("error");
            response.setMessage("Error en la actualización");
        }
        return response;
    }

    @DELETE
    @Path("/menu/{idMenu}")
    @Produces(MediaType.APPLICATION_JSON)
    public MyResponse deleteMenu(@PathParam("idMenu") int idM) throws SQLException{
        MyResponse response = new MyResponse();
        boolean d = (new MenuDao().deleteMenu(idM));
        response.setData(d);
        if(d){
            response.setCode(200);
            response.setStatus("success");
            response.setMessage("Eliminación exitosa");
        }else {
            response.setCode(400);
            response.setStatus("error");
            response.setMessage("Falla en la eliminación");
        }
        return response;
    }

}
