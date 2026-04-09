package com.sb.NotePad.mapper;

import com.sb.NotePad.dto.NoteDTO;
import com.sb.NotePad.dto.UserDTO;
import com.sb.NotePad.entities.Note;
import com.sb.NotePad.entities.Role;
import com.sb.NotePad.entities.User;

import java.util.List;

public class UserMapper {
    public static User toUser(UserDTO userDTO){
        User user = new User();

        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());

        List<NoteDTO> noteDTOList = userDTO.getNotes();
        List<Note> noteList = noteDTOList
                .stream()
                .map(noteDTO -> NoteMapper.toNote(noteDTO))
                .toList();

        noteList.forEach(note -> note.setUser(user));
        user.setNotes(noteList);
        user.setRole(Role.valueOf(userDTO.getRole()));
        user.setAccountEnabled(userDTO.getIsAccountEnabled());

        return user;
    }
    public static UserDTO toUserDto(User user){
        UserDTO userDTO = new UserDTO();

        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());

        List<Note> noteList = user.getNotes();

        List<NoteDTO> noteDTOList = noteList
                                    .stream()
                                    .map(note -> NoteMapper.toNoteDto(note))
                                    .toList();
        userDTO.setNotes(noteDTOList);
        userDTO.setRole(user.getRole().toString());
        userDTO.setIsAccountEnabled(user.isAccountEnabled());

        return userDTO;
    }
}
