package com.fusis.original.service;

import org.springframework.stereotype.Service;
import java.util.Date;

@Service
public class JwtService {

    private final String SECRET_KEY = "mysecretkey";

    public String generateToken(String username) {
        return "mock_token_for_" + username + "_" + new Date().getTime();
    }

    public boolean isTokenValid(String token) {
        return token != null && token.startsWith("mock_token");
    }
}