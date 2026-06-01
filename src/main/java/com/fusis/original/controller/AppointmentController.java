package com.fusis.original.controller;

import com.fusis.original.dto.request.AppointmentRequestDTO;
import com.fusis.original.dto.response.AppointmentResponseDTO;
import com.fusis.original.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;

    @PostMapping
    public ResponseEntity<AppointmentResponseDTO> createAppointment(
            @RequestBody AppointmentRequestDTO request) {
        return ResponseEntity.ok(appointmentService.createAppointment(request));
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<AppointmentResponseDTO>> getByStudent(
            @PathVariable Integer studentId) {
        return ResponseEntity.ok(appointmentService.getAppointmentsByStudent(studentId));
    }

    @GetMapping("/teacher/{teacherId}")
    public ResponseEntity<List<AppointmentResponseDTO>> getByTeacher(
            @PathVariable Integer teacherId) {
        return ResponseEntity.ok(appointmentService.getAppointmentsByTeacher(teacherId));
    }
}