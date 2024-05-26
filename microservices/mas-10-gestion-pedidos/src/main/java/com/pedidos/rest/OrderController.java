package com.pedidos.rest;

import java.io.IOException;
import java.util.List;

import java.util.concurrent.TimeoutException;

import com.pedidos.modelo.OrderEntity;
import com.pedidos.service.OrderService;

import jakarta.inject.Inject;
import jakarta.persistence.criteria.Order;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import lombok.Getter;
@Path("/orders")

public class OrderController  {

    @Inject
    private OrderService orderService;
    

    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public OrderEntity createOrder(OrderEntity order){
        return orderService.createOrder(order);
        
    }

    @GET
    @Path("/{orderId}")
    @Produces(MediaType.APPLICATION_JSON)
    public OrderEntity getOrder(@PathParam("orderId") String orderId) {
        
        OrderEntity order = orderService.getOrderById(orderId);
        
        if (order != null) {
            
            return order;
        } else {
            
            return null;
        }
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<OrderEntity> getAllOrders() throws IOException, TimeoutException {
        List<OrderEntity> orders = orderService.getAll();
        return orders;
    }

    @PUT
    @Path("/update/{orderId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public OrderEntity cambiarEstado(OrderEntity order, @PathParam("orderId") String orderId){
        return orderService.updateOrderStatus(order, orderId);
        
    }

    @GET
    @Path("/productos/cliente/{clienteId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<OrderEntity> getAllClientesOrdenes(@PathParam("clienteId") String clientID){

        return orderService.ordenesDeCliente(clientID);
    }

}
