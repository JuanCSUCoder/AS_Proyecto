package com.compras.modelo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderItem {

    private String id;
    private String orderId;
    private String productId;
    private int quantity;
}
