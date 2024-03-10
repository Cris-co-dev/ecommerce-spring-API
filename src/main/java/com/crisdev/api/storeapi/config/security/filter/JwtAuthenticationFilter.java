package com.crisdev.api.storeapi.config.security.filter;

import com.crisdev.api.storeapi.exception.ObjectNotFoundException;
import com.crisdev.api.storeapi.persistence.entity.security.JwtToken;
import com.crisdev.api.storeapi.persistence.entity.security.User;
import com.crisdev.api.storeapi.persistence.repository.security.JwtTokenRepository;
import com.crisdev.api.storeapi.service.UserService;
import com.crisdev.api.storeapi.service.auth.JwtTokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Date;
import java.util.Optional;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenService jwtTokenService;
    private final UserService userService;
    private final JwtTokenRepository jwtTokenRepository;

    public JwtAuthenticationFilter(JwtTokenService jwtTokenService,
                                   UserService userService,
                                   JwtTokenRepository jwtTokenRepository) {

        this.jwtTokenService = jwtTokenService;
        this.userService = userService;
        this.jwtTokenRepository = jwtTokenRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        // 1. Extract token from request
        String jwt = jwtTokenService.extractJwtFromRequest(request);

        if (!StringUtils.hasText(jwt)) {
            filterChain.doFilter(request, response);
            return;
        }

        // 2. If exists, get non-expired token from bd
        Optional<JwtToken> token = jwtTokenRepository.findByToken(jwt);
        boolean isTokenValid = validateToken(token);

        if (!isTokenValid) {
            filterChain.doFilter(request, response);
            return;
        }

        // 3. Get user by email from token and validates the format, signature and expiration date of the token.
        String userEmail = jwtTokenService.extractEmail(jwt);
        User userDetails = userService.findByEmail(userEmail)
                .orElseThrow(() -> new ObjectNotFoundException("User not found."));

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                userDetails.getEmail(), null, userDetails.getAuthorities()
        );

        authToken.setDetails(new WebAuthenticationDetails(request));

        // 4. Set auth object to SecurityContextHolder
        SecurityContextHolder.getContext().setAuthentication(authToken);

        // 5. The end
        filterChain.doFilter(request, response);
    }



    private boolean validateToken(Optional<JwtToken> optionalJwtToken) {

        if (!optionalJwtToken.isPresent()) {
            return false;
        }

        JwtToken jwtToken = optionalJwtToken.get();
        Date now = new Date(System.currentTimeMillis());

        //Validate token expiration
        boolean isValid = jwtToken.isValid() && jwtToken.getExpiration().after(now);

        if (!isValid) {
            jwtToken.setValid(false);
            jwtTokenRepository.save(jwtToken);
        }

        return isValid;
    }




}
