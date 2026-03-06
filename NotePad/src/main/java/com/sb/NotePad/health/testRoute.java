package com.sb.NotePad.health;

import com.sb.NotePad.dto.ApiResponseDTO;
import com.sb.NotePad.dto.NoteDTO;
import com.sb.NotePad.dto.UserDTO;
import com.sb.NotePad.entities.User;
import com.sb.NotePad.service.NoteService;
import com.sb.NotePad.service.UserService;
import com.sb.NotePad.serviceImpl.NoteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class testRoute {
    @Autowired
    NoteService noteService;
    @Autowired
    UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDTO<List<NoteDTO>>> test(@PathVariable int id){
        List<NoteDTO> noteDTOList = noteService.getAllNotesForUser(id);

        ApiResponseDTO<List<NoteDTO>> response =
                new ApiResponseDTO<>(true,"Successfully get all the notes for the user",noteDTOList);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/")
    public ResponseEntity<ApiResponseDTO<List<NoteDTO>>> test(){
        List<NoteDTO> noteDTOList = noteService.getAllNotes();

        ApiResponseDTO<List<NoteDTO>> response =
                new ApiResponseDTO<>(true,"Successfully get all the notes",noteDTOList);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<ApiResponseDTO<UserDTO>> test2(@PathVariable int id){
        UserDTO userDTO = userService.getUserById(id);

        ApiResponseDTO<UserDTO> response =
                new ApiResponseDTO<>(true,"Successfully get the user",userDTO);
        return ResponseEntity.ok(response);
    }


}
