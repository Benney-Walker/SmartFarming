package com.bbquantum.smartfarming.Config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        System.out.println(">>> 403 DENIED on: " + request.getRequestURI());
        System.out.println(">>> Method: " + request.getMethod());

        if (auth == null) {
            System.out.println(">>> Auth is NULL - filter never set authentication");
        } else {
            System.out.println(">>> Authenticated as: " + auth.getName());
            System.out.println(">>> Authorities: " + auth.getAuthorities());
            System.out.println(">>> Is Authenticated: " + auth.isAuthenticated());
        }

        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.getWriter().write(
                "{\"error\": \"Access Denied\", " +
                        "\"user\": \"" + (auth != null ? auth.getName() : "null") + "\", " +
                        "\"authorities\": \"" + (auth != null ? auth.getAuthorities() : "null") + "\", " +
                        "\"uri\": \"" + request.getRequestURI() + "\"}"
        );
    }
}
