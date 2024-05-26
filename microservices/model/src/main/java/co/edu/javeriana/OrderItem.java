package co.edu.javeriana;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class OrderItem {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  String id;

  int quantity;

  String orderId;

  @ManyToOne
  @JoinColumn(name = "orderId", columnDefinition = "id")
  Order order;

  String productId;

  @ManyToOne
  @JoinColumn(name = "productId", columnDefinition = "id")
  Product product;
}
