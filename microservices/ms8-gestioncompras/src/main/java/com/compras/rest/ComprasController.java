package com.compras.rest;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.compras.Services.OrderService;
import com.compras.model.Inventory;
import com.compras.model.Order;
import com.compras.model.OrderItem;
import com.compras.model.Product;
import com.compras.model.User;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.CookieParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Cookie;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/compras")
public class ComprasController {

    @Inject
    private OrderService orderService;

    @POST
    @Path("/realizar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response  realizarCompra(@QueryParam("userId") String userId,@CookieParam("sessionId") String sessionId, List<Product> products) {
        // Verificar los productos de la compra
        boolean productosDisponibles = verificarProductosDisponibles(products);
        Cookie sessionIdCookie = new Cookie("sessionId", sessionId);

        if (!productosDisponibles) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Algunos productos no están disponibles en el inventario.")
                    .build();
        }

        //Actualizar el inventario
        Map<String, Integer> idCountMap = new HashMap<>();

        // Contar la cantidad de veces que se repite cada ID
        for (Product product : products) {
            String id = product.getId();
            idCountMap.put(id, idCountMap.getOrDefault(id, 0) + 1);
        }


        // Mostrar las ID y su cantidad de repeticiones
        for (Map.Entry<String, Integer> entry : idCountMap.entrySet()) {
            System.out.println("ID: " + entry.getKey() + ", Repeticiones: " + entry.getValue());
            Inventory InvProd =  orderService.obtenerInventarioProducto(entry.getKey() );
            System.out.println("inventario conseguido" + InvProd);
            InvProd.setStock(InvProd.getStock()-entry.getValue());
            System.out.println("Actualizar inventario" + InvProd);
            orderService.actualizarInventario( entry.getKey(), InvProd);
        }
        

        //Crear la orden
        Order order = crearOrden(userId, products);

        Response orderResp =orderService.createOrder(order,sessionIdCookie);
        // Aquí podrías almacenar la orden en la base de datos u otro sistema de almacenamiento
        return orderResp;

    }

   private static boolean verificarProductosDisponibles(List<Product> products) {
        // Mapa para almacenar las IDs de los productos y su cantidad de repeticiones
    
        

        // Aquí podrías implementar la lógica para verificar la disponibilidad de los productos
        return true; // Por ahora, simplemente retornamos true
    }

    private void actualizarInventario(List<Product> products) {
        // Lógica para actualizar el inventario después de una compra
    }
    private Order crearOrden(String userId, List<Product> products) {
        // Lógica para crear una nueva orden
        Order order = new Order();
        User user = new User();
        user.setUserPod(userId);
        user.setId(userId);
        user.setProviderUrl("holaquetal");
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
