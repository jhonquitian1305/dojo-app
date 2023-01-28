package com.app.dojo.controllers;

import com.app.dojo.constants.EndPointsConstants;
import com.app.dojo.dtos.DiplomaDTO;
import com.app.dojo.dtos.DiplomaDTOResponse;
import com.app.dojo.mappers.MapperDiploma;
import com.app.dojo.services.Interfaces.DiplomaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.app.dojo.constants.EndPointsConstants.*;

@RestController
@RequestMapping(ENDPOINT_DIPLOMAS)
public class DiplomaController {
    @Autowired
    DiplomaService diplomaService;

    @Autowired
    MapperDiploma mapperDiploma;

    @PostMapping(ENDPOINT_DIPLOMAS_STUDENT)
    public ResponseEntity<DiplomaDTOResponse> saveDiplomaStudent(@PathVariable("id") Long id, @RequestBody DiplomaDTO diplomaDTO) throws Exception {
        DiplomaDTOResponse diplomaSaved = this.mapperDiploma.mapDiplomaDTOResponse(this.diplomaService.saveDiplomaStudent(id, diplomaDTO));
        return new ResponseEntity<>(diplomaSaved, HttpStatus.CREATED);
    }
}
