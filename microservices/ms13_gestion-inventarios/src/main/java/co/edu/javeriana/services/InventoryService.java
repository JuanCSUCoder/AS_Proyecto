package co.edu.javeriana.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;

import co.edu.javeriana.model.Product;

import co.edu.javeriana.model.Inventory;
import co.edu.javeriana.repositories.InventoryRepository;
import co.edu.javeriana.repositories.ProductRepository;

@ApplicationScoped
public class InventoryService{

    @Inject
    InventoryRepository inventoryRepository;

    @Inject
    ProductRepository productRepository;

    public List<Inventory> listAll() {
        return (List<Inventory>) inventoryRepository.findAll();
    }

    public Inventory findById(String id) {
        return inventoryRepository.findById(id).orElse(null);
    }

    public Inventory save(Inventory inventory) {
        return inventoryRepository.save(inventory);
    }

    public void deleteById(String id) {
        inventoryRepository.deleteById(id);
    }

    public Inventory addProductToInventory(String inventoryId, String productId) {
        Inventory inventory = inventoryRepository.findById(inventoryId).orElseThrow(() -> new RuntimeException("Inventory not found"));
        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));
        
        inventory.setProduct(product);
        product.setInventory(inventory);
        
        inventoryRepository.save(inventory);
        productRepository.save(product);
        
        return inventory;
    }

    public Product updateProduct(String productId, Product updatedProduct) {
        Product existingProduct = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));
        
        existingProduct.setName(updatedProduct.getName());
        existingProduct.setDescr(updatedProduct.getDescr());
        existingProduct.setImageURL(updatedProduct.getImageURL());
        existingProduct.setPrice(updatedProduct.getPrice());
        
        return productRepository.save(existingProduct);
    }

    public List<Product> listAllProducts() {
        return (List<Product>) productRepository.findAll();
    }

}