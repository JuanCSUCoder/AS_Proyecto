package com.pedidos.Respositoy;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pedidos.model.Order;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

@ApplicationScoped
public class rabbit {
    private static final String QUEUE_NAME = "cola-correo";
    private ConnectionFactory factory;
    private ObjectMapper objectMapper;

    @Inject
    public rabbit() {
        factory = new ConnectionFactory();
        factory.setHost("localhost"); // Cambia esto según tu configuración
        factory.setUsername("user");
        factory.setPassword("password");
        objectMapper = new ObjectMapper();
    }

    public void sendMessage(Order order) throws IOException, TimeoutException {
        String message = objectToJson(order);
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
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
}
