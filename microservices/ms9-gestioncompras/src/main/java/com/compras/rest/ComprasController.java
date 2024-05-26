package com.compras.rest;


import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;

import com.compras.model.CompraRequest;
import com.compras.model.Order;
import com.compras.model.OrderItem;
import com.compras.model.Product;
import com.compras.model.User;

@Path("/compras")
public class ComprasController {


    @POST
    @Path("/realizar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Order realizarCompra(@QueryParam("userId") String userId, List<Product> products) {
        // Verificar los productos de la compra
        boolean productosDisponibles = verificarProductosDisponibles(products);
        
        // if (!productosDisponibles) {
        //     return null;
        // }

        // Actualizar el inventario
        // actualizarInventario(products);

        //Crear la orden
        Order order = crearOrden(userId, products);

        // Aquí podrías almacenar la orden en la base de datos u otro sistema de almacenamiento
 

        return order;
    }

    private boolean verificarProductosDisponibles(List<Product> products) {
        // Lógica para verificar si todos los productos están disponibles en el inventario
        return true; // Aquí deberías implementar la lógica adecuada
    }

    private void actualizarInventario(List<Product> products) {
        // Lógica para actualizar el inventario después de una compra
    }
    private Order crearOrden(String userId, List<Product> products) {
        // Lógica para crear una nueva orden
        Order order = new Order();
        User user = new User();
        user.setUserPod(userId);
        order.setUser(user);
        order.setStatus("En Proceso");
        
        List<OrderItem> orderItems = new ArrayList<>();
        double total = 0;
        
        for (Product prod : products) {
            OrderItem item = new OrderItem();
            item.setProduct(prod);
            item.setQuantity(1);
            orderItems.add(item);
            total += prod.getPrice();
        }
        
        order.setItems(orderItems);
        order.setTotal(total);
        
        // Configurar la orden con los datos del usuario y los productos
        return order;
    }
    
}
