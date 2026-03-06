package com.sb.NotePad.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String desc;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Note(String title, String desc, User user) {
        this.title = title;
        this.desc = desc;
        this.user = user;
    }
}
