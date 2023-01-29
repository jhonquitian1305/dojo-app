package com.app.dojo.controllers;

import com.app.dojo.constants.PaginationRequest;
import com.app.dojo.dtos.DiplomaDTO;
import com.app.dojo.dtos.DiplomaDTOResponse;
import com.app.dojo.dtos.DiplomaResponse;
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

    @PostMapping(ENDPOINT_DIPLOMAS_TEACHER)
    public ResponseEntity<DiplomaDTOResponse> saveDiplomaTeacher(@PathVariable("id") Long id, @RequestBody DiplomaDTO diplomaDTO){
        DiplomaDTOResponse diplomaSaved = this.mapperDiploma.mapDiplomaDTOResponse(this.diplomaService.saveDiplomaTeacher(id, diplomaDTO));
        return new ResponseEntity<>(diplomaSaved, HttpStatus.CREATED);
    }

    @GetMapping(ENDPOINT_DIPLOMAS_STUDENT)
    public ResponseEntity<DiplomaResponse> findStudent(
            @PathVariable("id") Long id,
            @RequestParam(value = "pageNo", defaultValue = PaginationRequest.DEFAULT_NUMBER_PAGE, required = false) int numberPage,
            @RequestParam(value = "pageSize", defaultValue = PaginationRequest.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = PaginationRequest.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = PaginationRequest.DEFAULT_SORT_DIR, required = false) String sortDir
    ) throws Exception {
        return new ResponseEntity<>(this.diplomaService.getDiplomasStudent(id, numberPage, pageSize, sortBy, sortDir), HttpStatus.OK);
    }
}
