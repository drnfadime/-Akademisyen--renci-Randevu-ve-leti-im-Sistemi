package com.fusis.original.service;

import com.fusis.original.entity.Appointment;
import com.fusis.original.entity.User;
import com.fusis.original.repository.AppointmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;

    // Öğrenci randevu talebi oluşturur
    public Appointment createAppointment(String topic, String description,
                                         LocalDateTime appointmentDate,
                                         User student, User academician) {
        Appointment appointment = new Appointment();
        appointment.setTopic(topic);
        appointment.setDescription(description);
        appointment.setStatus("PENDING");
        appointment.setRequestedAt(LocalDateTime.now());
        appointment.setAppointmentDate(appointmentDate);
        appointment.setStudent(student);
        appointment.setAcademician(academician);
        return appointmentRepository.save(appointment);
    }

    // Akademisyenin bekleyen taleplerini getirir
    public List<Appointment> getPendingAppointments(Long academicianId) {
        return appointmentRepository.findByAcademicianId(academicianId);
    }

    // Akademisyen onaylar veya reddeder
    public Appointment updateStatus(Long appointmentId, String status) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("Randevu bulunamadı"));
        appointment.setStatus(status);
        return appointmentRepository.save(appointment);
    }
}