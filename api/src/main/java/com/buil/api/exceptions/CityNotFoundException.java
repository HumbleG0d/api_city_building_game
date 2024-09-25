package com.buil.api.exceptions;

public class CityNotFoundException extends RuntimeException {
  public CityNotFoundException(String message) {
   super(message);
  }
}