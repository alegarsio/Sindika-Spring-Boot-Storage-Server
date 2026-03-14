package com.storageserver.controller;

import com.storageserver.dto.LoginRequest;
import com.storageserver.model.Pengelola;
import com.storageserver.repository.PengelolaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {
    @Autowired 
    private PengelolaRepository pengelolaRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {

        Optional<Pengelola> userOptional = pengelolaRepository.findByEmail(loginRequest.getEmail());

        if (userOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Email atau password salah."));
        }

        Pengelola pengelola = userOptional.get();

        if (passwordEncoder.matches(loginRequest.getPassword(), pengelola.getPassword())) {
            
            
            Map<String, Object> response = Map.of(
                "message", "Login Berhasil",
                "id" , pengelola.getId(),
                "nama", pengelola.getNama(),
                "email", pengelola.getEmail()
            );
            return ResponseEntity.ok(response);
            
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Email atau password salah."));
        }
    }
}
