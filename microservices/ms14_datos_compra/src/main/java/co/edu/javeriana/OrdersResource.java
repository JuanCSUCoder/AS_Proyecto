package co.edu.javeriana;

import co.edu.javeriana.model.Order;
import co.edu.javeriana.model.OrderItem;
import co.edu.javeriana.repositories.OrderItemRepository;
import co.edu.javeriana.repositories.OrderRepository;
import co.edu.javeriana.repositories.ProductRepository;
import co.edu.javeriana.repositories.UserRepository;
import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

@Path("/")
public class OrdersResource {

    @Inject
    OrderRepository repo;

    @Inject
    OrderItemRepository itemRepo;

    @Inject
    ProductRepository prodRepo;

    @Inject
    UserRepository userRepo;

    @POST
    @Path("/order")
    @Produces(MediaType.APPLICATION_JSON)
    public Order placeOrder(Order order) {
        order.setStatus("PENDING");
        order.setUser(userRepo.findById(order.getUser().getId()).get());

        Order finishOrder = repo.save(order);
        double total = 0.0;

        for (OrderItem item : order.getItems()) {
            item.setOrder(finishOrder);
            item.setProduct(prodRepo.findById(item.getProduct().getId()).get());
            OrderItem finishItem = itemRepo.save(item);
            total += finishItem.getProduct().getPrice() * finishItem.getQuantity();
        }

        finishOrder.setTotal(total);

        finishOrder = repo.save(finishOrder);

        return finishOrder;
    }

    @POST
    @Path("/payorder")
    @Produces(MediaType.APPLICATION_JSON)
    public Order registerOrderPayment(@QueryParam("orderid") String orderid) {
        Order order = repo.findById(orderid).get();

        order.setStatus("PAID");

        repo.save(order);
        return order;
    }

    @POST
    @Path("/ondeliver")
    @Produces(MediaType.APPLICATION_JSON)
    public Order registerOrderOnDelivery(@QueryParam("orderid") String orderid) {
        Order order = repo.findById(orderid).get();

        order.setStatus("ON_DELIVERY");

        repo.save(order);
        return order;
    }

    @POST
    @Path("/delivered")
    @Produces(MediaType.APPLICATION_JSON)
    public Order registerOrderDelivered(@QueryParam("orderid") String orderid) {
        Order order = repo.findById(orderid).get();

        order.setStatus("FINISHED");

        repo.save(order);
        return order;
    }
}
