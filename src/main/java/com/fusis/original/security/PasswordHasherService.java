package com.fusis.original.security;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class PasswordHasherService {

    // Kullanıcı kayıt olurken şifresini gizlemek (hashlemek) için
    public String hashPassword(String plainTextPassword) {
        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
    }

    // Kullanıcının girdiği şifre ile veritabanındaki şifre uyuşuyor mu kontrolü
    public boolean verifyPassword(String plainTextPassword, String hashedPassword) {
        return BCrypt.checkpw(plainTextPassword, hashedPassword);
    }
}