package com.fusis.original.repository;

import com.fusis.original.entity.Appointment;
import com.fusis.original.entity.AppointmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

    // Öğrencinin tüm randevuları
    List<Appointment> findByStudent_Id(Integer studentId);

    // Öğretmenin tüm randevuları
    List<Appointment> findByTeacher_Teacherid(Integer teacherId);

    // Öğretmenin bekleyen randevuları (PENDING)
    List<Appointment> findByTeacher_TeacheridAndStatus(Integer teacherId, AppointmentStatus status);

    // Öğrencinin belirli statüdeki randevuları
    List<Appointment> findByStudent_IdAndStatus(Integer studentId, AppointmentStatus status);

    // Öğretmenin belirli statüdeki randevuları
    List<Appointment> findByTeacher_TeacheridAndStatus(Integer teacherId, AppointmentStatus status);
}