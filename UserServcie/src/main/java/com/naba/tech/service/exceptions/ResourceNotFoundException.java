package com.naba.tech.service.exceptions;


import lombok.Data;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException() {
        super("Resource Not Found on Server !!");
    }

    public ResourceNotFoundException(String message) {
        super( message );
    }

}
