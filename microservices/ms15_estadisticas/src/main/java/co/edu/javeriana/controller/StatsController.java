package co.edu.javeriana.controller;


import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.util.List;
import co.edu.javeriana.model.Inventory;
import co.edu.javeriana.services.StatsService;
import co.edu.javeriana.model.Product;

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

}
