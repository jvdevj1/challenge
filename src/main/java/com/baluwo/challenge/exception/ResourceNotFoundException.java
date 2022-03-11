package com.baluwo.challenge.exception;

public class ResourceNotFoundException extends RuntimeException {
    private static final String RESOURCE_NOT_FOUND = "Resource %s with id %s not found.";

    public ResourceNotFoundException(String resourceName, Integer resourceId) {
        super(String.format(RESOURCE_NOT_FOUND, resourceName, resourceId));
    }
}
