package com.app.dojo.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentDTO extends UserDTO {
    public StudentDTO(Long id, String dni, String names, String lastnames, Date birthday, String email, String password) {
        super(id, dni, names, lastnames, birthday, email, password);
    }
}
