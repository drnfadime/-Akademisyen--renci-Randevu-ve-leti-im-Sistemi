package com.fusis.original.service.impl;

import com.fusis.original.dto.request.TeacherRequestDTO;
import com.fusis.original.dto.response.TeacherResponseDTO;
import com.fusis.original.entity.Teacher;
import com.fusis.original.repository.TeacherRepository;
import com.fusis.original.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;

    @Override
    public TeacherResponseDTO createTeacher(TeacherRequestDTO request) {
        if (teacherRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Bu e-posta adresi zaten kayıtlı");
        }

        Teacher teacher = new Teacher();
        teacher.setName(request.getName());
        teacher.setSurname(request.getSurname());
        teacher.setEmail(request.getEmail());
        teacher.setPassword(request.getPassword()); // ileride encode edilecek

        Teacher saved = teacherRepository.save(teacher);
        return toResponseDTO(saved);
    }

    @Override
    public TeacherResponseDTO getTeacherById(Integer id) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Öğretmen bulunamadı"));
        return toResponseDTO(teacher);
    }

    @Override
    public List<TeacherResponseDTO> getAllTeachers() {
        return teacherRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public TeacherResponseDTO updateTeacher(Integer id, TeacherRequestDTO request) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Öğretmen bulunamadı"));

        teacher.setName(request.getName());
        teacher.setSurname(request.getSurname());
        teacher.setEmail(request.getEmail());
        if (request.getPassword() != null && !request.getPassword().isBlank()) {
            teacher.setPassword(request.getPassword());
        }

        return toResponseDTO(teacherRepository.save(teacher));
    }

    @Override
    public void deleteTeacher(Integer id) {
        if (!teacherRepository.existsById(id)) {
            throw new RuntimeException("Öğretmen bulunamadı");
        }
        teacherRepository.deleteById(id);
    }

    private TeacherResponseDTO toResponseDTO(Teacher teacher) {
        TeacherResponseDTO dto = new TeacherResponseDTO();
        dto.setId(teacher.getTeacherid());
        dto.setName(teacher.getName());
        dto.setSurname(teacher.getSurname());
        dto.setEmail(teacher.getEmail());
        return dto;
    }
}