package com.fusis.original.controller;

import com.fusis.original.entity.Appointment;
import com.fusis.original.entity.User;
import com.fusis.original.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/appointments")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;

    // Randevu talebi oluştur
    @PostMapping
    public ResponseEntity<Appointment> create(@RequestBody AppointmentRequest request) {
        User student = new User();
        student.setId(request.getStudentId());

        User academician = new User();
        academician.setId(request.getAcademicianId());

        Appointment appointment = appointmentService.createAppointment(
                request.getTopic(),
                request.getDescription(),
                request.getAppointmentDate(),
                student,
                academician
        );
        return ResponseEntity.ok(appointment);
    }

    // Akademisyenin taleplerini getir
    @GetMapping("/academician/{id}")
    public ResponseEntity<List<Appointment>> getByAcademician(@PathVariable Long id) {
        return ResponseEntity.ok(appointmentService.getPendingAppointments(id));
    }

    // Randevu durumunu güncelle (onayla/reddet)
    @PatchMapping("/{id}/status")
    public ResponseEntity<Appointment> updateStatus(@PathVariable Long id,
                                                    @RequestParam String status) {
        return ResponseEntity.ok(appointmentService.updateStatus(id, status));
    }
}