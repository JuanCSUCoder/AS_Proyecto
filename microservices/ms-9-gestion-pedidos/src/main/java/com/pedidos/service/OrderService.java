package com.pedidos.service;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeoutException;


import com.pedidos.Respositoy.rabbit;
import com.pedidos.model.Order;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Cookie;
@ApplicationScoped
public class OrderService {

    private static final String BASE_URL = "http://ms14_datos_compra:8080";

    private Client client = ClientBuilder.newClient();


    @Inject
    private rabbit ra;

    public Order createOrder(Order order,String cookie) throws IOException, TimeoutException {
        
        Response response = client.target(BASE_URL)
        .path("/order")
        .request(MediaType.APPLICATION_JSON)
        .post(Entity.entity(order, MediaType.APPLICATION_JSON));

        if (response.getStatus() == Response.Status.OK.getStatusCode()) {
            ra.sendMessage(order, cookie);
            return response.readEntity(Order.class);
        } else {
            throw new RuntimeException("Failed to create order");
        }
    }

    public Order getOrderById(String orderId) {
        System.out.println(orderId);
        Response response = client.target(BASE_URL)
            .path("/order")
            .queryParam("orderId", orderId)
            .request(MediaType.APPLICATION_JSON)
            .get();

        if (response.getStatus() == Response.Status.OK.getStatusCode()) {
            return response.readEntity(Order.class);
        } else {
            throw new RuntimeException("Order not found");
        }
    }

    public List<Order> getOrdersByUserId(String userId) {
        Response response = client.target(BASE_URL)
        .path("/orders")
        .queryParam("userId", userId)
        .request(MediaType.APPLICATION_JSON)
        .get();

        if (response.getStatus() == Response.Status.OK.getStatusCode()) {
            return response.readEntity(new GenericType<List<Order>>() {});
        } else {
            throw new RuntimeException("Failed to fetch orders for user");
        }
    }

    public List<Order> getAll() throws IOException, TimeoutException{

       
        return null;
    }

    public List<Order> ordenesDeCliente(String clientID) {
        
        Response response = client.target(BASE_URL)
            .path("/orders")
            .queryParam("clientId", clientID)
            .request(MediaType.APPLICATION_JSON)
            .get();

        if (response.getStatus() == Response.Status.OK.getStatusCode()) {
            return response.readEntity(new GenericType<List<Order>>() {});
        } else {
            throw new RuntimeException("Failed to fetch orders for client");
        }
    }

    public Order registerOrderPayment(String orderId) {
        Response response = client.target(BASE_URL)
            .path("/payorder")
            .queryParam("orderid", orderId)
            .request(MediaType.APPLICATION_JSON)
            .post(null); // No se envía entidad en el cuerpo de la solicitud

        if (response.getStatus() == Response.Status.OK.getStatusCode()) {
            return response.readEntity(Order.class);
        } else {
            throw new RuntimeException("Failed to register order payment");
        }
    }

    public Order registerOrderOnDelivery(String orderId) {
        Response response = client.target(BASE_URL)
            .path("/ondeliver")
            .queryParam("orderid", orderId)
            .request(MediaType.APPLICATION_JSON)
            .post(null); // No se envía entidad en el cuerpo de la solicitud

        if (response.getStatus() == Response.Status.OK.getStatusCode()) {
            return response.readEntity(Order.class);
        } else {
            throw new RuntimeException("Failed to register order on delivery");
        }
    }

    public Order registerOrderDelivered(String orderId) {
        Response response = client.target(BASE_URL)
            .path("/delivered")
            .queryParam("orderid", orderId)
            .request(MediaType.APPLICATION_JSON)
            .post(null); // No se envía entidad en el cuerpo de la solicitud

        if (response.getStatus() == Response.Status.OK.getStatusCode()) {
            return response.readEntity(Order.class);
        } else {
            throw new RuntimeException("Failed to register order as delivered");
        }
    }
    
}
