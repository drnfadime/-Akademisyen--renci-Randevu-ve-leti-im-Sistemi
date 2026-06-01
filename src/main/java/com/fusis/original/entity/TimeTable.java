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
    @Column(name = "\"timeTableid\"")
    private Integer timeTableid;

    private String day;
    private String startTime;
    private String endTime;
// bu çalışma saati hangi hıcaya ait
    @ManyToOne
    @JoinColumn(name = "\"teacherId\"")
    private Teacher teacher;
}