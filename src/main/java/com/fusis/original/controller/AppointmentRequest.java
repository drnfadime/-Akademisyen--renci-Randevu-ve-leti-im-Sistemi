package com.fusis.original.controller;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class AppointmentRequest {

    @NotBlank(message = "Konu boş bırakılamaz")
    private String topic;

    @NotBlank(message = "Açıklama boş bırakılamaz")
    private String description;

    @NotNull(message = "Randevu tarihi zorunludur")
    @Future(message = "Randevu tarihi gelecekte olmalıdır")
    private LocalDateTime appointmentDate;

    @NotNull(message = "Öğrenci ID zorunludur")
    private Long studentId;

    @NotNull(message = "Akademisyen ID zorunludur")
    private Long academicianId;
}