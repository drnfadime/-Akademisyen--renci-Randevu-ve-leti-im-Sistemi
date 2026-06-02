package com.fusis.original.dto.response;

import com.fusis.original.entity.AppointmentStatus;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class AppointmentResponseDTO {
    private Integer id;
    private LocalDateTime date;
    private Boolean isonline;
    private AppointmentStatus status;
    private String subject;
    private String studentName;
    private String teacherName;
    private String meetLink;
}