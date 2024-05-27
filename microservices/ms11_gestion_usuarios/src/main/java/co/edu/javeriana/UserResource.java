package co.edu.javeriana;

import co.edu.javeriana.repositories.OrderItemRepository;
import co.edu.javeriana.repositories.OrderRepository;
import co.edu.javeriana.repositories.ProductRepository;
import co.edu.javeriana.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;

import co.edu.javeriana.model.User;
import jakarta.ws.rs.Produces;
import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;

import jakarta.ws.rs.QueryParam;

@Path("/userresource")
public class UserResource {
    @Inject
    OrderRepository orderRepo;

    @Inject
    OrderItemRepository itemRepo;

    @Inject
    ProductRepository prodRepo;

    @Inject
    UserRepository userRepo;

    @GET
    @Path("/users")
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getUsers() {
        Iterable<User> usersIterable = userRepo.findAll();
        List<User> usersList = new ArrayList<>();
        usersIterable.forEach(usersList::add);
        return usersList;
    }

    @GET
    @Path("/user")
    @Produces(MediaType.APPLICATION_JSON)
    public User getUser(@QueryParam("userId") String userId) {
        return userRepo.findById(userId).get();
    }

    @DELETE
    @Path("/delete_user")
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteUser(@QueryParam("userId") String userId) {
        userRepo.deleteById(userId);
    }

    @PUT
    @Path("/update_user")
    @Produces(MediaType.APPLICATION_JSON)
    public User updateUser(User user) {
        return userRepo.save(user);
    }

    @POST
    @Path("/create_user")
    @Produces(MediaType.APPLICATION_JSON)
    public User createUser(User user) {
        return userRepo.save(user);
    }

}
