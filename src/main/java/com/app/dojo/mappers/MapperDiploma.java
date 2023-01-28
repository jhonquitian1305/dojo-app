package com.app.dojo.mappers;

import com.app.dojo.builders.builderDTO.DiplomaDTOResponseBuilder;
import com.app.dojo.dtos.DiplomaDTOResponse;
import com.app.dojo.models.Diploma;
import org.springframework.stereotype.Component;

@Component
public class MapperDiploma {
    public DiplomaDTOResponse mapDiplomaDTOResponse(Diploma diploma){
        return new DiplomaDTOResponseBuilder()
                .setId(diploma.getId())
                .setDiplomaName(diploma.getDiplomaName())
                .setUser(diploma.getUser())
                .build();
    }
}
