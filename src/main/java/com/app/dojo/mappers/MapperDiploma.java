package com.app.dojo.mappers;

import com.app.dojo.builders.builderDTO.DiplomaDTOResponseBuilder;
import com.app.dojo.builders.builderModels.DiplomaBuilder;
import com.app.dojo.dtos.DiplomaById;
import com.app.dojo.dtos.DiplomaDTO;
import com.app.dojo.dtos.DiplomaDTOResponse;
import com.app.dojo.models.Diploma;
import com.app.dojo.models.User;
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

    public Diploma createDiploma(DiplomaDTO diplomaDTO, User user){
        return new DiplomaBuilder()
                .setId(diplomaDTO.getId())
                .setDiplomaName(diplomaDTO.getDiplomaName())
                .setUser(user)
                .build();
    }

    public Diploma mapDiploma(DiplomaById diplomaById){
        return new DiplomaBuilder()
                .setId(diplomaById.getId())
                .setDiplomaName(diplomaById.getDiplomaName())
                .setUser(diplomaById.getUser())
                .build();
    }
}
