package  com.pedidos.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.json.bind.annotation.JsonbTransient;
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
  
  @JsonIgnore
  @OneToOne(mappedBy = "product")
  Inventory inventory;

  @JsonIgnore
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
  List<Review> reviews;

  @JsonIgnore
  @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
  List<OrderItem> orderItems;
}