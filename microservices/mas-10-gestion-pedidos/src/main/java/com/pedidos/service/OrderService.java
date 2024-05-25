package com.pedidos.service;

import java.util.List;


import com.pedidos.Respositoy.OrderRepository;
import com.pedidos.modelo.Order;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class OrderService {

    @Inject
    private OrderRepository orderRepository;

     public Order createOrder(Order order) {
        
        return orderRepository.save(order);
    }

    public Order getOrderById(Integer orderId) {
        System.out.println(orderId);
        return orderRepository.findById(orderId);
    }

    public List<Order> getOrdersByUserId(String userId) {
        return orderRepository.findByUserId(userId);
    }

    public Order updateOrderStatus(Order order, Integer orderId) {
        //Optional<Order> orderOpt = orderRepository.findById(orderId);
        if (order != null) {
            return orderRepository.update(order, orderId);
        }
        return null; // O manejar el caso de que no se encuentre el pedido
    }

    public List<Order> getAll(){
        return orderRepository.getAllOrders();
    }
    
}
