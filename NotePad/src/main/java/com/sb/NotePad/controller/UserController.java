package com.sb.NotePad.controller;

import com.sb.NotePad.dto.ApiResponseDTO;
import com.sb.NotePad.dto.UserDTO;
import com.sb.NotePad.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/get/{id}")
    public ResponseEntity<ApiResponseDTO<UserDTO>> getUserById(@PathVariable int id){
        UserDTO userDTO = userService.getUserById(id);
        ApiResponseDTO<UserDTO> responseDTO =
                    new ApiResponseDTO<>(true,"Successfully get the user",userDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponseDTO<UserDTO>> updateUserById(
            @RequestBody UserDTO userDTO,
            @PathVariable int id
    ){
        System.out.println(userDTO);
        UserDTO updatedUser = userService.updateUserById(userDTO,id);
        ApiResponseDTO<UserDTO> responseDTO =
                new ApiResponseDTO<>(true,"Successfully update the user",updatedUser);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponseDTO<Boolean>> deleteUserById(
            @PathVariable int id
    ){
        boolean isDeleted = userService.deleteUserById(id);

        if(isDeleted) {
            ApiResponseDTO<Boolean> responseDTO =
                    new ApiResponseDTO<>(true, "User deleted successfully", true);
            return ResponseEntity.ok(responseDTO);
        } else {
            ApiResponseDTO<Boolean> responseDTO =
                    new ApiResponseDTO<>(false, "User not found", false);
            return ResponseEntity.status(404).body(responseDTO);
        }
    }

}
