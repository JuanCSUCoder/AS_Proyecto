package com.compras.model;

import java.util.List;

import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "\"User\"")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  String id;

  String userPod;
  String providerUrl;
  
  // @JsonbTransient
  // @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
  // @ToString.Exclude
  // List<Order> orders;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
  @JsonbTransient
  @ToString.Exclude
  List<Review> reviews;
}

