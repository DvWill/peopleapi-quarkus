package dev.java10x.controller;

import domain.Users;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import service.UserService;

import java.util.UUID;

@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserController {

    private final UserService userService;

    @Inject
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @POST
    @Transactional
    public Response createUser(Users users) {
        return Response.ok(userService.createUser(users)).build();
    }

    @GET
    public Response getUsers() {
        return Response.ok(userService.getUsers()).build();
    }

    @GET
    @Path("/{id}")
    public Response getUserById(@PathParam("id") UUID id) {
        Users user = userService.getUserById(id);

        if(user == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok(user).build();
    }



    @PUT
    @Path("/{id}")
    @Transactional
    public Users updateUsers(UUID id, Users usersData) {
        Users user = Users.findById(id);

        if(user == null){
            return null;
        }

        user.username = usersData.username;
        user.email = usersData.email;

        return user;
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public boolean deleteUser(UUID id) {
        Users user = Users.findById(id);

        if(user == null){
            return false;
        }

        user.delete();
        return true;
    }
}