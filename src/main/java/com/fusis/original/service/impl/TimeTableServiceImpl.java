package com.fusis.original.service.impl;

import com.fusis.original.dto.request.TimeTableRequestDTO;
import com.fusis.original.dto.response.TimeTableResponseDTO;
import com.fusis.original.entity.Teacher;
import com.fusis.original.entity.TimeTable;
import com.fusis.original.repository.TeacherRepository;
import com.fusis.original.repository.TimeTableRepository;
import com.fusis.original.service.TimeTableService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TimeTableServiceImpl implements TimeTableService {

    private final TimeTableRepository timeTableRepository;
    private final TeacherRepository teacherRepository;

    @Override
    public TimeTableResponseDTO addTimeTable(TimeTableRequestDTO request) {
        Teacher teacher = teacherRepository.findById(request.getTeacherId())
                .orElseThrow(() -> new RuntimeException("Öğretmen bulunamadı"));

        TimeTable timeTable = new TimeTable();
        timeTable.setDay(request.getDay());
        timeTable.setStartTime(request.getStartTime());
        timeTable.setEndTime(request.getEndTime());
        timeTable.setTeacher(teacher);

        TimeTable saved = timeTableRepository.save(timeTable);
        return toResponseDTO(saved);
    }

    @Override
    public List<TimeTableResponseDTO> getTimeTableByTeacher(Integer teacherId) {
        return timeTableRepository.findByTeacher_Teacherid(teacherId)
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteTimeTable(Integer timeTableId) {
        if (!timeTableRepository.existsById(timeTableId)) {
            throw new RuntimeException("Çalışma saati bulunamadı");
        }
        timeTableRepository.deleteById(timeTableId);
    }

    private TimeTableResponseDTO toResponseDTO(TimeTable timeTable) {
        TimeTableResponseDTO dto = new TimeTableResponseDTO();
        dto.setId(timeTable.getTimeTableid());
        dto.setDay(timeTable.getDay());
        dto.setStartTime(timeTable.getStartTime());
        dto.setEndTime(timeTable.getEndTime());
        dto.setTeacherName(timeTable.getTeacher().getName() + " " + timeTable.getTeacher().getSurname());
        return dto;
    }
}