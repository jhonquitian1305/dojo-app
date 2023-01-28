package com.app.dojo.builders.builderModels;

import com.app.dojo.models.Diploma;
import com.app.dojo.models.User;

public class DiplomaBuilder {
    private Long id;
    private String diplomaName;
    private User user;

    public DiplomaBuilder setId(Long id) {
        this.id = id;
        return this;
    }

    public DiplomaBuilder setDiplomaName(String diplomaName) {
        this.diplomaName = diplomaName;
        return this;
    }

    public DiplomaBuilder setUser(User user) {
        this.user = user;
        return this;
    }

    public Diploma build(){
        return new Diploma(id, diplomaName, user);
    }
}
