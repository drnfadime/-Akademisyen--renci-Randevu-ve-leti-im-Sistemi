package com.fusis.original.entity;

import java.util.List;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "\"Teacher\"")
@Getter 
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Teacher {

    @Id
    // Prisma'da Teacherid dediğin için burada da ismini koruyoruz
    private Integer Teacherid; 
    
    private String name;
    private String surname;
    private String email;
    private String password;

    // Bir öğretmenin birden fazla randevusu olabilir
    @OneToMany(mappedBy = "teacher")
    private List<Appointment> appointments;

    // Bir öğretmenin çalışma saatleri (timetable) listesi
    @OneToMany(mappedBy = "teacher")
    private List<TimeTable> timeTable;
}