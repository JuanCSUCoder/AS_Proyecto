package com.compras.Services;



import jakarta.ejb.Stateless;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.compras.model.Order;
import com.compras.model.Product;

@Stateless
public class OrderService {

    private static final String ORDER_SERVICE_URL = "http://pedidos:9080/gestionpedidos/api/orders/create";
    private static final String VERIFY_PRODUCTS_URL = "http://localhost:5010/gestionproductos/api/products/verify";
    private static final String UPDATE_INVENTORY_URL = "http://localhost:5010/gestioninventario/api/inventory/update";
    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);
    public Response createOrder(Order order) {
        Client client = ClientBuilder.newClient();
        logger.info("try resonese rder: ");
        try {
            Response response = client
                .target(ORDER_SERVICE_URL)
                .request(MediaType.APPLICATION_JSON)
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
                .target(VERIFY_PRODUCTS_URL)
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(products, MediaType.APPLICATION_JSON));

            return response;
        } finally {
            client.close();
        }
    }

    public Response actualizarInventario(List<Product> products) {
        Client client = ClientBuilder.newClient();

        try {
            Response response = client
                .target(UPDATE_INVENTORY_URL)
                .request(MediaType.APPLICATION_JSON)
                .put(Entity.entity(products, MediaType.APPLICATION_JSON));

            return response;
        } finally {
            client.close();
        }
    }
}
