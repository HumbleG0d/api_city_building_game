package com.buil.api.exceptions;

/**
 * Custom exception thrown when a resource is not found in the system.
 * This exception extends the RuntimeException and is used to indicate 
 * that an operation related to resource management has failed due to 
 * the resource's absence.
 */
public class ResourceNotFoundException extends RuntimeException {

    /**
     * Constructs a new ResourceNotFoundException with the specified 
     * detail message.
     *
     * @param message the detail message which is saved for later retrieval 
     *                by the {@link #getMessage()} method.
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
