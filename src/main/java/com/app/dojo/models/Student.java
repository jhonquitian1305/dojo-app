package com.app.dojo.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dni", unique = true, nullable = false)
    private String dni;

    @Column(name = "names", nullable = false, length = 50)
    private String names;

    @Column(name = "lastnames", nullable = false, length = 50)
    private String lastnames;

    @Column(name = "birthday", nullable = false)
    private Date birthday;

    @Column(name = "email", nullable = false, length = 50)
    private String email;

    @Column(name = "password", nullable = false, length = 50)
    private String password;

    public Student() {
    }

    public Student(Long id, String dni, String names, String lastnames, Date birthday, String email, String password) {
        this.id = id;
        this.dni = dni;
        this.names = names;
        this.lastnames = lastnames;
        this.birthday = birthday;
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public String getDni() {
        return dni;
    }

    public String getNames() {
        return names;
    }

    public String getLastnames() {
        return lastnames;
    }

    public Date getBirthday() {
        return birthday;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
