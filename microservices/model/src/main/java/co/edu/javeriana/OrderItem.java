package co.edu.javeriana;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class OrderItem {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  String id;

  int quantity;

  String orderId;
  String productId;
}
