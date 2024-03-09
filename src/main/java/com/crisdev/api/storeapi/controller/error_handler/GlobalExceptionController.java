package com.crisdev.api.storeapi.controller.error_handler;

import com.crisdev.api.storeapi.dto.response.exception.GenericApiError;
import com.crisdev.api.storeapi.exception.ObjectNotFoundException;
import com.crisdev.api.storeapi.exception.OrderTotalException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionController {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handlerGenericException(Exception e, HttpServletRequest request) {

        GenericApiError response = new GenericApiError();
        response.setMessage("Error interno en el servidor, vuelva a intentarlo");
        response.setUrl(request.getRequestURL().toString());
        response.setMethod(request.getMethod());
        response.setBackendMessage(e.getMessage());
        response.setTimestamp(LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);

    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<?> handlerAccessDeniedException(AccessDeniedException e, HttpServletRequest request) {

        GenericApiError response = new GenericApiError();
        response.setMessage("Lo siento, no tienes permisos suficientes para acceder a este recurso. " +
                "Por favor, ponte en contacto con el administrador del sistema para obtener ayuda.");
        response.setUrl(request.getRequestURL().toString());
        response.setMethod(request.getMethod());
        response.setBackendMessage(e.getLocalizedMessage());
        response.setTimestamp(LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);

    }

    @ExceptionHandler(OrderTotalException.class)
    public ResponseEntity<?> handlerAccessDeniedException(OrderTotalException e, HttpServletRequest request) {

        GenericApiError response = new GenericApiError();
        response.setMessage("Lo siento, ah ocurrido un error con la orden intente nuevamente.");
        response.setUrl(request.getRequestURL().toString());
        response.setMethod(request.getMethod());
        response.setBackendMessage(e.getLocalizedMessage());
        response.setTimestamp(LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);

    }

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<?> handleIdNotFound(ObjectNotFoundException exception, HttpServletRequest request) {

        GenericApiError response = new GenericApiError();
        response.setMessage("");
        response.setUrl(request.getRequestURL().toString());
        response.setMethod(request.getMethod());
        response.setTimestamp(LocalDateTime.now());
        response.setBackendMessage(exception.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValid(MethodArgumentNotValidException exception) {

        List<String> exceptions = new ArrayList<>();

        exception.getAllErrors().forEach(error -> exceptions.add(error.getDefaultMessage()));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptions);
    }
}
