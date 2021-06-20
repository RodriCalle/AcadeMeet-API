package com.academic.academeet.exception;

<<<<<<< HEAD
<<<<<<< HEAD
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ResponseStatus(NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

=======
=======
>>>>>>> origin/feature/UserPlansImpl
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
<<<<<<< HEAD
public class ResourceNotFoundException extends RuntimeException {
>>>>>>> origin/feature/student
=======
public class ResourceNotFoundException extends RuntimeException{

>>>>>>> origin/feature/UserPlansImpl
    public ResourceNotFoundException() {
        super();
    }

<<<<<<< HEAD
<<<<<<< HEAD

=======
>>>>>>> origin/feature/student
=======
>>>>>>> origin/feature/UserPlansImpl
    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
<<<<<<< HEAD

<<<<<<< HEAD
    public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("Resource %s not found for %s with value %s", resourceName, fieldName, fieldValue));
=======
    public ResourceNotFoundException(String resourceName, String field, Object fieldValue) {
        super(String.format("Resource %s not found for %s with value %s", resourceName, field, fieldValue));
>>>>>>> origin/feature/student
    }
}
=======
    public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue){
        super(String.format("Resource %s not found for %s with value %s",resourceName, fieldName,fieldValue));
    }
}
>>>>>>> origin/feature/UserPlansImpl
