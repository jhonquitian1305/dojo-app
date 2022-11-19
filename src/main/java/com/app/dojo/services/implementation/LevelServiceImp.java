package com.app.dojo.services.implementation;

import com.app.dojo.dtos.LevelDTO;
import com.app.dojo.dtos.LevelResponse;
import com.app.dojo.mappers.MapperLevel;
import com.app.dojo.repositories.LevelRepository;
import com.app.dojo.services.Interfaces.LevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LevelServiceImp implements LevelService {

    @Autowired
    private LevelRepository levelRepository;
    @Autowired
    private MapperLevel mapperLevel;

    @Override
    public LevelDTO create(LevelDTO levelDTO) {
        return null;
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
