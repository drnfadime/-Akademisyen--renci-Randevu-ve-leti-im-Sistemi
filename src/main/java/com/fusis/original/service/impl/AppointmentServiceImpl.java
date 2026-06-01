package com.fusis.original.service.impl;

import com.fusis.original.dto.request.AppointmentRequestDTO;
import com.fusis.original.dto.response.AppointmentResponseDTO;
import com.fusis.original.entity.Appointment;
import com.fusis.original.entity.Teacher;
import com.fusis.original.entity.User;
import com.fusis.original.repository.AppointmentRepository;
import com.fusis.original.repository.TeacherRepository;
import com.fusis.original.repository.UserRepository;
import com.fusis.original.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final UserRepository userRepository;
    private final TeacherRepository teacherRepository;

    @Override
    public AppointmentResponseDTO createAppointment(AppointmentRequestDTO request) {
        User student = userRepository.findById(request.getStudentId())
                .orElseThrow(() -> new RuntimeException("Öğrenci bulunamadı"));

        Teacher teacher = teacherRepository.findById(request.getTeacherId())
                .orElseThrow(() -> new RuntimeException("Öğretmen bulunamadı"));

        Appointment appointment = new Appointment();
        appointment.setDate(request.getDate());
        appointment.setIsonline(request.getIsonline());
        appointment.setOld(false);
        appointment.setSubject(request.getSubject());
        appointment.setStudent(student);
        appointment.setTeacher(teacher);

        Appointment saved = appointmentRepository.save(appointment);
        return toResponseDTO(saved);
    }

    @Override
    public List<AppointmentResponseDTO> getAppointmentsByStudent(Integer studentId) {
        return appointmentRepository.findByStudent_Id(studentId)
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<AppointmentResponseDTO> getAppointmentsByTeacher(Integer teacherId) {
        return appointmentRepository.findByTeacher_Teacherid(teacherId)
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    private AppointmentResponseDTO toResponseDTO(Appointment appointment) {
        AppointmentResponseDTO dto = new AppointmentResponseDTO();
        dto.setId(appointment.getAppointmentid());
        dto.setDate(appointment.getDate());
        dto.setIsonline(appointment.getIsonline());
        dto.setOld(appointment.getOld());
        dto.setSubject(appointment.getSubject());
        dto.setStudentName(appointment.getStudent().getName() + " " + appointment.getStudent().getSurname());
        dto.setTeacherName(appointment.getTeacher().getName() + " " + appointment.getTeacher().getSurname());
        return dto;
    }


//hocanın randevu listelemesini görme kodları



    @Override
public List<AppointmentResponseDTO> getPendingByTeacher(Integer teacherId) {
    return appointmentRepository.findByTeacher_TeacheridAndOldFalse(teacherId)
            .stream()
            .map(this::toResponseDTO)
            .collect(Collectors.toList());
}

@Override
public AppointmentResponseDTO approveAppointment(Integer appointmentId) {
    Appointment appointment = appointmentRepository.findById(appointmentId)
            .orElseThrow(() -> new RuntimeException("Randevu bulunamadı"));
    appointment.setOld(false);
    return toResponseDTO(appointmentRepository.save(appointment));
}

@Override
public AppointmentResponseDTO rejectAppointment(Integer appointmentId) {
    Appointment appointment = appointmentRepository.findById(appointmentId)
            .orElseThrow(() -> new RuntimeException("Randevu bulunamadı"));
    appointment.setOld(true);
    return toResponseDTO(appointmentRepository.save(appointment));
}
}