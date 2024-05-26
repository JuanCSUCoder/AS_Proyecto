package com.usuarios.service;

import com.usuarios.model.User;

import jakarta.ejb.Stateless;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Stateless
public class UserService {

    private static final String BASE_URL = "http://localhost:5010/DatosUsuarios/api/usuarios";
    private final Client client;
    private final WebTarget target;

    public UserService() {
        this.client = ClientBuilder.newClient();
        this.target = client.target(BASE_URL);
    }

    public List<User> getAllUsers() {
        Response response = target.request(MediaType.APPLICATION_JSON).get();
        if (response.getStatus() == 200) {
            User[] usersArray = response.readEntity(User[].class);
            return Arrays.asList(usersArray);
        } else {
            throw new RuntimeException("Failed to fetch users, HTTP error code: " + response.getStatus());
        }
    }

    public Optional<User> getUserById(String id) {
        Response response = target.path("/{id}")
                                  .resolveTemplate("id", id)
                                  .request(MediaType.APPLICATION_JSON)
                                  .get();
        if (response.getStatus() == 200) {
            return Optional.of(response.readEntity(User.class));
        } else if (response.getStatus() == 404) {
            return Optional.empty();
        } else {
            throw new RuntimeException("Failed to fetch user, HTTP error code: " + response.getStatus());
        }
    }

    public User createUser(User user) {
        Response response = target.request(MediaType.APPLICATION_JSON)
                                  .post(Entity.entity(user, MediaType.APPLICATION_JSON));
        if (response.getStatus() == 201) {
            return response.readEntity(User.class);
        } else {
            throw new RuntimeException("Failed to create user, HTTP error code: " + response.getStatus());
        }
    }

    public User updateUser(String id, User user) {
        Response response = target.path("/{id}")
                                  .resolveTemplate("id", id)
                                  .request(MediaType.APPLICATION_JSON)
                                  .put(Entity.entity(user, MediaType.APPLICATION_JSON));
        if (response.getStatus() == 200) {
            return response.readEntity(User.class);
        } else {
            throw new RuntimeException("Failed to update user, HTTP error code: " + response.getStatus());
        }
    }

    public void deleteUser(String id) {
        Response response = target.path("/{id}")
                                  .resolveTemplate("id", id)
                                  .request(MediaType.APPLICATION_JSON)
                                  .delete();
        if (response.getStatus() != 204) {
            throw new RuntimeException("Failed to delete user, HTTP error code: " + response.getStatus());
        }
    }

    public void close() {
        client.close();
    }

    public List<User> searchUsersByUserPod(String userPod) {
        Response response = target.path("/search")
                                  .queryParam("userPod", userPod)
                                  .request(MediaType.APPLICATION_JSON)
                                  .get();
        if (response.getStatus() == 200) {
            User[] usersArray = response.readEntity(User[].class);
            return Arrays.asList(usersArray);
        } else {
            throw new RuntimeException("Failed to search users, HTTP error code: " + response.getStatus());
        }
    }
}
