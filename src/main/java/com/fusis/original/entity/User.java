package com.fusis.original.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
@NoArgsConstructor // jpa denemek için lazım olabilir
@AllArgsConstructor

@Entity
@Table(name = "\"User\"")
@Getter 
@Setter
public class User {
    @Id
    private Integer id;
    private String name;
    private String email;   
    private String password;
    private String surname;

    @OneToMany(mappedBy = "student")
    private List<Appointment> appointments;

}
