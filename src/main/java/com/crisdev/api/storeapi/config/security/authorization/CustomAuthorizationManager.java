package com.crisdev.api.storeapi.config.security.authorization;

import com.crisdev.api.storeapi.exception.ObjectNotFoundException;
import com.crisdev.api.storeapi.persistence.entity.security.GrantedPermission;
import com.crisdev.api.storeapi.persistence.entity.security.Operation;
import com.crisdev.api.storeapi.persistence.entity.security.User;
import com.crisdev.api.storeapi.persistence.repository.security.OperationRepository;
import com.crisdev.api.storeapi.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Component
public class CustomAuthorizationManager implements AuthorizationManager<RequestAuthorizationContext> {

    private final OperationRepository operationRepository;
    private final UserService userService;

    public CustomAuthorizationManager(OperationRepository operationRepository, UserService userService) {
        this.operationRepository = operationRepository;
        this.userService = userService;
    }

    @Override
    public AuthorizationDecision check(Supplier<Authentication> authentication, RequestAuthorizationContext object) {

        HttpServletRequest request = object.getRequest();

        String contextPath = request.getContextPath();
        String url = request.getRequestURI();

        url = url.replace(contextPath, "");
        String httpMethod = request.getMethod();

        boolean isPublic = isPublic(url, httpMethod);

        if (isPublic){
            return new AuthorizationDecision(true);
        }

        boolean isGranted = isGranted(url,httpMethod,authentication.get());

        return new AuthorizationDecision(isGranted);
    }

    private boolean isPublic(String url, String httpMethod) {
        List<Operation> publicAccessEndpoints = operationRepository.findByPublicAccess();

        return  publicAccessEndpoints.stream().anyMatch(getOperationPredicate(url, httpMethod));
    }

    private static Predicate<Operation> getOperationPredicate(String url, String httpMethod) {
        return operation -> {
            String basePath = operation.getModule().getBasePath();

            Pattern pattern = Pattern.compile(basePath.concat(operation.getPath()));
            Matcher matcher = pattern.matcher(url);

            return matcher.matches() && operation.getHttpMethod().equals(httpMethod);

        };
    }
    private boolean isGranted(String url, String httpMethod, Authentication authentication) {

        if (authentication == null || !(authentication instanceof UsernamePasswordAuthenticationToken)){
            throw new AuthenticationCredentialsNotFoundException("User not logged in");
        }

        List<Operation> operations = obtainOperations((UsernamePasswordAuthenticationToken) authentication);

        return operations.stream().anyMatch(getOperationPredicate(url, httpMethod));
    }

    private List<Operation> obtainOperations(UsernamePasswordAuthenticationToken authentication) {
        String userEmail = (String) authentication.getPrincipal();

        User user = userService.findByEmail(userEmail)
                .orElseThrow(() -> new ObjectNotFoundException("User not found."));

        return user.getRole().getPermissions().stream()
                .map(GrantedPermission::getOperation)
                .collect(Collectors.toList());
    }
}
