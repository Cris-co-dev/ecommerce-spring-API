package com.crisdev.api.storeapi.config.security.handler;

import com.crisdev.api.storeapi.dto.response.exception.GenericApiError;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {

        GenericApiError errorResponse = new GenericApiError();
        errorResponse.setBackendMessage(accessDeniedException.getLocalizedMessage());
        errorResponse.setUrl(request.getRequestURL().toString());
        errorResponse.setMessage("Lo sentimos, no tienes permisos suficientes para acceder a este recurso. " +
                "Por favor, ponte en contacto con el administrador del sistema para obtener ayuda.");
        errorResponse.setTimestamp(LocalDateTime.now());
        errorResponse.setMethod(request.getMethod());

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpStatus.FORBIDDEN.value());

        ObjectMapper objectMapper = new ObjectMapper();
       objectMapper.registerModule(new com.fasterxml.jackson.datatype.jsr310.JavaTimeModule());

        String errorResponseAsJson = objectMapper.writeValueAsString(errorResponse);

        response.getWriter().write(errorResponseAsJson);

    }
}
