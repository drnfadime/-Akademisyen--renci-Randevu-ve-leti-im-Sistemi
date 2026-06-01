package com.fusis.original.service;

import com.fusis.original.dto.request.AppointmentRequestDTO;
import com.fusis.original.dto.response.AppointmentResponseDTO;
import java.util.List;



public interface AppointmentService {

    AppointmentResponseDTO createAppointment(AppointmentRequestDTO request);




    List<AppointmentResponseDTO> getAppointmentsByStudent(Integer studentId);
    List<AppointmentResponseDTO> getAppointmentsByTeacher(Integer teacherId);



    //bekleyenler için randevu listeleme

    List<AppointmentResponseDTO> getPendingByTeacher(Integer teacherId);
    AppointmentResponseDTO approveAppointment(Integer appointmentId);
    AppointmentResponseDTO rejectAppointment(Integer appointmentId);

    //geçmiş randevular için listeleme

    List<AppointmentResponseDTO> getOldAppointmentsByStudent(Integer studentId);
    List<AppointmentResponseDTO> getOldAppointmentsByTeacher(Integer teacherId);



}