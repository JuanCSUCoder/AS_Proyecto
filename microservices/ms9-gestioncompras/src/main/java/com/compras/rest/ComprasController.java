package com.compras.rest;

import com.compras.modelo.Order;
import com.compras.modelo.OrderItem;
import com.compras.modelo.Product;
import com.compras.modelo.User;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/compras")
public class ComprasController {

    @POST
    @Path("/realizar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response realizarCompra(User user, List<Product> products) {
        // Verificar los productos de la compra
        boolean productosDisponibles = verificarProductosDisponibles(products);
        
        if (!productosDisponibles) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Algunos productos no están disponibles en el inventario.")
                    .build();
        }

        // Actualizar el inventario
        actualizarInventario(products);

        // Crear la orden
        Order order = crearOrden(user, products);

        // Aquí podrías almacenar la orden en la base de datos u otro sistema de almacenamiento

        return Response.status(Response.Status.OK)
                .entity(order)
                .build();
    }

    private boolean verificarProductosDisponibles(List<Product> products) {
        // Lógica para verificar si todos los productos están disponibles en el inventario
        return true; // Aquí deberías implementar la lógica adecuada
    }

    private void actualizarInventario(List<Product> products) {
        // Lógica para actualizar el inventario después de una compra
    }

    private Order crearOrden(User user, List<Product> products) {
        // Lógica para crear una nueva orden
        Order order = new Order();
        // Configurar la orden con los datos del usuario y los productos
        return order;
    }
}
