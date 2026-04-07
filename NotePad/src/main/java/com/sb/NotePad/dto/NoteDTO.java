package com.sb.NotePad.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class NoteDTO {
    private Integer id;
    private String title;
    private String desc;
    private Integer userId;
    private String createdByUserName;
    private String createdByUserEmail;

}
