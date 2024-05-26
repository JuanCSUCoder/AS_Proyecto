package co.edu.javeriana.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import co.edu.javeriana.model.Review;

public interface ReviewRepository extends CrudRepository<Review, String> {
  Optional<Review> findById(String id);

  List<Review> findByProductId(String productId);
}
