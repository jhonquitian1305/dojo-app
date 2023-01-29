package com.app.dojo.dtos;

public class DiplomaDTO {
    private Long id;
    private String diplomaName;
    private Long user;

    public DiplomaDTO() {
    }

    public DiplomaDTO(Long id, String diplomaName, Long user) {
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

    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }
}
