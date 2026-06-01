package com.fusis.original.dto.request;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class AppointmentRequestDTO {
    private LocalDateTime date;
    private Integer studentId;
    private Integer teacherId;
    private Boolean isonline;
    private String subject;
}