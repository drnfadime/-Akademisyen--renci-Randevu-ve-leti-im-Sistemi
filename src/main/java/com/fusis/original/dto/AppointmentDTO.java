package com.fusis.original.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class AppointmentDTO {

    private Long id;
    private String topic;
    private String description;
    private String status;
    private LocalDateTime requestedAt;
    private LocalDateTime appointmentDate;
    private Long studentId;
    private String studentName;
    private Long academicianId;
    private String academicianName;
}