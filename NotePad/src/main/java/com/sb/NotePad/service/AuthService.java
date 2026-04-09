package com.sb.NotePad.service;

import com.sb.NotePad.dto.UserDTO;

public interface AuthService {
    UserDTO registerUser(UserDTO dto);
    UserDTO loginUser(UserDTO dto);
}
