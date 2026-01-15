package lu.cnfpcfullstackdev.tfl_api.exception;
import lu.cnfpcfullstackdev.tfl_api.dto.error.ErrorResponseDTO;
import lu.cnfpcfullstackdev.tfl_api.exception.InvalidCredentialsException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

   



    // 1. Handle ResourceNotFoundException → 404 Not Found
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleResourceNotFound(ResourceNotFoundException ex) {
        ErrorResponseDTO error = new ErrorResponseDTO(
            HttpStatus.NOT_FOUND.value(), 
            ex.getMessage()
        );
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    // 2. NEW: Handle DuplicateResourceException → 409 Conflict
    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<ErrorResponseDTO> handleDuplicateResource(DuplicateResourceException ex) {
        ErrorResponseDTO error = new ErrorResponseDTO(
            HttpStatus.CONFLICT.value(), // 409
            ex.getMessage()
        );
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    // 3. Handle all other RuntimeExceptions → 500 Internal Server Error
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponseDTO> handleRuntimeException(RuntimeException ex) {
        ErrorResponseDTO error = new ErrorResponseDTO(
            HttpStatus.INTERNAL_SERVER_ERROR.value(), 
            ex.getMessage()
        );
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
     // Handle invalid credentials

@ExceptionHandler(InvalidCredentialsException.class)

public ResponseEntity<ErrorResponseDTO> handleInvalidCredentials(

        InvalidCredentialsException ex) {

    ErrorResponseDTO response = new ErrorResponseDTO(

        HttpStatus.UNAUTHORIZED.value(),   // 401

        ex.getMessage()

    );

    return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);

}

}