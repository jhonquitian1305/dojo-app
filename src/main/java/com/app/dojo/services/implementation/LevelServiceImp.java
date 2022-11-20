package com.app.dojo.services.implementation;

import com.app.dojo.constants.Message;
import com.app.dojo.dtos.LevelDTO;
import com.app.dojo.dtos.LevelResponse;
import com.app.dojo.exception.errors.BadRequest;
import com.app.dojo.mappers.MapperLevel;
import com.app.dojo.models.Level;
import com.app.dojo.repositories.LevelRepository;
import com.app.dojo.services.Interfaces.LevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


import java.util.Optional;

@Service
public class LevelServiceImp implements LevelService {

    @Autowired
    private LevelRepository levelRepository;
    @Autowired
    private MapperLevel mapperLevel;

    @Override
    public LevelDTO create(LevelDTO levelDTO) {
        Optional<Level> levelFound=this.levelRepository.findByName(levelDTO.getName().toUpperCase());
        if(levelFound.isPresent()) throw new BadRequest(Message.MESSAGE_BAD_REQUEST_CREATE_LEVEL.formatted(levelDTO.getName()));
        levelDTO.setName(levelDTO.getName().toUpperCase());
        Level levelSaved=this.levelRepository.save(mapperLevel.mapperLevel(levelDTO));
        return mapperLevel.mapperLevelDTO(levelSaved);
    }

    @Override
    public LevelResponse getAll() {
        return null;
    }

    @Override
    public LevelDTO getOne(Long id) {
        return null;
    }
}
