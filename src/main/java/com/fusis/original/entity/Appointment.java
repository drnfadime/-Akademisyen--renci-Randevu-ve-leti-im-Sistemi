package com.fusis.original.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "\"Appointment\"")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Appointment {

    @Id
    @Column(name = "\"Appointmentid\"")
    private Integer Appointmentid;
   

    private LocalDateTime date;
    private Boolean isonline;
    private Boolean old;
    private String subject;

    @ManyToOne
    @JoinColumn(name = "\"studentId\"")
    private User student;

    @ManyToOne
    @JoinColumn(name = "\"teacherId\"")
    private Teacher teacher;
}