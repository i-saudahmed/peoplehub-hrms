package org.saud.peoplehub.exception;

import jakarta.ws.rs.core.Response;

public class UserNotFoundException extends ApplicationException {

    public UserNotFoundException(String message) {
        super(message, Response.Status.NOT_FOUND.getStatusCode());
    }
}