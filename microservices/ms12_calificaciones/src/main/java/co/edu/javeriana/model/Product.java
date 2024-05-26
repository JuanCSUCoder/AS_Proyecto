package co.edu.javeriana.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
public class Product {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  String id;

  String name;
  String descr;

  String imageURL;
  
  double price;

  @OneToOne(mappedBy = "product")
  @JsonIgnore
  Inventory inventory;

  @OneToMany(fetch = FetchType.LAZY)
  @JsonIgnore
  @ToString.Exclude
  List<Review> reviews;

  @OneToMany
  List<OrderItem> orderItems;
}