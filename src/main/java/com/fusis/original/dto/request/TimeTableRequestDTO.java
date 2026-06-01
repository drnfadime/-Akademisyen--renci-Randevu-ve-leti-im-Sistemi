package com.fusis.original.dto.request;

import lombok.Data;

@Data
public class TimeTableRequestDTO {
    private Integer teacherId;
    private String day;
    private String startTime;
    private String endTime;
}