package com.pedidos.modelo;

import java.util.List;

import jakarta.json.bind.annotation.JsonbTransient;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Order  {
    
    private Integer id;
    private String userId;
    private String status;
    private float total;
    @JsonbTransient
    private List<Product> products;

}
