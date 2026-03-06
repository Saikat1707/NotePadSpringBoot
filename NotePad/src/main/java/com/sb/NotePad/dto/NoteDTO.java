package com.sb.NotePad.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class NoteDTO {
    private int id;
    private String title;
    private String desc;
    private int userId;
    private String createdByUserName;
    private String createdByUserEmail;

}
