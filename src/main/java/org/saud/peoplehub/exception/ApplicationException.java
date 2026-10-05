package org.saud.peoplehub.exception;

import lombok.Getter;

@Getter 
public class ApplicationException extends RuntimeException {

    private final int status;

    public ApplicationException(String message, int status) {
        super(message);
        this.status = status;
    }
    
}
