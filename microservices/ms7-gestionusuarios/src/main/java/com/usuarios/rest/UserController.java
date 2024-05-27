package com.usuarios.rest;

import com.usuarios.model.User;
import com.usuarios.service.UserService;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserController {

    private static List<User> users = new ArrayList<>();  // Simulación de base de datos en memoria
    
    @Inject
    private UserService userService;

    // Crear un nuevo usuario
    @POST
    public Response createUser(User user) {
        try {
            User createdUser = userService.createUser(user);
            System.out.println("created user es "+createdUser);
            return Response.status(Response.Status.CREATED).entity(createdUser).build();
        } catch (RuntimeException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    // Obtener todos los usuarios
    @GET
    public Response getAllUsers() {
        //conseguir usuarios
        List<User> AllUsers = userService.getAllUsers();

        return Response.ok(AllUsers).build();
    }

    // Obtener un usuario por ID
    @GET
    @Path("/{id}")
    public Response getUserById(@PathParam("id") String id) {
        Optional<User> usuarioById = userService.getUserById(id);

        if (usuarioById.isPresent()) {
            return Response.ok(usuarioById.get()).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    // Actualizar un usuario existente
    @PUT
    @Path("/{id}")
    public Response updateUser(@PathParam("id") String id, User user) {
        try {
            User updatedUser = userService.updateUser(id, user);
            return Response.ok(updatedUser).build();
        } catch (RuntimeException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    // Eliminar un usuario
    @DELETE
    @Path("/{id}")
    public Response deleteUser(@PathParam("id") String id) {
        try {
            userService.deleteUser(id);
            return Response.noContent().build(); // 204 No Content
        } catch (RuntimeException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/search")
    public Response searchUsersByUserPod(@QueryParam("userPod") String userPod) {
        List<User> users = userService.searchUsersByUserPod(userPod);
        return Response.ok(users).build();
    }
}
