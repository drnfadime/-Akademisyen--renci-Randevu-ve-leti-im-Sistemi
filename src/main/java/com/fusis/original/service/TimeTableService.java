package com.fusis.original.service;

import com.fusis.original.dto.request.TimeTableRequestDTO;
import com.fusis.original.dto.response.TimeTableResponseDTO;

import java.util.List;

public interface TimeTableService {

    // Öğretmene çalışma saati ekle
    TimeTableResponseDTO addTimeTable(TimeTableRequestDTO request);

    // Öğretmenin tüm çalışma saatlerini getir
    List<TimeTableResponseDTO> getTimeTableByTeacher(Integer teacherId);

    // Çalışma saatini sil
    void deleteTimeTable(Integer timeTableId);
}