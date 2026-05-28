package com.fusis.original.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "appointments")
@Data
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String topic;
    private String description;
    private String status; // PENDING, APPROVED, REJECTED

    private LocalDateTime requestedAt;
    private LocalDateTime appointmentDate;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private User student;

    @ManyToOne
    @JoinColumn(name = "academician_id")
    private User academician;
}