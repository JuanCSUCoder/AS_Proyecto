package com.pedidos.modelo;

import java.util.List;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Order  {
    
    private Integer id;
    private String userId;
    private String status;
    private float total;
    private List<Product> products;

}
