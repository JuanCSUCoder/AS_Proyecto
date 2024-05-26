package com.pedidos.Respositoy;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.enterprise.context.ApplicationScoped;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeoutException;

@ApplicationScoped
public class RabbitMQConsumerService {

    private static final String QUEUE_NAME = "myQueue";
    private ConnectionFactory factory;
    private Connection connection;
    private Channel channel;
    private ExecutorService executorService;

    @PostConstruct
    public void init() {
        factory = new ConnectionFactory();
        factory.setHost("localhost");  // Cambia esto según tu configuración
        factory.setUsername("user");
        factory.setPassword("password");

        executorService = Executors.newSingleThreadExecutor();
        executorService.submit(this::consumeMessages);
    }

    private void consumeMessages() {
        try {
            connection = factory.newConnection();
            channel = connection.createChannel();
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);

            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
                System.out.println(" [x] Received '" + message + "'");
                processMessage(message);
            };

            channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }
    }

    private void processMessage(String message) {
        // Aquí procesas el mensaje recibido
        System.out.println(" [x] Processing '" + message + "'");
        // Añade tu lógica de procesamiento aquí
    }

    @PreDestroy
    public void cleanup() {
        try {
            if (channel != null && channel.isOpen()) {
                channel.close();
            }
            if (connection != null && connection.isOpen()) {
                connection.close();
            }
            if (executorService != null && !executorService.isShutdown()) {
                executorService.shutdown();
            }
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }
    }
}