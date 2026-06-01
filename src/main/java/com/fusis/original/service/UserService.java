package com.fusis.original.service;

import java.util.List;

import com.fusis.original.dto.request.UserRequestDTO;
import com.fusis.original.dto.response.UserResponseDTO;

public interface UserService {

    UserResponseDTO createUser(UserRequestDTO request);

    UserResponseDTO getUserById(Integer id);

    List<UserResponseDTO> getAllUsers();

    UserResponseDTO updateUser(Integer id, UserRequestDTO request);

    void deleteUser(Integer id);
}
