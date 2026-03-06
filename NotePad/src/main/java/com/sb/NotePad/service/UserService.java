package com.sb.NotePad.service;

import com.sb.NotePad.dto.UserDTO;

public interface UserService {
    UserDTO getUserById(int userId);
    UserDTO updateUserById(UserDTO userDTO,int userId);
    boolean deleteUserById(int userId);
}
