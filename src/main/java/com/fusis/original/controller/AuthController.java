package com.fusis.original.controller;

import com.fusis.original.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    // Geçici login — FUS-7 (UI) sonrası veritabanına bağlanacak
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(
            @RequestBody Map<String, String> request) {

        String email = request.get("email");
        String password = request.get("password");
        String role = request.get("role"); // "STUDENT" veya "TEACHER"

        // Şimdilik sabit kontrol, sonra DB'den gelecek
        if (email == null || password == null) {
            return ResponseEntity.badRequest()
                .body(Map.of("error", "Email ve şifre zorunlu"));
        }

        String token = jwtUtil.generateToken(email, role != null ? role : "STUDENT");
        return ResponseEntity.ok(Map.of("token", token));
    }
}