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
    private String subject;

    // old field'ı kaldırıldı, yerine status geldi
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private AppointmentStatus status;

    @ManyToOne
    @JoinColumn(name = "\"studentId\"")
    private User student;

    @ManyToOne
    @JoinColumn(name = "\"teacherId\"")
    private Teacher teacher;
}