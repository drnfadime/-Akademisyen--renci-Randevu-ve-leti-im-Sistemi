package com.fusis.original.service;

import com.fusis.original.dto.request.TeacherRequestDTO;
import com.fusis.original.dto.response.TeacherResponseDTO;

import java.util.List;

public interface TeacherService {

    TeacherResponseDTO createTeacher(TeacherRequestDTO request);

    TeacherResponseDTO getTeacherById(Integer id);

    List<TeacherResponseDTO> getAllTeachers();

    TeacherResponseDTO updateTeacher(Integer id, TeacherRequestDTO request);

    void deleteTeacher(Integer id);
}