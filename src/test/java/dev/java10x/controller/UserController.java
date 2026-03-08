package dev.java10x.controller;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/users")
public class UserController {

    @GET
    public String heloo(){
        return "Hello from UserController!";
    }

}
