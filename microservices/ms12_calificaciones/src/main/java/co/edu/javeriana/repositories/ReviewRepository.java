package co.edu.javeriana.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import co.edu.javeriana.model.Review;

public interface ReviewRepository extends CrudRepository<Review, String> {
  Optional<Review> findById(String id);

  @Query("SELECT r FROM Review r WHERE r.product.id = ?1")
  List<Review> findByProductId(String productId);
}
