package org.learn.AccountOpeningDemo.controllerAdvice;

import org.learn.AccountOpeningDemo.Exception.CustomerNotFoundException;
import org.learn.AccountOpeningDemo.entity.Customer;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * Class of controller advice to handle exceptions
 */
@RestControllerAdvice
public class ApplicationExceptionHandler {

    /**
     * Method to handle the custom exception CustomerNotFoundException
     * @param ex
     * @return errorObject i.e. map of error.
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(CustomerNotFoundException.class)
    public Map<String, String> handleBusinessException(CustomerNotFoundException ex){
        Map<String, String> errorObject = new HashMap<>();
        errorObject.put("errorMessage", ex.getMessage());
        return errorObject;
    }

    /**
     * Method to handle the exception MethodArgumentNotValidException
     * @param ex
     * @return errors i.e. Map of errors
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
