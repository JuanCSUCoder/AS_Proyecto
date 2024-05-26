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
        user.setId(java.util.UUID.randomUUID().toString());  // Generar un ID único
        users.add(user);

        // aqui se deberia comunicar con logica de datos

        
        
        return Response.status(Response.Status.CREATED).entity(user).build();
    }

    // Obtener todos los usuarios
    @GET
    public Response getAllUsers() {
        //conseguir usuarios
        //userService.getAllUsers();

        return Response.ok(users).build();
    }

    // Obtener un usuario por ID
    @GET
    @Path("/{id}")
    public Response getUserById(@PathParam("id") String id) {
        Optional<User> userOpt = users.stream().filter(u -> u.getId().equals(id)).findFirst();
        if (userOpt.isPresent()) {
            return Response.ok(userOpt.get()).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    // Actualizar un usuario existente
    @PUT
    @Path("/{id}")
    public Response updateUser(@PathParam("id") String id, User updatedUser) {
        Optional<User> userOpt = users.stream().filter(u -> u.getId().equals(id)).findFirst();
        if (userOpt.isPresent()) {
            User existingUser = userOpt.get();
            existingUser.setUserPod(updatedUser.getUserPod());
            existingUser.setProviderUrl(updatedUser.getProviderUrl());
            return Response.ok(existingUser).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    // Eliminar un usuario
    @DELETE
    @Path("/{id}")
    public Response deleteUser(@PathParam("id") String id) {
        Optional<User> userOpt = users.stream().filter(u -> u.getId().equals(id)).findFirst();
        if (userOpt.isPresent()) {
            users.remove(userOpt.get());
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("/search")
    public Response searchUsersByUserPod(@QueryParam("userPod") String userPod) {
        List<User> users = userService.searchUsersByUserPod(userPod);
        return Response.ok(users).build();
    }
}
