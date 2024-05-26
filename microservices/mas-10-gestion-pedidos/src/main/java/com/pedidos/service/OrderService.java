package com.pedidos.service;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeoutException;

import com.pedidos.Respositoy.OrderRepository;
import com.pedidos.Respositoy.rabbit;
import com.pedidos.modelo.OrderEntity;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class OrderService {

    @Inject
    private OrderRepository orderRepository;

    @Inject
    private rabbit ra;

     public OrderEntity createOrder(OrderEntity order) {
        
        return orderRepository.save(order);
    }

    public OrderEntity getOrderById(String orderId) {
        System.out.println(orderId);
        return orderRepository.findById(orderId);
    }

    public List<OrderEntity> getOrdersByUserId(String userId) {
        return orderRepository.findByUserId(userId);
    }

    public OrderEntity updateOrderStatus(OrderEntity order, String orderId) {
        //Optional<Order> orderOpt = orderRepository.findById(orderId);
        if (order != null) {
            return orderRepository.update(order, orderId);
        }
        return null; // O manejar el caso de que no se encuentre el pedido
    }

    public List<OrderEntity> getAll() throws IOException, TimeoutException{
        ra.sendMessage("Hola");
        return orderRepository.getAllOrders();
    }

    public List<OrderEntity> ordenesDeCliente(String clientID) {
        // TODO Auto-generated method stub
        return orderRepository.getOrdenClient(clientID);
    }
    
}
