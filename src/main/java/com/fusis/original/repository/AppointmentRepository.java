package com.fusis.original.repository;

import com.fusis.original.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    List<Appointment> findByAcademicianId(Long academicianId);
    List<Appointment> findByStudentId(Long studentId);
    List<Appointment> findByAcademicianIdAndStatus(Long academicianId, String status);
}