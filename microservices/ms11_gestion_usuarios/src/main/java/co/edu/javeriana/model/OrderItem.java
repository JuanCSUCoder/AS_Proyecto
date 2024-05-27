package co.edu.javeriana.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class OrderItem {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  String id;

  int quantity;

  @ManyToOne
  @JoinColumn(name = "orderId", referencedColumnName = "id")
  @JsonIgnore
  @ToString.Exclude
  Order order;

  @ManyToOne
  @JoinColumn(name = "productId", referencedColumnName = "id")
  Product product;
}
