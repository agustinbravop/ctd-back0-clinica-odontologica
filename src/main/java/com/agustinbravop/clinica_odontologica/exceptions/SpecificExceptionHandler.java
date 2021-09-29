package com.agustinbravop.clinica_odontologica.exceptions;

import org.apache.log4j.Logger;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class SpecificExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger logger = Logger.getLogger(SpecificExceptionHandler.class);

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<?> resourceNotFoundExceptionHandler(ResourceNotFoundException ex) {
        logger.error(ex.getMessage(), ex);
        return new ResponseEntity<>(
                "El recurso solicitado no fue encontrado. Mensaje: " + ex.getMessage(),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<?> entityNotFoundExceptionHandler(EntityNotFoundException ex) {
        logger.error(ex.getMessage(), ex);
        return new ResponseEntity<>(
                "El recurso solicitado no fue encontrado. Mensaje: " + ex.getMessage(),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> badRequestExceptionHandler(BadRequestException ex) {
        logger.error(ex.getMessage(), ex);
        return new ResponseEntity<>(
                "No se pudo interpretar correctamente la petición. Mensaje: " + ex.getMessage(),
                HttpStatus.BAD_REQUEST
        );
    }
}
