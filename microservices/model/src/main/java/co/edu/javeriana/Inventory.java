package co.edu.javeriana;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Inventory {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  String id;

  String location;
  int stock;

  String productId;
}
