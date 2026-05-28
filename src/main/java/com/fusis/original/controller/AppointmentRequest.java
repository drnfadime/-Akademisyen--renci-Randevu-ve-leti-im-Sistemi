package com.fusis.original.controller;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class AppointmentRequest {
    private String topic;
    private String description;
    private LocalDateTime appointmentDate;
    private Long studentId;
    private Long academicianId;
}