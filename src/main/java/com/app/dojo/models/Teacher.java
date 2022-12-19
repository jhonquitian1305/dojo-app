package com.app.dojo.models;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
@Entity
@Table(name = "teachers")
public class Teacher extends User implements Serializable {
    public Teacher() {
        super();
    }

    public Teacher(Long id, String dni, String names, String lastnames, Date birthday, String email, String password) {
        super(id, dni, names, lastnames, birthday, email, password);
    }
}
