package br.com.fiap.apiRest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ValidationExceptionHandler
{
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException exception)
    {
        Map<String, String> erros = new HashMap<>();
        for(FieldError fe : exception.getBindingResult().getFieldErrors())
        {
            erros.put(fe.getField(), fe.getDefaultMessage());
        }
        return new ResponseEntity<>(erros, HttpStatus.BAD_REQUEST);
    }

}
