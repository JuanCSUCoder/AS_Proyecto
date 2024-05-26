package co.edu.javeriana;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Inventory {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  String id;

  String location;
  int stock;

  String productId;

  @OneToOne
  @JoinColumn(name = "productId", referencedColumnName = "id")
  Product product;
}
