package org.saud.peoplehub.controller;

import org.saud.peoplehub.dto.request.user.CreateUserRequest;
import org.saud.peoplehub.dto.response.UserResponse;
import org.saud.peoplehub.service.UserService;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;

@Path("/api/users")
public class UserController {

    @Inject
    UserService userService;

    @POST
    public Response createUser(@Valid CreateUserRequest request) {

        UserResponse response = userService.createUser(request);
        return Response.status(Response.Status.CREATED).entity(response).build();
    }

    @GET 
    @Path("/search")
    public Response searchUser(@QueryParam("query") String query) {
        
        UserResponse response = userService.getUserByUsernameOrEmailOrId(query);
        return Response.status(Response.Status.FOUND).entity(response).build();

    }

}
