package com.usuarios.rest;

import com.usuarios.model.UserProfile;
import com.usuarios.service.UserProfileService;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Cookie;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Optional;

@Path("/userprofiles")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserProfileController {

    @Inject
    private UserProfileService userProfileService;

    // Crear un nuevo perfil de usuario
    @POST
    public Response createUserProfile(UserProfile userProfile,
                                      @CookieParam("sessionId") String sessionId,
                                      @CookieParam("providerUrl") String providerUrl) {
        try {
            // Crear y agregar cookies
            System.out.println("session id es "+sessionId);
            Cookie sessionIdCookie = new Cookie("sessionId", sessionId);
            Cookie providerUrlCookie = new Cookie("providerUrl", providerUrl);
            
            String createdUserProfile = userProfileService.createUserProfile(userProfile, sessionIdCookie);
            return Response.status(Response.Status.CREATED).entity(createdUserProfile).build();
        } catch (RuntimeException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    // Obtener todos los perfiles de usuario
    // @GET
    // public Response getAllUserProfiles() {
    //     List<UserProfile> allUserProfiles = userProfileService.getAllUserProfiles();
    //     return Response.ok(allUserProfiles).build();
    // }

    // Obtener un perfil de usuario por ID
    @GET
    @Path("/{id}")
    public Response getUserProfileById(@PathParam("id") String id) {
        Cookie sessionIdCookie = new Cookie("sessionId", id);
        Optional<UserProfile> userProfileById = userProfileService.getUserProfileById(sessionIdCookie);

        if (userProfileById.isPresent()) {
            return Response.ok(userProfileById.get()).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("/email/{id}")
    public Response getUserProfileByEmail(@PathParam("id") String id) {
        Cookie sessionIdCookie = new Cookie("sessionId", id);
        Optional<UserProfile> userProfileById = userProfileService.getUserProfileById(sessionIdCookie);

        if (userProfileById.isPresent()) {
            // Retrieve email from UserProfile
            String email = userProfileById.get().getEmail();
            // Return email in response
            return Response.ok(email).type(MediaType.TEXT_PLAIN).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    // Actualizar un perfil de usuario existente
    @PUT
    @Path("/{id}")
    public Response updateUserProfile(@PathParam("id") String id, 
                                      UserProfile userProfile,
                                      @CookieParam("sessionId") String sessionId,
                                      @CookieParam("providerUrl") String providerUrl) {
        try {
            // Crear y agregar cookies
            System.out.println("session id es "+sessionId);
            Cookie sessionIdCookie = new Cookie("sessionId", sessionId);
            Cookie providerUrlCookie = new Cookie("providerUrl", providerUrl);
            
            String createdUserProfile = userProfileService.createUserProfile(userProfile, sessionIdCookie);
            return Response.status(Response.Status.CREATED).entity(createdUserProfile).build();
        } catch (RuntimeException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    // Eliminar un perfil de usuario
    // @DELETE
    // @Path("/{id}")
    // public Response deleteUserProfile(@PathParam("id") String id) {
    //     try {
    //         userProfileService.deleteUserProfile(id);
    //         return Response.noContent().build(); // 204 No Content
    //     } catch (RuntimeException e) {
    //         return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
    //     }
    // }
}
