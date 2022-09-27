package com.app.dojo.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "student")
public class Student extends User implements Serializable {

    public Student() {
        super();
    }
    public Student(Long id, String dni, String names, String lastnames, Date birthday, String email, String password) {
        super(id, dni, names, lastnames, birthday, email, password);
    }
}
