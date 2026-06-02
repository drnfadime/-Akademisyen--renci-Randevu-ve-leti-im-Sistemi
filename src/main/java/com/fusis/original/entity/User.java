package com.fusis.original.entity;

import java.util.List;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "\"User\"")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    private String email;
    private String name;
    private String surname;
    private String password;

    @OneToMany(mappedBy = "student")
    private List<Appointment> appointments;
}