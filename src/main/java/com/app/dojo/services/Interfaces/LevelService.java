package com.app.dojo.services.Interfaces;

import com.app.dojo.dtos.LevelDTO;
import com.app.dojo.dtos.LevelResponse;

public interface LevelService {
    LevelDTO create(LevelDTO levelDTO);
    LevelResponse getAll();
    LevelDTO getOne(Long id);
}
