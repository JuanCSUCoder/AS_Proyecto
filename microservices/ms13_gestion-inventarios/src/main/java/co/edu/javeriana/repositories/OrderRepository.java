package co.edu.javeriana.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import co.edu.javeriana.model.Order;

public interface OrderRepository extends CrudRepository<Order, String> {

    @Query("SELECT o FROM Order o WHERE o.user.id = ?1")
    List<Order> findByUserId(String userId);
}