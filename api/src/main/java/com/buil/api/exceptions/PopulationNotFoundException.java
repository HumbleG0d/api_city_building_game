package com.buil.api.exceptions;

/**
 * Custom exception thrown when a population is not found in the system.
 * This exception extends the RuntimeException and is used to indicate 
 * that an operation related to population has failed due to the 
 * population's absence.
 */
public class PopulationNotFoundException extends RuntimeException {

    /**
     * Constructs a new PopulationNotFoundException with the specified 
     * detail message.
     *
     * @param message the detail message which is saved for later retrieval 
     *                by the {@link #getMessage()} method.
     */
    public PopulationNotFoundException(String message) {
        super(message);
    }
}
