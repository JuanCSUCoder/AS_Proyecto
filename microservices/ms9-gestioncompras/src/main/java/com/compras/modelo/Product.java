package com.compras.modelo;

import java.util.List;

import jakarta.json.bind.annotation.JsonbTransient;
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
    @JsonbTransient
    private Inventory inventory;
    @JsonbTransient
    private List<Review> reviews;
    @JsonbTransient
    private List<OrderItem> orderItems;
    
}
