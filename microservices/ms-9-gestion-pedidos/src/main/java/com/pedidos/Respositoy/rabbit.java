package com.pedidos.Respositoy;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pedidos.model.Order;
import com.pedidos.model.OrderWithEmail;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.Cookie;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

@ApplicationScoped
public class rabbit {
    private static final String BASE_URL = "http://usuarios:9080/gestionusuarios/api/userprofiles/email";

    private Client client = ClientBuilder.newClient();
    private static final String QUEUE_NAME = "cola-correo";
    private ConnectionFactory factory;
    private ObjectMapper objectMapper;

    @Inject
    public rabbit() {
        factory = new ConnectionFactory();
        factory.setHost("rabbitmq");  // Cambia esto según tu configuración
        factory.setUsername("user");
        factory.setPassword("password");
        objectMapper = new ObjectMapper();
    }

    public void sendMessage(Order order, String cookie) throws IOException, TimeoutException {
        System.out.println("antes de enviar:" + cookie);
        String email = getUserProfileByEmail(cookie);
        System.out.println(email);
        OrderWithEmail orderWithEmail = new OrderWithEmail(order, email);
        String message = objectToJson(orderWithEmail);
        try (Connection connection = factory.newConnection();
            Channel channel = connection.createChannel()) {
            channel.queueDeclare(QUEUE_NAME, true, false, false, null);
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes(StandardCharsets.UTF_8));
        }
    }

    public void receiveMessage() throws IOException, TimeoutException {
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            channel.basicConsume(QUEUE_NAME, true, (consumerTag, delivery) -> {
                String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
                Order order = jsonToObject(message, Order.class);
                System.out.println(" [x] Received '" + order + "'");
            }, consumerTag -> { });
        }
    }

    private String objectToJson(Object obj) throws JsonProcessingException {
        return objectMapper.writeValueAsString(obj);
    }

    private <T> T jsonToObject(String json, Class<T> clazz) throws JsonProcessingException {
        return objectMapper.readValue(json, clazz);
    }

    public String getUserProfileByEmail(String id) {
        String apiUrl = BASE_URL + "/" + id;
        
    
        Response response = client.target(apiUrl)
                .request(MediaType.TEXT_PLAIN)
                .get();
    
        if (response.getStatus() == Response.Status.OK.getStatusCode()) {
            return response.readEntity(String.class);
        } else if (response.getStatus() == Response.Status.NOT_FOUND.getStatusCode()) {
            throw new RuntimeException("User not found");
        } else {
            throw new RuntimeException("Failed to get user profile: " + response.getStatus());
        }
    }
    
}
