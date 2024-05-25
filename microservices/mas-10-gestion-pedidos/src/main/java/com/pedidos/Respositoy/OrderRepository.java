package com.pedidos.Respositoy;



import jakarta.enterprise.context.ApplicationScoped;

import java.util.ArrayList;
import java.util.List;

import java.util.stream.Collectors;


import com.pedidos.modelo.Order;
import com.pedidos.modelo.OrderItem;
import com.pedidos.modelo.Product;
import com.pedidos.modelo.User;

@ApplicationScoped
public class OrderRepository {

    private List<Order> orders = new ArrayList<>();
    private List<OrderItem> orderItems = new ArrayList<>();
    private List<Product> products = new ArrayList<>();
    private List<User> users = new ArrayList<>();

    public OrderRepository() {
        // Datos quemados para usuarios
        User user1 = new User();
        user1.setId("user1");
        user1.setUserPod("userPod1");
        user1.setProviderUrl("providerUrl1");

        User user2 = new User();
        user2.setId("user2");
        user2.setUserPod("userPod2");
        user2.setProviderUrl("providerUrl2");

        users.add(user1);
        users.add(user2);

        // Datos quemados para productos
        Product product1 = new Product();
        product1.setId("product1");
        product1.setName("Product 1");
        product1.setDesc("Description for product 1");
        product1.setPrice(10.0f);
        product1.setStock(100);

        Product product2 = new Product();
        product2.setId("product2");
        product2.setName("Product 2");
        product2.setDesc("Description for product 2");
        product2.setPrice(20.0f);
        product2.setStock(200);

        products.add(product1);
        products.add(product2);

        // Datos quemados para pedidos
        OrderItem orderItem1 = new OrderItem();
        orderItem1.setId("orderItem1");
        orderItem1.setOrderId("order1");
        orderItem1.setProductId("product1");
        orderItem1.setQuantity(2);
        

        OrderItem orderItem2 = new OrderItem();
        orderItem2.setId("orderItem2");
        orderItem2.setOrderId("order1");
        orderItem2.setProductId("product2");
        orderItem2.setQuantity(1);
        

        orderItems.add(orderItem1);
        orderItems.add(orderItem2);

        Order order1 = new Order();
        order1.setId(1);
        order1.setUserId("user1");
        order1.setStatus("Pending");
        order1.setTotal(40.0f);

        orders.add(order1);
    }

    public Order save(Order order) {
        orders.add(order);
        return order;
    }

    public Order update(Order order, Integer orderId){
        System.out.println(orderId);
        Order orderchange = findById(orderId);
        if(orderchange != null){
            for (int i = 0 ; i < orders.size();i++){
                if (orders.get(i).getId().equals(orderId)){
                    orders.set(i, order);
                    return order;
                }
            }
        }
        

        return null;
    }

    public Order findById(Integer id) {
        
        for (int i = 0 ; i < orders.size();i++){
            
            if(orders.get(i).getId().equals(id) ){
                
                return orders.get(i);
            }
        }

        return null;
    }

    public List<Order> findByUserId(String userId) {
        return orders.stream().filter(order -> order.getUserId().equals(userId)).collect(Collectors.toList());
    }

    public List<Order> getAllOrders(){
        return orders;
    }

    public List<Order> getOrdenClient(String clientID) {
        List<Order> clientOrders = new ArrayList<>();
        for (int i = 0 ; i < orders.size();i++){
            String user = orders.get(i).getUserId();
            if ( user.equals(clientID)){
                clientOrders.add(orders.get(i));
            }
        }

        return clientOrders;
    }
}
