package com.compras.Services;



import jakarta.ejb.Stateless;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.Cookie;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.compras.model.Inventory;
import com.compras.model.Order;
import com.compras.model.Product;

@Stateless
public class OrderService {

    private static final String ORDER_SERVICE_URL = "http://pedidos:9080/gestionpedidos/api/orders/create";
    private static final String INVENTORY_URL = "http://inventarios:8080/products/{productId}/inventory";
    private static final String UPDATE_INVENTORY_URL = "http://inventarios:8080/inventories/{id}";
    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);
    public Response createOrder(Order order, Cookie sessionId) {
        Client client = ClientBuilder.newClient();
        logger.info("try resonese rder: ");
        try {
            Response response = client
                .target(ORDER_SERVICE_URL)
                .request(MediaType.APPLICATION_JSON).cookie(sessionId)
                .post(Entity.entity(order, MediaType.APPLICATION_JSON));

            return response;
        } finally {
            client.close();
        }
    }

    public Response verificarProductosDisponibles(List<Product> products) {
        Client client = ClientBuilder.newClient();

        try {
            Response response = client
                .target(INVENTORY_URL)
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(products, MediaType.APPLICATION_JSON));

            return response;
        } finally {
            client.close();
        }
    }

    public Inventory obtenerInventarioProducto(String productId) {
        Client client = ClientBuilder.newClient();

        try {
            Response response = client
                .target(INVENTORY_URL)
                .resolveTemplate("productId", productId)
                .request(MediaType.APPLICATION_JSON)
                .get();

            if (response.getStatus() == Response.Status.OK.getStatusCode()) {
                Inventory inventario = response.readEntity(Inventory.class);
                return inventario;
            } else {
                // Manejar el caso en que la solicitud no tenga éxito (por ejemplo, lanzar una excepción)
                throw new RuntimeException("No se pudo obtener el inventario del producto. Código de estado: " + response.getStatus());
            }
        } finally {
            client.close();
        }
    }

    public Response actualizarInventario(String inventoryId, Inventory inventory) {
        Client client = ClientBuilder.newClient();
    
        try {
            Response response = client
                .target(UPDATE_INVENTORY_URL)
                .resolveTemplate("id", inventoryId)
                .request(MediaType.APPLICATION_JSON)
                .put(Entity.entity(inventory, MediaType.APPLICATION_JSON));
    
            return response;
        } finally {
            client.close();
        }
    }
}
