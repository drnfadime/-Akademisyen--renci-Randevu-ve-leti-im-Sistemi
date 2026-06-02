package com.fusis.original.controller;

import com.fusis.original.dto.request.TimeTableRequestDTO;
import com.fusis.original.dto.response.TimeTableResponseDTO;
import com.fusis.original.service.TimeTableService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/timetable")
@RequiredArgsConstructor
public class TimeTableController {

    private final TimeTableService timeTableService;

    // Öğretmene çalışma saati ekle
    @PostMapping
    public ResponseEntity<TimeTableResponseDTO> addTimeTable(
            @RequestBody TimeTableRequestDTO request) {
        return ResponseEntity.ok(timeTableService.addTimeTable(request));
    }

    // Öğretmenin çalışma saatlerini getir
    @GetMapping("/teacher/{teacherId}")
    public ResponseEntity<List<TimeTableResponseDTO>> getByTeacher(
            @PathVariable Integer teacherId) {
        return ResponseEntity.ok(timeTableService.getTimeTableByTeacher(teacherId));
    }

    // Çalışma saatini sil
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTimeTable(
            @PathVariable Integer id) {
        timeTableService.deleteTimeTable(id);
        return ResponseEntity.noContent().build();
    }
}