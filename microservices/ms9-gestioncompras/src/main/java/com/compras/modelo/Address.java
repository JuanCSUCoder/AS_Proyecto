package com.compras.modelo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Address {
    private String id ;
    private String userId;
    private String street;
    private String city;
    private String state;
    private String country;
    private String zip;
    
    private User user;
}
