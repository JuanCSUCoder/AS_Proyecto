package com.pedidos.Respositoy;



import jakarta.enterprise.context.ApplicationScoped;

import java.util.ArrayList;
import java.util.List;

import java.util.stream.Collectors;

import com.pedidos.modelo.OrderEntity;
import com.pedidos.modelo.OrderItemEntity;
import com.pedidos.modelo.ProductEntity;
import com.pedidos.modelo.UserEntity;


@ApplicationScoped
public class OrderRepository {

    private List<OrderEntity> orders = new ArrayList<>();
    private List<OrderItemEntity> orderItems = new ArrayList<>();
    private List<ProductEntity> products = new ArrayList<>();
    private List<UserEntity> users = new ArrayList<>();

    public OrderRepository() {
        // Datos quemados para usuarios
        UserEntity user1 = new UserEntity();
        user1.setId("user1");
        user1.setUserPod("userPod1");
        user1.setProviderUrl("providerUrl1");

        UserEntity user2 = new UserEntity();
        user2.setId("user2");
        user2.setUserPod("userPod2");
        user2.setProviderUrl("providerUrl2");

        users.add(user1);
        users.add(user2);

        // Datos quemados para productos
        ProductEntity product1 = new ProductEntity();
        product1.setId("product1");
        product1.setName("Product 1");
        product1.setDescr("Description for product 1");
        product1.setPrice(10.0f);
        

        ProductEntity product2 = new ProductEntity();
        product2.setId("product2");
        product2.setName("Product 2");
        product2.setDescr("Description for product 2");
        product2.setPrice(20.0f);
        

        products.add(product1);
        products.add(product2);

        // Datos quemados para pedidos
        OrderItemEntity orderItem1 = new OrderItemEntity();
        orderItem1.setId("orderItem1");
        orderItem1.setOrderId("order1");
        orderItem1.setProductId("product1");
        orderItem1.setQuantity(2);
        

        OrderItemEntity orderItem2 = new OrderItemEntity();
        orderItem2.setId("orderItem2");
        orderItem2.setOrderId("order1");
        orderItem2.setProductId("product2");
        orderItem2.setQuantity(1);
        

        orderItems.add(orderItem1);
        orderItems.add(orderItem2);

        OrderEntity order1 = new OrderEntity();
        order1.setId("1");
        order1.setUserId("user1");
        order1.setStatus("Pending");
        order1.setTotal(40.0f);

        orders.add(order1);
    }

    public OrderEntity save(OrderEntity order) {
        orders.add(order);
        return order;
    }

    public OrderEntity update(OrderEntity order, String orderId){
        System.out.println(orderId);
        OrderEntity orderchange = findById(orderId);
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

    public OrderEntity findById(String id) {
        
        for (int i = 0 ; i < orders.size();i++){
            
            if(orders.get(i).getId().equals(id) ){
                
                return orders.get(i);
            }
        }

        return null;
    }

    public List<OrderEntity> findByUserId(String userId) {
        return orders.stream().filter(order -> order.getUserId().equals(userId)).collect(Collectors.toList());
    }

    public List<OrderEntity> getAllOrders(){
        return orders;
    }

    public List<OrderEntity> getOrdenClient(String clientID) {
        List<OrderEntity> clientOrders = new ArrayList<>();
        for (int i = 0 ; i < orders.size();i++){
            String user = orders.get(i).getUserId();
            if ( user.equals(clientID)){
                clientOrders.add(orders.get(i));
            }
        }

        return clientOrders;
    }
}
