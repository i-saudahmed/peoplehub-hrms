package org.saud.peoplehub.exception;

import java.time.LocalDateTime;

import org.jboss.logging.Logger;
import org.saud.peoplehub.dto.response.ErrorResponse;

import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class GlobalExceptionHandler implements ExceptionMapper<ApplicationException> {

    @Context
    UriInfo uriInfo;

      private static final Logger LOG =
            Logger.getLogger(GlobalExceptionHandler.class);


    @Override
    public Response toResponse(ApplicationException exception) {

          LOG.warnf(
                "Application error. status=%d, message=%s, path=%s",
                exception.getStatus(),
                exception.getMessage(),
                uriInfo.getPath()
        );

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatus(Response.Status.CONFLICT.getStatusCode());
        errorResponse.setMessage(exception.getMessage());
        errorResponse.setPath(uriInfo.getPath());
        errorResponse.setTimestamp(LocalDateTime.now());

        return Response.status(Response.Status.CONFLICT).entity(errorResponse).build();

    }

}
