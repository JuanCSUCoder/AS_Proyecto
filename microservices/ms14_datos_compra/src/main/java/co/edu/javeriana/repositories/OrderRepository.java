package co.edu.javeriana.repositories;

import org.springframework.data.repository.CrudRepository;

import co.edu.javeriana.model.Order;

public interface OrderRepository extends CrudRepository<Order, String> {
  
}
