package com.sb.NotePad.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDTO {
    private Integer id;
    private String name;
    private String email;
    private String password;
    private List<NoteDTO> notes;
}
