package co.edu.javeriana.controller;


import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.util.List;
import co.edu.javeriana.model.Inventory;
import co.edu.javeriana.services.InventoryService;
import co.edu.javeriana.model.Product;

@Path("/inventories")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class InventoryController {

    @Inject
    InventoryService inventoryService;

    @GET
    public List<Inventory> listAll() {
        return inventoryService.listAll();
    }

    @GET
    @Path("/{id}")
    public Inventory findById(@PathParam("id") String id) {
        return inventoryService.findById(id);
    }

    @POST
    public Inventory create(Inventory inventory) {
        return inventoryService.save(inventory);
    }

    @PUT
    @Path("/{id}")
    public Inventory update(@PathParam("id") String id, Inventory inventory) {
        inventory.setId(id);
        return inventoryService.save(inventory);
    }

    @DELETE
    @Path("/{id}")
    public void deleteById(@PathParam("id") String id) {
        inventoryService.deleteById(id);
    }

    @PUT
    @Path("/{inventoryId}/addProduct/{productId}")
    public Inventory addProductToInventory(@PathParam("inventoryId") String inventoryId, @PathParam("productId") String productId) {
        return inventoryService.addProductToInventory(inventoryId, productId);
    }

    @PUT
    @Path("/updateProduct/{productId}")
    public Product updateProduct(@PathParam("productId") String productId, Product updatedProduct) {
        return inventoryService.updateProduct(productId, updatedProduct);
    }

    @GET
    @Path("/products")
    public List<Product> listAllProducts() {
        return inventoryService.listAllProducts();
    }

}
