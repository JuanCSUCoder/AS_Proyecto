package com.usuarios.service;

import com.usuarios.model.UserProfile;

import jakarta.ejb.Stateless;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Cookie;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Stateless
public class UserProfileService {

    private static final String BASE_URL = "http://ms5_pods:5006/user";
    private final Client client;
    private final WebTarget target;


    public UserProfileService() {
        this.client = ClientBuilder.newClient();
        this.target = client.target(BASE_URL);
    }

    // public List<UserProfile> getAllUserProfiles() {
    //     Response response = target.path("/userprofiles").request(MediaType.APPLICATION_JSON).get();
    //     if (response.getStatus() == 200) {
    //         UserProfile[] userProfilesArray = response.readEntity(UserProfile[].class);
    //         return Arrays.asList(userProfilesArray);
    //     } else {
    //         throw new RuntimeException("Failed to fetch user profiles, HTTP error code: " + response.getStatus());
    //     }
    // }

    public Optional<UserProfile> getUserProfileById(Cookie sessionId) {
        Response response = null;
        try {
            response = target
                              .request(MediaType.APPLICATION_JSON)
                              .cookie(sessionId)
                              .get();
    
            System.out.println("La respuesta es la siguiente: " + response.getStatus());
            
            if (response.getStatus() == 200) {
                String responseBody = response.readEntity(String.class);
                System.out.println("Contenido de la respuesta: " + responseBody);
                
                // Use a JSON parser to map responseBody to UserProfile
                UserProfile userProfile = parseUserProfile(responseBody);
                return Optional.of(userProfile);
            } else if (response.getStatus() == 404) {
                return Optional.empty();
            } else {
                throw new RuntimeException("Failed to fetch user profile, HTTP error code: " + response.getStatus());
            }
        } finally {
            if (response != null) {
                response.close();
            }
        }
    }
    
    private UserProfile parseUserProfile(String json) {
        Jsonb jsonb = JsonbBuilder.create();
        return jsonb.fromJson(json, UserProfile.class);
    }

    //crear usuario

    public String createUserProfile(UserProfile userProfile, Cookie sessionId) {
        Response response = null;
        try {
            response = target.request()
                             .cookie(sessionId)
                             .put(Entity.entity(userProfile, MediaType.APPLICATION_JSON));
        
            System.out.println("La respuesta es la siguiente: " + response.getStatus());
            String responseBody = response.readEntity(String.class);
            System.out.println("Contenido de la respuesta: " + responseBody);
            
            if (response.getStatus() == 200) {
                return "OK";
            } else {
                throw new RuntimeException("Failed to create user profile, HTTP error code: " + response.getStatus());
            }
        } finally {
            if (response != null) {
                response.close();
            }
        }
    }


    //actualizar ususario
    public UserProfile updateUserProfile(String id, UserProfile userProfile, Cookie sessionId) {
        Response response = target.request(MediaType.APPLICATION_JSON)
                                            .cookie(sessionId)
                                            .put(Entity.entity(userProfile, MediaType.APPLICATION_JSON));
        if (response.getStatus() == 200) {
            return response.readEntity(UserProfile.class);
        } else {
            throw new RuntimeException("Failed to update user profile, HTTP error code: " + response.getStatus());
        }
    }

    public void deleteUserProfile(String id) {
        Response response = target.queryParam("userProfileId", id)
                                            .request()
                                            .delete();
        if (response.getStatus() != 204) { // 204 No Content is the expected response for successful deletion
            throw new RuntimeException("Failed to delete user profile, HTTP error code: " + response.getStatus());
        }
    }

    public void close() {
        client.close();
    }
}
