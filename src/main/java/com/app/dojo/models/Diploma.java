package com.app.dojo.models;

import jakarta.persistence.*;

@Entity
@Table(name = "Diplomas")
public class Diploma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String diplomaName;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;

    public Diploma() {
    }

    public Diploma(Long id, String diplomaName, User user) {
        this.id = id;
        this.diplomaName = diplomaName;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDiplomaName() {
        return diplomaName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setDiplomaName(String diplomaName) {
        this.diplomaName = diplomaName;
    }
}
