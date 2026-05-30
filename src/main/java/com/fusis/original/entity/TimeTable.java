package com.fusis.original.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "\"timeTable\"")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class TimeTable {

    @Id
    private Integer timeTableid;

    private String day;
    private String startTime;
    private String endTime;

    // Bu çalışma saati hangi öğretmene ait?
    @ManyToOne
    @JoinColumn(name = "teacherId")
    private Teacher teacher;
}