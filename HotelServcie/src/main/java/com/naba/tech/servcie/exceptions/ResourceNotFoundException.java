package com.naba.tech.servcie.exceptions;


public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super( message );
    }
}
