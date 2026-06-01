package com.fusis.original.dto.response;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class AppointmentResponseDTO {
    private Integer id;
    private LocalDateTime date;
    private Boolean isonline;
    private Boolean old;
    private String subject;
    private String studentName;
    private String teacherName;
}