package org.saud.peoplehub.exception;

import jakarta.ws.rs.core.Response;

public class InvalidRequestException extends ApplicationException {

     public InvalidRequestException(String message) {
        super(message, Response.Status.BAD_REQUEST.getStatusCode());
    }
}
