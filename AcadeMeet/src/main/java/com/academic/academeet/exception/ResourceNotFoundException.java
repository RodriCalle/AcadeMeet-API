package com.academic.academeet.exception;

<<<<<<< HEAD
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ResponseStatus(NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

=======
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
>>>>>>> origin/feature/student
    public ResourceNotFoundException() {
        super();
    }

<<<<<<< HEAD

=======
>>>>>>> origin/feature/student
    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

<<<<<<< HEAD
    public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("Resource %s not found for %s with value %s", resourceName, fieldName, fieldValue));
=======
    public ResourceNotFoundException(String resourceName, String field, Object fieldValue) {
        super(String.format("Resource %s not found for %s with value %s", resourceName, field, fieldValue));
>>>>>>> origin/feature/student
    }
}
