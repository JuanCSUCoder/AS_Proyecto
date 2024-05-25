package com.pedidos.modelo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Review {
    private String id ;
    private int score;
    private String productId;
    
    private Product product;
}
