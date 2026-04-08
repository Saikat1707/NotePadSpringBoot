package com.sb.NotePad.serviceImpl;

import com.sb.NotePad.dto.UserDTO;
import com.sb.NotePad.entities.User;
import com.sb.NotePad.mapper.UserMapper;
import com.sb.NotePad.repository.UserRepo;
import com.sb.NotePad.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

        if(userDTO.getName() != null)userToBeUpdate.setName(userDTO.getName());
        if(userDTO.getEmail() != null)userToBeUpdate.setEmail(userDTO.getEmail());
        if(userDTO.getPassword() != null)userToBeUpdate.setPassword(userDTO.getPassword());

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

    public List<UserDTO> getAllUsers(){
        List<User> userList = userRepo.findAll();
        List<UserDTO> userDTOList =
                userList.stream()
                        .map((user)->UserMapper.toUserDto(user))
                        .toList();
        return userDTOList;
    }

    @Override
    public UserDTO createUser(UserDTO dto) {
        if(userRepo.existsByEmail(dto.getEmail())){
            throw new RuntimeException("Email already exists");
        }
        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setNotes(List.of());
        User savedUser = userRepo.save(user);
        return UserMapper.toUserDto(savedUser);
    }


}
