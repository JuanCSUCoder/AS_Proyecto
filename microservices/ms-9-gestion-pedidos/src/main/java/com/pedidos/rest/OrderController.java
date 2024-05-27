package com.pedidos.rest;

import java.io.IOException;
import java.util.List;

import java.util.concurrent.TimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pedidos.model.Order;
import com.pedidos.service.OrderService;

import jakarta.inject.Inject;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/orders")

public class OrderController  {

    @Inject
    private OrderService orderService;
    
    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createOrder(Order order){

        Order order2= orderService.createOrder(order);

        if(order2 == null){
            return Response.status(Response.Status.BAD_REQUEST)
            .entity("Algunos productos no están disponibles en el inventario.")
            .build();
        }

        return Response.status(Response.Status.OK)
        .entity(order2)
        .build();
        
    }

    @GET
    @Path("/{orderId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Order getOrder(@PathParam("orderId") String orderId) {
        
        return  orderService.getOrderById(orderId);
        
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Order> getAllOrders() throws IOException, TimeoutException {
        List<Order> orders = orderService.getAll();
        return orders;
    }


    @POST
    @Path("/payorder")
    @Produces(MediaType.APPLICATION_JSON)
    public Order registerOrderPayment(@QueryParam("orderid") String orderid){
        return orderService.registerOrderPayment(orderid);
    }

    @GET
    @Path("/productos/cliente/{clienteId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Order> getAllClientesOrdenes(@PathParam("clienteId") String clientID){

        return orderService.getOrdersByUserId(clientID);
    }

    @POST
    @Path("/ondeliver")
    @Produces(MediaType.APPLICATION_JSON)
    public Order registerOrderOnDelivery(@QueryParam("orderid") String orderid){

        return orderService.registerOrderOnDelivery(orderid);
    }

    @POST
    @Path("/delivered")
    @Produces(MediaType.APPLICATION_JSON)
    public Order registerOrderDelivered(@QueryParam("orderid") String orderid){
        return orderService.registerOrderDelivered(orderid);
    }

    @PUT
    @Path("/delivered")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Order update(Order order,@QueryParam("orderid") String orderid) throws IOException, TimeoutException{
        return orderService.updateOrderStatus(order,orderid);
    }

}
