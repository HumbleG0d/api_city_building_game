package com.buil.api.exceptions;

/**
 * Custom exception thrown when an infrastructure is not found in the system.
 * This exception extends the RuntimeException and is used to indicate 
 * that an operation related to infrastructure has failed due to the 
 * infrastructure's absence.
 */
public class InfraestructureNotFoundException extends RuntimeException {

    /**
     * Constructs a new InfraestructureNotFoundException with the specified 
     * detail message.
     *
     * @param message the detail message which is saved for later retrieval 
     *                by the {@link #getMessage()} method.
     */
    public InfraestructureNotFoundException(String message) {
        super(message);
    }
}