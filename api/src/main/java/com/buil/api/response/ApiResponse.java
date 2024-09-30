package com.buil.api.response;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * A generic class that represents a standard API response structure.
 * It encapsulates a message and the data returned by an API call.
 *
 * @param <T> the type of the data being returned in the response.
 */
@Data
@AllArgsConstructor
public class ApiResponse<T> {
      /**
     * A message describing the outcome of the API call (e.g., success, error).
     */
    private String message;
    private T data;
}
