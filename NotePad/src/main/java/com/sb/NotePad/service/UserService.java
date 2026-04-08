package com.sb.NotePad.service;

import com.sb.NotePad.dto.UserDTO;

import java.util.List;

public interface UserService {
    UserDTO getUserById(int userId);
    UserDTO updateUserById(UserDTO userDTO,int userId);
    boolean deleteUserById(int userId);
    List<UserDTO> getAllUsers();
    UserDTO createUser(UserDTO dto);
}
