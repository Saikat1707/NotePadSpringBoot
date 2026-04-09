package com.sb.NotePad.controller;

import com.sb.NotePad.dto.ApiResponseDTO;
import com.sb.NotePad.dto.UserDTO;
import com.sb.NotePad.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponseDTO<UserDTO>> register(
            @RequestBody UserDTO dto
    ){
        ApiResponseDTO<UserDTO> api = new ApiResponseDTO<>(
                true,
                "User created Successfully",
                authService.registerUser(dto)

        );
        return new ResponseEntity<>(
                api,
                HttpStatus.CREATED
        );
    }
}
