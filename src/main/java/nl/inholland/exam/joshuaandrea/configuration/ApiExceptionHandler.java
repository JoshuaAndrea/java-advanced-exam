package nl.inholland.exam.joshuaandrea.configuration;

import nl.inholland.exam.joshuaandrea.models.dtos.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.naming.LimitExceededException;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = {HttpRequestMethodNotSupportedException.class})
    public ResponseEntity<Object> handleException(HttpRequestMethodNotSupportedException e) {
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(new ErrorDTO("Method Not Allowed"));
    }

    @ExceptionHandler(value = {LimitExceededException.class})
    public ResponseEntity<Object> handleException(LimitExceededException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorDTO(e.getMessage()));
    }


}
