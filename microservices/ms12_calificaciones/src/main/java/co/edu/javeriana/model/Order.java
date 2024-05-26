package co.edu.javeriana;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
public class Order {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  String id;

  String status;
  double total;

  String userId;

  @OneToOne
  @JoinColumn(name = "userId", referencedColumnName = "id")
  User user;
}
