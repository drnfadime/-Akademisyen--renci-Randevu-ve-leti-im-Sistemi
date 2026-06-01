package com.fusis.original.controller;

import com.fusis.original.dto.request.TeacherRequestDTO;
import com.fusis.original.dto.response.TeacherResponseDTO;
import com.fusis.original.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teachers")
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;

    // Yeni akademisyen kaydı
    @PostMapping
    public ResponseEntity<TeacherResponseDTO> createTeacher(
            @RequestBody TeacherRequestDTO request) {
        return ResponseEntity.ok(teacherService.createTeacher(request));
    }

    // ID ile akademisyen getir
    @GetMapping("/{id}")
    public ResponseEntity<TeacherResponseDTO> getTeacherById(
            @PathVariable Integer id) {
        return ResponseEntity.ok(teacherService.getTeacherById(id));
    }

    // Tüm akademisyenleri listele
    @GetMapping
    public ResponseEntity<List<TeacherResponseDTO>> getAllTeachers() {
        return ResponseEntity.ok(teacherService.getAllTeachers());
    }

    // Akademisyen bilgilerini güncelle
    @PutMapping("/{id}")
    public ResponseEntity<TeacherResponseDTO> updateTeacher(
            @PathVariable Integer id,
            @RequestBody TeacherRequestDTO request) {
        return ResponseEntity.ok(teacherService.updateTeacher(id, request));
    }

    // Akademisyeni sil
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeacher(
            @PathVariable Integer id) {
        teacherService.deleteTeacher(id);
        return ResponseEntity.noContent().build();
    }
}