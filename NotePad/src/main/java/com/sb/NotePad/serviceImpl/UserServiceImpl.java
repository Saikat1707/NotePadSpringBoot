package com.sb.NotePad.serviceImpl;

import com.sb.NotePad.dto.UserDTO;
import com.sb.NotePad.entities.User;
import com.sb.NotePad.mapper.UserMapper;
import com.sb.NotePad.repository.UserRepo;
import com.sb.NotePad.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepo userRepo;

    @Override
    public UserDTO getUserById(int userId) {
        User user = userRepo.findById(userId).orElseThrow(()->new RuntimeException("Resource not found"));
        return UserMapper.toUserDto(user);
    }

    @Override
    public UserDTO updateUserById(UserDTO userDTO, int userId) {
        User userToBeUpdate = userRepo.findById(userId)
                .orElseThrow(()->new RuntimeException("User not found"));

        userToBeUpdate.setName(userDTO.getName());
        userToBeUpdate.setEmail(userDTO.getEmail());
        userToBeUpdate.setPassword(userDTO.getPassword());

        User user = userRepo.save(userToBeUpdate);

        return UserMapper.toUserDto(user);
    }

    @Override
    public boolean deleteUserById(int userId) {
        if(userRepo.existsById(userId)){
            userRepo.deleteById(userId);
            return true;
        }
        return false;
    }
}
