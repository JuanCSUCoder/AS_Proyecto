package co.edu.javeriana.repositories;

import org.springframework.data.repository.CrudRepository;

import co.edu.javeriana.model.OrderItem;

public interface OrderItemRepository extends CrudRepository<OrderItem, String> {
  
}
