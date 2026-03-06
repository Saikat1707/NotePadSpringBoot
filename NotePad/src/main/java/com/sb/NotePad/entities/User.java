package com.sb.NotePad.entities;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Note> notes;

    public User(String name, String email, List<Note> notes) {
        this.name = name;
        this.email = email;
        this.notes = notes;
    }

    public User(String name, String email) {
        this.email = email;
        this.name = name;
    }
}
