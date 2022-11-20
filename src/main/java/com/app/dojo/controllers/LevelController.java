package com.app.dojo.controllers;

import com.app.dojo.constants.EndPointsConstants;
import com.app.dojo.dtos.LevelDTO;
import com.app.dojo.services.Interfaces.LevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(EndPointsConstants.ENDPOINT_LEVELS)
public class LevelController {
    @Autowired
    private LevelService levelService;

    @PostMapping()
    public ResponseEntity<LevelDTO> create(@Valid @RequestBody() LevelDTO levelDTO) throws Exception{
        LevelDTO levelSaved=this.levelService.create(levelDTO);
        return new ResponseEntity<>(levelSaved, HttpStatus.CREATED);
    }
}
