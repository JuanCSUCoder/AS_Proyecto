package co.edu.javeriana.repositories;

import org.springframework.data.repository.CrudRepository;

import co.edu.javeriana.model.Product;

public interface ProductRepository extends CrudRepository<Product, String> {
  
}
