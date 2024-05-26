package com.compras.model;

import java.util.List;

import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  String id;

  String name;
  String descr;

  String imageURL;
  
  double price;
  
  @JsonbTransient
  @OneToOne
  Inventory inventory;

  @JsonbTransient
  @OneToMany
  List<Review> reviews;

  @JsonbTransient
  @OneToMany
  List<OrderItem> orderItems;
}