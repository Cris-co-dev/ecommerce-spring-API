package com.crisdev.api.storeapi.config.security.handler;

import com.crisdev.api.storeapi.dto.response.exception.GenericApiError;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        GenericApiError error = new GenericApiError();
        error.setTimestamp(LocalDateTime.now());
        error.setMethod(request.getMethod());
        error.setUrl(request.getRequestURL().toString());
        error.setBackendMessage(authException.getLocalizedMessage());
        error.setMessage("Lo sentimos, acceso no autorizado. " +
                "Las credenciales proporcionadas son inválidas o no se han proporcionado. " +
                "Por favor, verifica tus credenciales e intenta nuevamente. " +
                "Si el problema persiste, contacta al administrador del sistema para obtener asistencia.");

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpStatus.UNAUTHORIZED.value());

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new com.fasterxml.jackson.datatype.jsr310.JavaTimeModule());

        String apiErrorAsJson = objectMapper.writeValueAsString(error);

        response.getWriter().write(apiErrorAsJson);
    }
}
