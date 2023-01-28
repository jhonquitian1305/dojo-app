package com.app.dojo.builders.builderDTO;

import com.app.dojo.dtos.DiplomaDTO;

public class DiplomaDTOBuilder {
    private Long id;
    private String diplomaName;
    private Long user;

    public DiplomaDTOBuilder setId(Long id) {
        this.id = id;
        return this;
    }

    public DiplomaDTOBuilder setDiplomaName(String diplomaName) {
        this.diplomaName = diplomaName;
        return this;
    }

    public DiplomaDTOBuilder setUser(Long user) {
        this.user = user;
        return this;
    }

    public DiplomaDTO build(){
        return new DiplomaDTO(id, diplomaName, user);
    }
}
