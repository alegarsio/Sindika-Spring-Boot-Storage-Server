package com.storageserver.security;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
/**
 * This Security config not allowed for production use
 * This is only for testing purpose
 */

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Value("${api.auth.key-name}")
    private String apiKeyHeaderName;

    @Value("${api.auth.key-value}")
    private String apiKeyValue;

    /**
     * Security Filter Chain
     * @param http
     * @return
     * @throws Exception
     * Disables CSRF protection, sets the session management policy to stateless, adds a custom API key authentication filter, and permits all requests without authentication.
     */

     @Bean
     public PasswordEncoder passwordEncoder() {
         return new BCryptPasswordEncoder();
     }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) 
            
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Konfigurasi lama Anda
            
            .addFilterBefore(new ApiKeyAuthFilter(apiKeyHeaderName, apiKeyValue), UsernamePasswordAuthenticationFilter.class)
            
            .authorizeHttpRequests(auth -> auth
             
                .requestMatchers("/api/auth/login").permitAll() 
                
                .requestMatchers("/api/**").authenticated() 
                
                .anyRequest().permitAll()
            );
            
        return http.build();
    }
}
