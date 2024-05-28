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

    private static final String BASE_URL = "http://ms11_gestion_usuarios:8080/userresource";
    private final Client client;
    private final WebTarget target;
    private final WebTarget createUserTarget;
    private final WebTarget updateUserTarget;
    private final WebTarget deleteUserTarget;
    private final WebTarget SearchUserTarget;
    public UserService() {
        this.client = ClientBuilder.newClient();
        this.target = client.target(BASE_URL);
        this.createUserTarget = target.path("/create_user");
        this.updateUserTarget = target.path("/update_user");
        this.deleteUserTarget = target.path("/delete_user");
        this.SearchUserTarget = target.path("/user_by_pod");
    }

    public List<User> getAllUsers() {
        Response response = target.path("/users").request(MediaType.APPLICATION_JSON).get();
        if (response.getStatus() == 200) {
            User[] usersArray = response.readEntity(User[].class);
            return Arrays.asList(usersArray);
        } else {
            throw new RuntimeException("Failed to fetch users, HTTP error code: " + response.getStatus());
        }
    }

    public Optional<User> getUserById(String id) {
        Response response = target.path("/user")
                                  .queryParam("userId", id)
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
        Response response = createUserTarget.request(MediaType.APPLICATION_JSON)
                                            .post(Entity.entity(user, MediaType.APPLICATION_JSON));

        System.out.println("la resposne es "+response);
        if (response.getStatus() == 200) {
            return response.readEntity(User.class);
        } else {
            throw new RuntimeException("Failed to create user, HTTP error code: " + response.getStatus());
        }
    }

    public User updateUser(String id, User user) {
        Response response = updateUserTarget.request(MediaType.APPLICATION_JSON)
                                            .put(Entity.entity(user, MediaType.APPLICATION_JSON));
        if (response.getStatus() == 200) {
            return response.readEntity(User.class);
        } else {
            throw new RuntimeException("Failed to update user, HTTP error code: " + response.getStatus());
        }
    }

    public void deleteUser(String id) {
        Response response = deleteUserTarget.queryParam("userId", id)
                                            .request()
                                            .delete();
        if (response.getStatus() != 204) { // 204 No Content is the expected response for successful deletion
            throw new RuntimeException("Failed to delete user, HTTP error code: " + response.getStatus());
        }
    }

    public void close() {
        client.close();
    }

    public User searchUsersByUserPod(String userPod) {
        Response response = SearchUserTarget
                                  .queryParam("userPod", userPod)
                                  .request(MediaType.APPLICATION_JSON)
                                  .get();
        if (response.getStatus() == 200) {
            return response.readEntity(User.class);
        } else {
            throw new RuntimeException("Failed to search users, HTTP error code: " + response.getStatus());
        }
    }
}
