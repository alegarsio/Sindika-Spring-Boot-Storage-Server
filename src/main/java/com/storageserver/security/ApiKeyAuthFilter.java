package com.storageserver.security;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;


/**
 * Spring Security dependencies
 */

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;


import java.io.IOException;


public class ApiKeyAuthFilter extends OncePerRequestFilter{
    
    private String headerName;
    private String apiKeyValue;


    public ApiKeyAuthFilter(String headerName, String apiKeyValue) {
        this.headerName = headerName;
        this.apiKeyValue = apiKeyValue;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String authHeader = request.getHeader(headerName);

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String submittedKey = authHeader.substring(7);
            if (submittedKey.equals(apiKeyValue)) {

                Authentication authentication = new ApiKeyAuthentication(submittedKey, AuthorityUtils.NO_AUTHORITIES);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        

        filterChain.doFilter(request, response);
    }


    


}
