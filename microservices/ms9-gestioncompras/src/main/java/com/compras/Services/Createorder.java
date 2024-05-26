package com.compras.Services;

import com.compras.model.Order;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

public class Createorder {
    
    private static final String ORDER_SERVICE_URL = "http://localhost:5010/gestionpedidos/api/orders/create";

    public Response createOrder(Order order) {
        Client client = ClientBuilder.newClient();

        try {
            Response response = client
                .target(ORDER_SERVICE_URL)
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(order, MediaType.APPLICATION_JSON));

            return response;
        } finally {
            client.close();
        }
    }
}
