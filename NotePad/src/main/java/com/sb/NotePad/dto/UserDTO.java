package com.sb.NotePad.dto;

import com.sb.NotePad.entities.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDTO {
    private String name;
    private String email;
    private String password;
    private List<NoteDTO> notes;
    private String role;
    private Boolean isAccountEnabled;
}
