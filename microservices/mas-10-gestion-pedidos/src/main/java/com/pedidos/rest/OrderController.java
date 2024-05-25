package com.pedidos.rest;

import java.util.List;
import java.util.Optional;

import com.pedidos.modelo.Order;
import com.pedidos.service.OrderService;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
@Path("/orders")

public class OrderController  {

    @Inject
    private OrderService orderService;
    

    @POST
    public Order createOrder(Order order){
        return null;
    }

    @GET
    @Path("/{orderId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Order getOrder(@PathParam("orderId") Integer orderId) {
        
        Order order = orderService.getOrderById(orderId);
        
        if (order != null) {
            
            return order;
        } else {
            
            return null;
        }
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Order> getAllOrders() {
        List<Order> orders = orderService.getAll();
        return orders;
    }

}
