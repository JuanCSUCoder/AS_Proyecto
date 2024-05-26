package co.edu.javeriana.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.util.List;
import co.edu.javeriana.model.Product;
import co.edu.javeriana.services.ProductService;

@Path("/products")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProductController {

    @Inject
    ProductService productService;

    @GET
    public List<Product> listAll() {
        return productService.listAllProducts();
    }

    @GET
    @Path("/{productId}")
    public Product getProductById(@PathParam("productId") String productId) {
        return productService.getProductById(productId);
    }

    @POST
    public Product createProduct(Product product) {
        return productService.createProduct(product);
    }

    @DELETE
    @Path("/{productId}")
    public void deleteProduct(@PathParam("productId") String productId) {
        productService.deleteProduct(productId);
    }

    

    // Agrega más endpoints según sea necesario para la gestión de productos
}
