package com.app.dojo.services.implementation;

import com.app.dojo.builders.builderDTO.LevelResponseBuilder;
import com.app.dojo.constants.Message;
import com.app.dojo.dtos.LevelDTO;
import com.app.dojo.dtos.LevelResponse;
import com.app.dojo.exception.errors.BadRequest;
import com.app.dojo.exception.errors.NotFoundException;
import com.app.dojo.mappers.MapperLevel;
import com.app.dojo.models.Level;
import com.app.dojo.repositories.LevelRepository;
import com.app.dojo.services.Interfaces.LevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public LevelResponse getAll(int numberPage, int pageSize) {
        Pageable pageable=PageRequest.of(numberPage,pageSize);
        Page<Level> levelsFound=this.levelRepository.findAll(pageable);
        List<Level> allLevelsFound=levelsFound.getContent();
        List<LevelDTO> levels=allLevelsFound.stream().map(level->mapperLevel.mapperLevelDTO(level)).collect(Collectors.toList());
        return new LevelResponseBuilder()
                .setContent(levels)
                .setNumberPage(levelsFound.getNumber())
                .setSizePage(levelsFound.getSize())
                .setLastOne(levelsFound.isLast())
                .setTotalElements(levelsFound.getTotalElements())
                .setTotalPages(levelsFound.getTotalPages())
                .build();
    }

    @Override
    public LevelDTO getOne(Long id) {
        Optional<Level> levelFound=this.levelRepository.findById(id);
        if(levelFound.isEmpty()) throw  new NotFoundException(Message.MESSAGE_NOT_FOUND_LEVEL_ID.formatted(id));
        return mapperLevel.mapperLevelDTO(levelFound.get());
    }

    @Override
    public void delete(Long id) {
        getOne(id);
        this.levelRepository.deleteById(id);
    }
}
