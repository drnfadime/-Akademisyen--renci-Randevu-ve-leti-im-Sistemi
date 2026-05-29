package com.fusis.original.service;

import com.fusis.original.entity.Appointment;
import com.fusis.original.entity.User;
import com.fusis.original.repository.AppointmentRepository;
import com.fusis.original.dto.AppointmentDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final SummarizationService summarizationService;

    // Öğrenci randevu talebi oluşturur
    public Appointment createAppointment(String topic, String description,
                                         LocalDateTime appointmentDate,
                                         User student, User academician) {
        Appointment appointment = new Appointment();
        appointment.setTopic(topic);
        String summary = summarizationService.summarize(description);
        appointment.setDescription(summary);
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

    // Akademisyenin randevularını duruma göre filtrele
    public List<Appointment> getAppointmentsByStatus(Long academicianId, String status) {
        if (status == null || status.isEmpty()) {
            return appointmentRepository.findByAcademicianId(academicianId);
        }
        return appointmentRepository.findByAcademicianIdAndStatus(academicianId, status);
    }

    // Geçmiş randevuları getirir
    public List<Appointment> getPastAppointments(Long userId, String role) {
        LocalDateTime now = LocalDateTime.now();
        if ("ACADEMICIAN".equals(role)) {
            return appointmentRepository.findByAcademicianIdAndAppointmentDateBefore(userId, now);
        }
        return appointmentRepository.findByStudentIdAndAppointmentDateBefore(userId, now);
    }

    // Appointment entity'sini DTO'ya dönüştür
    public AppointmentDTO toDTO(Appointment appointment) {
        AppointmentDTO dto = new AppointmentDTO();
        dto.setId(appointment.getId());
        dto.setTopic(appointment.getTopic());
        dto.setDescription(appointment.getDescription());
        dto.setStatus(appointment.getStatus());
        dto.setRequestedAt(appointment.getRequestedAt());
        dto.setAppointmentDate(appointment.getAppointmentDate());
        dto.setStudentId(appointment.getStudent().getId());
        dto.setStudentName(appointment.getStudent().getName());
        dto.setAcademicianId(appointment.getAcademician().getId());
        dto.setAcademicianName(appointment.getAcademician().getName());
        return dto;
    }
}