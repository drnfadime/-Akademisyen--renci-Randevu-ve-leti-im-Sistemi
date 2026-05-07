package com.fusis.original.security;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PasswordHasherServiceTest {

    // Test edeceğimiz servisi çağırıyoruz
    PasswordHasherService passwordHasherService = new PasswordHasherService();

    @Test
    public void testHashPassword() {

        String rawPassword = "benimGizliSifrem123";

        // Şifreleme metodumuzu çalıştırıyorum
        String hashedPassword = passwordHasherService.hashPassword(rawPassword);


        // Şifrelenmiş metin boş dönmemeli
        Assertions.assertNotNull(hashedPassword);

        // Şifrelenmiş metin, orijinal şifreden farklı bir şey olmalı
        Assertions.assertNotEquals(rawPassword, hashedPassword);

        System.out.println("Orijinal Şifre: " + rawPassword);
        System.out.println("Hashlenmiş Şifre: " + hashedPassword);
    }

    @Test
    public void testVerifyPassword() {

        String rawPassword = "benimGizliSifrem123";
        String hashedPassword = passwordHasherService.hashPassword(rawPassword);

        // Doğru şifre ile doğrulama yapıldığında TRUE (başarılı) dönmeli
        boolean isMatch = passwordHasherService.verifyPassword(rawPassword, hashedPassword);
        Assertions.assertTrue(isMatch);

        // Yanlış şifre girildiğinde doğrulama FALSE (başarısız) dönmeli
        boolean isNotMatch = passwordHasherService.verifyPassword("yanlisSifre", hashedPassword);
        Assertions.assertFalse(isNotMatch);
    }
}