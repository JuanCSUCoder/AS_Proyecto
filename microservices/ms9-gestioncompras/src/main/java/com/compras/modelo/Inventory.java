package com.compras.modelo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Inventory {
    private String id;
    private String productId;
    private String location;
    private int quantity;
    
}
