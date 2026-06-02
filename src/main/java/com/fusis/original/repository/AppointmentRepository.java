package com.fusis.original.repository;

import com.fusis.original.entity.Appointment;
import com.fusis.original.entity.AppointmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

    List<Appointment> findByStudent_Id(Integer studentId);

    List<Appointment> findByTeacher_Teacherid(Integer teacherId);

    List<Appointment> findByTeacher_TeacheridAndStatus(Integer teacherId, AppointmentStatus status);

    List<Appointment> findByStudent_IdAndStatus(Integer studentId, AppointmentStatus status);
}