package com.fusis.original.dto.response;

import lombok.Data;

@Data
public class TimeTableResponseDTO {
    private Integer id;
    private String day;
    private String startTime;
    private String endTime;
    private String teacherName;
}