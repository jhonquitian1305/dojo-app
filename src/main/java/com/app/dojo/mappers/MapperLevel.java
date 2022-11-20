package com.app.dojo.mappers;

import com.app.dojo.builders.builderDTO.LevelDTOBuilder;
import com.app.dojo.builders.builderModels.LevelBuilder;
import com.app.dojo.dtos.LevelDTO;
import com.app.dojo.models.Level;
import org.springframework.stereotype.Component;

@Component
public class MapperLevel {

    public Level mapperLevel(LevelDTO levelDTO){
        return new LevelBuilder()
                .setId(levelDTO.getId())
                .setName(levelDTO.getName())
                .build();
    }

    public LevelDTO mapperLevelDTO(Level level){
        return new LevelDTOBuilder()
                .setId(level.getId())
                .setName(level.getName())
                .build();
    }
}
