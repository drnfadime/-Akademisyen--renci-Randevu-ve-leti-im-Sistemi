package com.fusis.original.repository;

import com.fusis.original.entity.TimeTable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TimeTableRepository extends JpaRepository<TimeTable, Integer> {
    List<TimeTable> findByTeacher_Teacherid(Integer teacherId);
}