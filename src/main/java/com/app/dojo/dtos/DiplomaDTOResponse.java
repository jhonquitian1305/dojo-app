package com.app.dojo.dtos;

import com.app.dojo.models.User;

public class DiplomaDTOResponse {
    private Long id;
    private String diplomaName;
    private User user;

    public DiplomaDTOResponse() {
    }

    public DiplomaDTOResponse(Long id, String diplomaName, User user) {
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

    public void setDiplomaName(String diplomaName) {
        this.diplomaName = diplomaName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
