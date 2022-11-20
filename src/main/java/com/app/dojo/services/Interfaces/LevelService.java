package com.app.dojo.services.Interfaces;

import com.app.dojo.dtos.LevelDTO;
import com.app.dojo.dtos.LevelResponse;

public interface LevelService {
    LevelDTO create(LevelDTO levelDTO);
    LevelResponse getAll(int numberPage, int pageSize);
    LevelDTO getOne(Long id);
    void delete(Long id);
}
