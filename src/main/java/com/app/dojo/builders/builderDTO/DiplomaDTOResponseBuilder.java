package com.app.dojo.builders.builderDTO;

import com.app.dojo.dtos.DiplomaDTOResponse;
import com.app.dojo.models.User;

public class DiplomaDTOResponseBuilder {
    private Long id;
    private String diplomaName;
    private User user;

    public DiplomaDTOResponseBuilder setId(Long id) {
        this.id = id;
        return this;
    }

    public DiplomaDTOResponseBuilder setDiplomaName(String diplomaName) {
        this.diplomaName = diplomaName;
        return this;
    }

    public DiplomaDTOResponseBuilder setUser(User user) {
        this.user = user;
        return this;
    }

    public DiplomaDTOResponse build(){
        return new DiplomaDTOResponse(id, diplomaName, user);
    }
}
