package org.saud.peoplehub.exception;

import java.time.LocalDateTime;

import org.saud.peoplehub.dto.response.ErrorResponse;

import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class GlobalExceptionHandler implements ExceptionMapper<UserAlreadyExistsException> {

    @Context
    UriInfo uriInfo;

    @Override
    public Response toResponse(UserAlreadyExistsException exception) {

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatus(Response.Status.CONFLICT.getStatusCode());
        errorResponse.setMessage(exception.getMessage());
        errorResponse.setPath(uriInfo.getPath());
        errorResponse.setTimestamp(LocalDateTime.now());

        return Response.status(Response.Status.CONFLICT).entity(errorResponse).build();

    }

}
