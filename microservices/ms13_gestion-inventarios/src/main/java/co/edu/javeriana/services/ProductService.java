package co.edu.javeriana.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

import co.edu.javeriana.model.Order;
import co.edu.javeriana.model.Product;
import co.edu.javeriana.repositories.ProductRepository;

@ApplicationScoped
public class ProductService {
    @Inject
    ProductRepository productRepository;

    public Product getProductById(String productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    // public List<Order> getOrdersByProduct(String productId) {
    //     // Implementa la lógica para obtener los pedidos relacionados con el producto
    //     // Puedes acceder al repositorio de pedidos para realizar consultas
    //     // Este es solo un ejemplo de cómo podrías hacerlo
    //     // return orderRepository.findByProductId(productId);
    //     throw new UnsupportedOperationException("Not implemented yet");
    // }

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

    // Agrega más métodos según sea necesario para tu lógica de negocio
}
