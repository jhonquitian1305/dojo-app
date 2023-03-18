package com.app.dojo.models;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class User {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @Column(name = "dni", unique = true, nullable = false)
    private String dni;

    @Column(name = "names", nullable = false, length = 50)
    private String names;

    @Column(name = "lastnames", nullable = false, length = 50)
    private String lastnames;

    @Column(name = "birthday", nullable = false)
    private Date birthday;

    @Column(name = "email", nullable = false, length = 50, unique = true)
    private String email;

    @Column(name = "password", nullable = false, length = 200)
    private String password;

    public User() {
    }

    public User(Long id, String dni, String names, String lastnames, Date birthday, String email, String password) {
        this.id = id;
        this.dni = dni;
        this.names = names;
        this.lastnames = lastnames;
        this.birthday = birthday;
        this.email = email;
        this.password = password;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public void setLastnames(String lastnames) {
        this.lastnames = lastnames;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
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
