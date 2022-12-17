package com.app.dojo.dtos;

import java.util.Date;

public class TeacherDTO extends UserDTO {
    public TeacherDTO(Long id, String dni, String names, String lastnames, Date birthday, String email, String password) {
        super(id, dni, names, lastnames, birthday, email, password);
    }
}