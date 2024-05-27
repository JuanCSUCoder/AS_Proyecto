package co.edu.javeriana.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import co.edu.javeriana.model.Inventory;

public interface InventoryRepository extends CrudRepository<Inventory, String> {
    @Query("SELECT i FROM Inventory i WHERE i.product.id = ?1")
    Optional<Inventory> findByProductId(String productId);
}
