package org.saud.peoplehub.exception;

import jakarta.ws.rs.core.Response;

public class UserAlreadyExistsException extends ApplicationException {

     public UserAlreadyExistsException(String message) {
        super(message, Response.Status.CONFLICT.getStatusCode());
    }
}
