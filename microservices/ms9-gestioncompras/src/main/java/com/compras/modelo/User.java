package com.compras.modelo;

import java.util.List;


import jakarta.mail.Address;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class User {
    private String id;
    private String userPod;
    private String providerUrl;
    
    private Address address;
    private List<Order> orders;
}
