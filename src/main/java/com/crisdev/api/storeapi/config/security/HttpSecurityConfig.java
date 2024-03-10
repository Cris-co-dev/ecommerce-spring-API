package com.crisdev.api.storeapi.config.security;

import com.crisdev.api.storeapi.config.security.filter.JwtAuthenticationFilter;
import com.crisdev.api.storeapi.config.security.handler.CustomAccessDeniedHandler;
import com.crisdev.api.storeapi.config.security.handler.CustomAuthenticationEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class HttpSecurityConfig {

    private final AuthenticationProvider authenticationProvider;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthorizationManager<RequestAuthorizationContext> authorizationManager;

    public HttpSecurityConfig(AuthenticationProvider authenticationProvider,
                              CustomAccessDeniedHandler customAccessDeniedHandler,
                              CustomAuthenticationEntryPoint customAuthenticationEntryPoint,
                              JwtAuthenticationFilter jwtAuthenticationFilter,
                              AuthorizationManager<RequestAuthorizationContext> authorizationManager) {

        this.authenticationProvider = authenticationProvider;
        this.customAccessDeniedHandler = customAccessDeniedHandler;
        this.customAuthenticationEntryPoint = customAuthenticationEntryPoint;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.authorizationManager = authorizationManager;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity config) throws Exception {

        return config
                .cors(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(sessionManagmentConfig ->
                        sessionManagmentConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(authorize -> authorize.anyRequest().access(authorizationManager))
                .exceptionHandling(httpSecurityExceptionHandlingConfigurer -> {
                    httpSecurityExceptionHandlingConfigurer.authenticationEntryPoint(customAuthenticationEntryPoint);
                    httpSecurityExceptionHandlingConfigurer.accessDeniedHandler(this.customAccessDeniedHandler);
                }).build();
    }

}
