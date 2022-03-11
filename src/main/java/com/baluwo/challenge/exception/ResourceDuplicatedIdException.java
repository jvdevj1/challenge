package com.baluwo.challenge.exception;

public class ResourceDuplicatedIdException extends RuntimeException {
    private static final String RESOURCE_DUPLICATED_ID = "Could not create %s with id %s because it already exists.";

    public ResourceDuplicatedIdException(String resourceName, Integer resourceId) {
        super(String.format(RESOURCE_DUPLICATED_ID, resourceName, resourceId));
    }
}
