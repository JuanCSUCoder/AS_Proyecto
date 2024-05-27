package com.pedidos.Respositoy;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@ApplicationScoped
public class rabbit {
    private static final String QUEUE_NAME = "myQueue";
    private ConnectionFactory factory;

    @Inject
    public rabbit() {
        factory = new ConnectionFactory();
        factory.setHost("localhost");  // Cambia esto según tu configuración
        factory.setUsername("user");
        factory.setPassword("password");
    }

    public void sendMessage(String message) throws IOException, TimeoutException {
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println(" [x] Sent '" + message + "'");
        }
    }

    public void receiveMessage() throws IOException, TimeoutException {
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            channel.basicConsume(QUEUE_NAME, true, (consumerTag, delivery) -> {
                String message = new String(delivery.getBody(), "UTF-8");
                System.out.println(" [x] Received '" + message + "'");
            }, consumerTag -> { });
        }
    }
}
