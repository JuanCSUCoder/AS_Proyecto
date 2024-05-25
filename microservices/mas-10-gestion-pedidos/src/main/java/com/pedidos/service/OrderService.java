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
        // Lógica para crear un nuevo pedido
        // Esto podría incluir validación de productos y cálculo del total
        return orderRepository.save(order);
    }

    public Order getOrderById(Integer orderId) {
        System.out.println(orderId);
        return orderRepository.findById(orderId);
    }

    public List<Order> getOrdersByUserId(String userId) {
        return orderRepository.findByUserId(userId);
    }

    // public Order updateOrderStatus(String orderId, String status) {
    //     //Optional<Order> orderOpt = orderRepository.findById(orderId);
    //     if (orderOpt.isPresent()) {
    //         Order order = orderOpt.get();
    //         order.setStatus(status);
    //         return orderRepository.save(order);
    //     }
    //     return null; // O manejar el caso de que no se encuentre el pedido
    // }

    public List<Order> getAll(){
        return orderRepository.getAllOrders();
    }
    
}
