package com.app.dojo.controllers;

import com.app.dojo.constants.EndPointsConstants;
import com.app.dojo.constants.PaginationRequest;
import com.app.dojo.dtos.LevelDTO;
import com.app.dojo.dtos.LevelResponse;
import com.app.dojo.services.Interfaces.LevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping()
    public  ResponseEntity<LevelResponse> getAll(
            @RequestParam(value = "numberPage", defaultValue = PaginationRequest.DEFAULT_NUMBER_PAGE, required = false) int numberPage,
            @RequestParam(value = "pageSize",defaultValue = PaginationRequest.DEFAULT_PAGE_SIZE, required = false)int pageSize

    ){
        return ResponseEntity.ok(this.levelService.getAll(numberPage,pageSize));
    }

    @GetMapping(EndPointsConstants.ENDPOINT_ID)
    public ResponseEntity<LevelDTO> getOne(@PathVariable("id") Long id){
        LevelDTO levelFound=this.levelService.getOne(id);
        return ResponseEntity.ok(levelFound);
    }

    @DeleteMapping(EndPointsConstants.ENDPOINT_ID)
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        this.levelService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
