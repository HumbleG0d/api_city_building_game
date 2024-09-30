package com.buil.api.exceptions;

/**
 * Custom exception thrown when a city is not found in the system.
 * This exception extends the RuntimeException and is used to indicate 
 * that an operation related to a city has failed due to the city's 
 * absence.
 */
public class CityNotFoundException extends RuntimeException {

  /**
   * Constructs a new CityNotFoundException with the specified detail message.
   *
   * @param message the detail message which is saved for later retrieval 
   *                by the {@link #getMessage()} method.
   */
  public CityNotFoundException(String message) {
      super(message);
  }
}