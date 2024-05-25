package com.pedidos.modelo;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Product {

    private String id ;
    private String name;
    private String desc;
    private float price;
    private int stock;
    
    private Inventory inventory;
    private List<Review> reviews;
    private List<OrderItem> orderItems;
    
}
