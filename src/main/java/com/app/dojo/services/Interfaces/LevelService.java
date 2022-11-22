package com.app.dojo.services.Interfaces;

import com.app.dojo.dtos.LevelDTO;
import com.app.dojo.dtos.LevelResponse;
import com.app.dojo.models.Level;

public interface LevelService {
    Level create(LevelDTO levelDTO);
    LevelResponse getAll(int numberPage, int pageSize);
    Level getOne(Long id);
    void delete(Long id);
}
