package co.edu.javeriana.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.ArrayList;
import java.util.List;

import co.edu.javeriana.model.Order;
import co.edu.javeriana.model.OrderItem;
import co.edu.javeriana.model.Product;
import co.edu.javeriana.repositories.ProductRepository;
import co.edu.javeriana.repositories.OrderRepository;
@ApplicationScoped
public class ProductService {
    @Inject
    ProductRepository productRepository;

    @Inject
    OrderRepository orderRepository;

    public Product getProductById(String productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }


    public Product createProduct(Product product) {
        // Puedes agregar validaciones aquí antes de guardar el producto en el repositorio
        return productRepository.save(product);
    }

    public void deleteProduct(String productId) {
        Product existingProduct = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        productRepository.delete(existingProduct);
    }

    public List<Product> listAllProducts() {
        return (List<Product>) productRepository.findAll();
    }


    public List<Product> getProductsByUserId(String userId) {
        List<Order> userOrders = orderRepository.findByUserId(userId);
        List<Product> products = new ArrayList<>();
        for (Order order : userOrders) {
            for (OrderItem item : order.getItems()) {
                products.add(item.getProduct());
            }
        }
        return products;
    }
    // Agrega más métodos según sea necesario para tu lógica de negocio
}
