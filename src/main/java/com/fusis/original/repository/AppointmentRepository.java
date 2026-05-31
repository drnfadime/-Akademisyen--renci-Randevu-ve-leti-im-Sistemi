package com.fusis.original.repository;

import com.fusis.original.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
    List<Appointment> findByTeacher_Teacherid(Integer teacherId);
    List<Appointment> findByStudent_Id(Integer studentId);
    List<Appointment> findByTeacher_TeacheridAndOldFalse(Integer teacherId);
    List<Appointment> findByStudent_IdAndOldTrue(Integer studentId);
    List<Appointment> findByTeacher_TeacheridAndOldTrue(Integer teacherId);
}