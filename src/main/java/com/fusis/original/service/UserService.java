package com.fusis.original.service;

import com.fusis.original.entity.User;
import com.fusis.original.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    // Kullanıcı kaydet
    public User save(User user) {
        return userRepository.save(user);
    }

    // Email ile kullanıcı bul
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // ID ile kullanıcı bul
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    // Role göre kullanıcıları getir
    public List<User> findByRole(String role) {
        return userRepository.findByRole(role);
    }

    // Email var mı kontrol et
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    // Kullanıcı sil
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}