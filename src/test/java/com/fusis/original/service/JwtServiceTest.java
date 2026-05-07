package com.fusis.original.service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class JwtServiceTest {

    private final JwtService jwtService = new JwtService();

    @Test
    public void testGenerateToken() {
        String token = jwtService.generateToken("melih");
        assertNotNull(token);
        assertTrue(token.contains("melih"));
    }

    @Test
    public void testIsTokenValid() {
        String token = jwtService.generateToken("melih");
        boolean isValid = jwtService.isTokenValid(token);
        assertTrue(isValid);
    }
}