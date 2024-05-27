package com.compras.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Inventory {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  String id;

  String location;
  int stock;

  @OneToOne(fetch = FetchType.LAZY)
  @ToString.Exclude
  @JoinColumn(name = "productId", referencedColumnName = "id")
  Product product;
}