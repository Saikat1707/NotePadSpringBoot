package com.sb.NotePad.serviceImpl;

import com.sb.NotePad.dto.UserDTO;
import com.sb.NotePad.service.AuthService;
import com.sb.NotePad.service.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDTO registerUser(UserDTO dto) {

        //encode password
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        return userService.createUser(dto);
    }

    @Override
    public UserDTO loginUser(UserDTO dto) {
        return null;
    }
}
