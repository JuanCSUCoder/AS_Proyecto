package co.edu.javeriana.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Arrays;
import java.util.List;

import co.edu.javeriana.model.Product;

import co.edu.javeriana.model.Inventory;
import co.edu.javeriana.repositories.InventoryRepository;
import co.edu.javeriana.repositories.ProductRepository;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public class StatsService{

    public List<Product> fetchProducts() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://inventarios:8080/products");

        Response response = target.request(MediaType.APPLICATION_JSON).get();

        if (response.getStatus() == 200) {
            Product[] products = response.readEntity(Product[].class);
            return Arrays.asList(products);
        } else {
            // Manejo de errores
            throw new RuntimeException("Failed to fetch products: " + response.getStatus());
        }
    }

    public List<Inventory> fetchInventories() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://inventarios:8080/inventories");

        Response response = target.request(MediaType.APPLICATION_JSON).get();

        if (response.getStatus() == 200) {
            Inventory[] inventories = response.readEntity(Inventory[].class);
            return Arrays.asList(inventories);
        } else {
            // Manejo de errores
            throw new RuntimeException("Failed to fetch inventories: " + response.getStatus());
        }
    }

    
}