package co.edu.javeriana.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "\"Order\"")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  String id;

  String status;
  double total;

  @ManyToOne
  @JoinColumn(name = "userId", referencedColumnName = "id")
  User user;

  @OneToMany(mappedBy = "order")
  List<OrderItem> items;
}
