package com.devsu.client.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException {

    private final long id;

    public ResourceNotFoundException() {
        super();
        this.id = -1;
    }

    public ResourceNotFoundException(long id, String message) {
        super(message);
        this.id = id;
    }
}
