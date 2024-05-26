package co.edu.javeriana.repositories;

import org.springframework.data.repository.CrudRepository;
import co.edu.javeriana.model.Inventory;

public interface InventoryRepository extends CrudRepository<Inventory, String> {
}
