package mx.edu.utez.controllers;

import mx.edu.utez.pedido.model.Pedido;
import mx.edu.utez.pedido.model.PedidoCompleto;
import mx.edu.utez.pedido.model.PedidoDao;
import mx.edu.utez.response.MyResponse;
import sun.nio.cs.ext.MacUkraine;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.List;

@Path("/daily")
public class ServicePedido {

    /*@GET
    @Path("/pedidos")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public MyResponse getPedidos() throws SQLException {
        MyResponse response = new MyResponse();
        List list = (new PedidoDao().getPedidos());

        if (list.size() > 0) {
            response.setCode(200);
            response.setStatus("success");
            response.setMessage("READ PEDIDOS OK");
            response.setData(list);
        } else {
            response.setCode(400);
            response.setStatus("error");
            response.setMessage("ERROR READ PEDIDOS");
            response.setData(null);
        }
        return response;
    }*/

    @GET
    @Path("/pedidosP")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public MyResponse getAllPedidosPreparacion() throws SQLException{
        MyResponse response = new MyResponse();
        List<PedidoCompleto> pedidos = ( new PedidoDao().getAllPedidosPreparacion());
        if(pedidos.size() > 0){
            response.setCode(200);
            response.setStatus("Success");
            response.setMessage("PEDIDOS EN PREPARACIÓN");
            response.setData(pedidos);
        }else{
            response.setCode(400);
            response.setStatus("Error");
            response.setMessage("HUBO ERROR");
            response.setData(null);
        }
        return response;
    }

    @GET
    @Path("/pedidosP/{user}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public MyResponse getPedidosPreparacionByUser(@PathParam("user") String user) throws SQLException{
        MyResponse response = new MyResponse();
        List<Pedido> pedidos = ( new PedidoDao().getPedidosPreparacionByUser(user));
        if(pedidos.size() > 0){
            response.setCode(200);
            response.setStatus("Success");
            response.setMessage("PEDIDOS EN PREPARACIÓN BY USER");
            response.setData(pedidos);
        }else{
            response.setCode(400);
            response.setStatus("Error");
            response.setMessage("HUBO ERROR");
            response.setData(null);
        }
        return response;
    }

    @GET
    @Path("/pedidosC")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public MyResponse getPedidosEnCurso() throws SQLException{
        MyResponse response = new MyResponse();
        List<Pedido> pedidos = ( new PedidoDao().getPedidosEnCurso());
        if(pedidos.size() > 0){
            response.setCode(200);
            response.setStatus("Success");
            response.setMessage("PEDIDOS EN CURSO");
            response.setData(pedidos);
        }else{
            response.setCode(400);
            response.setStatus("Error");
            response.setMessage("HUBO ERROR");
            response.setData(null);
        }
        return response;
    }

    @GET
    @Path("/pedidosC/{user}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public MyResponse getPedidosEnCursoByUser(@PathParam("user") String user) throws SQLException{
        MyResponse response = new MyResponse();
        List<Pedido> pedidos = ( new PedidoDao().getPedidosEnCursoByUser(user));
        if(pedidos.size() > 0){
            response.setCode(200);
            response.setStatus("Success");
            response.setMessage("PEDIDOS EN CURSO BY USER");
            response.setData(pedidos);
        }else{
            response.setCode(400);
            response.setStatus("Error");
            response.setMessage("HUBO ERROR");
            response.setData(null);
        }
        return response;
    }

    @GET
    @Path("/pedidosE")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public MyResponse getPedidosEntregados() throws SQLException{
        MyResponse response = new MyResponse();
        List<Pedido> pedidos = ( new PedidoDao().getPedidosEntregados());
        if(pedidos.size() > 0){
            response.setCode(200);
            response.setStatus("Success");
            response.setMessage("PEDIDOS ENTREGADOS OBTENIDOS");
            response.setData(pedidos);
        }else{
            response.setCode(400);
            response.setStatus("Error");
            response.setMessage("HUBO ERROR");
            response.setData(null);
        }
        return response;
    }

    @GET
    @Path("/pedidosE/{user}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public MyResponse getPedidosEntregadosByUser(@PathParam("user") String user) throws SQLException{
        MyResponse response = new MyResponse();
        List<Pedido> pedidos = ( new PedidoDao().getPedidosEntregadosByUser(user));
        if(pedidos.size() > 0){
            response.setCode(200);
            response.setStatus("Success");
            response.setMessage("PEDIDOS ENTREGADOS BY USER");
            response.setData(pedidos);
        }else{
            response.setCode(400);
            response.setStatus("Error");
            response.setMessage("HUBO ERROR");
            response.setData(null);
        }
        return response;
    }

    @GET
    @Path("/pedidosF/{user}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public MyResponse getPedidosFinalizadosByUser(@PathParam("user") String user) throws SQLException{
        MyResponse response = new MyResponse();
        List<Pedido> pedidos = ( new PedidoDao().getPedidosFinalizadosByUser(user));
        if(pedidos.size() > 0){
            response.setCode(200);
            response.setStatus("Success");
            response.setMessage("PEDIDOS FINALIZADOS BY USER");
            response.setData(pedidos);
        }else{
            response.setCode(400);
            response.setStatus("Error");
            response.setMessage("HUBO ERROR");
            response.setData(null);
        }
        return response;
    }

    @GET
    @Path("/pedidos/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public MyResponse getPedidoById(@PathParam("id") int id) throws SQLException {
        MyResponse response = new MyResponse();

        Pedido pedido = (new PedidoDao().getPedidoById(id));
        if (pedido.getId() > 0) {
            response.setCode(200);
            response.setStatus("success");
            response.setMessage("READ PEDIDO BY ID OK");
            response.setData(pedido);
        } else {
            response.setCode(400);
            response.setStatus("error");
            response.setMessage("ERROR READ PEDIDO BY ID");
            response.setData(null);
        }
        return response;
    }

    @POST
    @Path("/pedidos")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public MyResponse createPedido(Pedido pedido) throws SQLException {
        MyResponse response = new MyResponse();

        Pedido pedidoInsert = (new PedidoDao().createPedido(pedido));
        if (pedidoInsert.getId() > 0) {
            response.setCode(200);
            response.setStatus("success");
            response.setMessage("PEDIDO CREATED");
            response.setData(pedidoInsert);
        } else {
            response.setCode(400);
            response.setStatus("error");
            response.setMessage("PEDIDO NOT CREATED");
            response.setData(null);
        }

        return response;
    }

    @PUT
    @Path("/pedidos")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public MyResponse updatePedido(Pedido pedido) throws SQLException {
        MyResponse response = new MyResponse();

        boolean flag = (new PedidoDao().updatePedido(pedido));
        if (flag) {
            response.setCode(200);
            response.setStatus("success");
            response.setMessage("PEDIDO UPDATE");
            response.setData(pedido);
        } else {
            response.setCode(400);
            response.setStatus("error");
            response.setMessage("PEDIDO NOT UPDATE");
            response.setData(null);
        }

        return response;
    }

    @PUT
    @Path("/cancelar.pedido/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public MyResponse cancelPedido(@PathParam("id") int id) throws SQLException {
        MyResponse response = new MyResponse();

        boolean flag = (new PedidoDao().cancelarPedido(id));
        if (flag) {
            response.setCode(200);
            response.setStatus("success");
            response.setMessage("PEDIDO CANCELED");
            response.setData((new PedidoDao().getPedidoById(id)));
        } else {
            response.setCode(400);
            response.setStatus("error");
            response.setMessage("PEDIDO NOT CANCELED");
            response.setData(null);
        }

        return response;
    }


}
