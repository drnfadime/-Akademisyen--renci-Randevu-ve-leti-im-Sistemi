package com.fusis.original.dto.request;

import lombok.Data;

@Data
public class TeacherRequestDTO {
    private String name;
    private String surname;
    private String email;
    private String password;
}