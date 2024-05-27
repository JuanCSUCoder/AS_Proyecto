package co.edu.javeriana.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import co.edu.javeriana.model.*;
import co.edu.javeriana.services.StatsService;

@Path("/stats")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class StatsController {

    @Inject
    StatsService statsService;

    @GET
    @Path("/products")
    public List<Product> getProducts() {
        return statsService.fetchProducts();
    }

    @GET
    @Path("/inventories")
    public List<Inventory> getInventories() {
        return statsService.fetchInventories();
    }

    @GET
    @Path("/orders")
    public List<Order> getOrders() {
        return statsService.fetchOrders();
    }

    @GET
    @Path("/reviews")
    public List<Review> getReviews() {
        return statsService.fetchReviews();
    }

    @GET
    @Path("/users")
    public List<User> getUsers() {
        return statsService.fetchUsers();
    }

    @GET
    public Map<String, Object> getStatistics() {
        Map<String, Object> statistics = new HashMap<>();

        // Productos
        List<Product> products = statsService.fetchProducts();
        statistics.put("totalProducts", products.size());

        // Inventarios
        // Producto con mayor stock
        List<Inventory> inventories = statsService.fetchInventories();
        Inventory maxStockProduct = null;
        int maxStock = Integer.MIN_VALUE;

        for (Inventory inventory : inventories) {
            if (inventory.getStock() > maxStock) {
                maxStock = inventory.getStock();
                maxStockProduct = inventory;
            }
        }

        // Producto con menor stock
        Inventory minStockProduct = null;
        int minStock = Integer.MAX_VALUE;

        for (Inventory inventory : inventories) {
            if (inventory.getStock() < minStock) {
                minStock = inventory.getStock();
                minStockProduct = inventory;
            }
        }

        String maxStockProductId = maxStockProduct.getId();
        String minStockProductId = minStockProduct.getId();

        // Buscar el producto con el ID de maxStockProduct y obtener su nombre
        String maxStockProductName = products.stream()
                .filter(product -> product.getId().equals(maxStockProductId))
                .map(Product::getName)
                .findFirst()
                .orElse("Producto no encontrado");

        // Buscar el producto con el ID de maxStockProduct y obtener su nombre
        String minStockProductName = products.stream()
                .filter(product -> product.getId().equals(minStockProductId))
                .map(Product::getName)
                .findFirst()
                .orElse("Producto no encontrado");

        statistics.put("maxStockProductName", maxStockProductName); // Maximo stock
        statistics.put("minStockProductName", minStockProductName); // Minimo stock

        // Órdenes
        List<Order> orders = statsService.fetchOrders();
        statistics.put("totalOrders", orders.size());

        // Promedio de productos por orden
        OptionalDouble averageProductsPerOrder = orders.stream()
                .mapToInt(order -> order.getItems().size())
                .average();
        statistics.put("averageProductsPerOrder",
                averageProductsPerOrder.isPresent() ? averageProductsPerOrder.getAsDouble() : 0);

        // Orden mas cara
        Order mostExpensiveOrder = orders.stream()
                .max(Comparator.comparingDouble(Order::getTotal))
                .orElse(null);
        statistics.put("mostExpensiveOrder", mostExpensiveOrder != null ? mostExpensiveOrder.getTotal() : "");

        // Orden mas economica
        Order cheapestOrder = orders.stream()
                .filter(order -> order.getTotal() > 0)
                .min(Comparator.comparingDouble(Order::getTotal))
                .orElse(null);
        statistics.put("cheapestOrder", cheapestOrder != null ? cheapestOrder.getTotal() : "");

        // Usuarios
        List<User> users = statsService.fetchUsers();
        statistics.put("totalUsers", users.size());

        // Valor total del inventario
        // Calcular el valor total de todas las órdenes
        double totalOrderValue = orders.stream()
                .mapToDouble(Order::getTotal)
                .sum();

        statistics.put("totalOrderValue", totalOrderValue);

        return statistics;
    }

}
