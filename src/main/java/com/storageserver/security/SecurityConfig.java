package com.storageserver.security;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Value("${api.auth.key-name}")
    private String apiKeyHeaderName;

    @Value("${api.auth.key-value}")
    private String apiKeyValue;


   @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            // Menonaktifkan CSRF karena ini adalah API stateless
            .csrf(csrf -> csrf.disable())
            
            // Mengatur agar tidak ada session yang dibuat
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            
            // Filter API Key tetap ditambahkan, tapi hasilnya akan diabaikan oleh permitAll
            .addFilterBefore(new ApiKeyAuthFilter(apiKeyHeaderName, apiKeyValue), UsernamePasswordAuthenticationFilter.class)
            
            // Bagian yang diubah untuk mengizinkan semua request
            .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll() 
            );
            
        return http.build();
    }
}
