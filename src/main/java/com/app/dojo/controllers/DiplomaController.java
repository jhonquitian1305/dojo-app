package com.app.dojo.controllers;

import com.app.dojo.constants.PaginationRequest;
import com.app.dojo.dtos.DiplomaById;
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
    public ResponseEntity<DiplomaDTOResponse> saveDiplomaStudent(@PathVariable("idStudent") Long id, @RequestBody DiplomaDTO diplomaDTO) throws Exception {
        DiplomaDTOResponse diplomaSaved = this.mapperDiploma.mapDiplomaDTOResponse(this.diplomaService.saveDiplomaStudent(id, diplomaDTO));
        return new ResponseEntity<>(diplomaSaved, HttpStatus.CREATED);
    }

    @PostMapping(ENDPOINT_DIPLOMAS_TEACHER)
    public ResponseEntity<DiplomaDTOResponse> saveDiplomaTeacher(@PathVariable("idTeacher") Long id, @RequestBody DiplomaDTO diplomaDTO){
        DiplomaDTOResponse diplomaSaved = this.mapperDiploma.mapDiplomaDTOResponse(this.diplomaService.saveDiplomaTeacher(id, diplomaDTO));
        return new ResponseEntity<>(diplomaSaved, HttpStatus.CREATED);
    }

    @GetMapping(ENDPOINT_DIPLOMAS_STUDENT)
    public ResponseEntity<DiplomaResponse> findStudent(
            @PathVariable("idStudent") Long id,
            @RequestParam(value = "pageNo", defaultValue = PaginationRequest.DEFAULT_NUMBER_PAGE, required = false) int numberPage,
            @RequestParam(value = "pageSize", defaultValue = PaginationRequest.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = PaginationRequest.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = PaginationRequest.DEFAULT_SORT_DIR, required = false) String sortDir
    ) throws Exception {
        return new ResponseEntity<>(this.diplomaService.getDiplomasStudent(id, numberPage, pageSize, sortBy, sortDir), HttpStatus.OK);
    }

    @GetMapping(ENDPOINT_DIPLOMAS_TEACHER)
    public ResponseEntity<DiplomaResponse> findTeacher(
            @PathVariable("idTeacher") Long id,
            @RequestParam(value = "pageNo", defaultValue = PaginationRequest.DEFAULT_NUMBER_PAGE, required = false) int numberPage,
            @RequestParam(value = "pageSize", defaultValue = PaginationRequest.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = PaginationRequest.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = PaginationRequest.DEFAULT_SORT_DIR, required = false) String sortDir
    ){
        return new ResponseEntity<>(this.diplomaService.getDiplomasTeacher(id, numberPage, pageSize, sortBy, sortDir), HttpStatus.OK);
    }

    @GetMapping(ENDPOINT_DIPLOMAS_STUDENT+ENDPOINT_DIPLOMA)
    public ResponseEntity<DiplomaDTOResponse> findOneDiplomaStudent(
            @PathVariable("idStudent") Long idStudent,
            @PathVariable("idDiploma") Long idDiploma
    ) throws Exception {
        DiplomaDTOResponse diplomaFound = this.mapperDiploma.mapDiplomaDTOResponse(this.diplomaService.getByIdDiplomaStudent(idStudent, idDiploma));
        return new ResponseEntity<>(diplomaFound, HttpStatus.OK);
    }

    @GetMapping(ENDPOINT_DIPLOMAS_TEACHER+ENDPOINT_DIPLOMA)
    public ResponseEntity<DiplomaDTOResponse> findOneDiplomaTeacher(
            @PathVariable("idTeacher") Long idTeacher,
            @PathVariable("idDiploma") Long idDiploma
    ){
        DiplomaDTOResponse diplomaFound = this.mapperDiploma.mapDiplomaDTOResponse(this.diplomaService.getByIdDiplomaTeacher(idTeacher, idDiploma));
        return new ResponseEntity<>(diplomaFound, HttpStatus.OK);
    }

    @DeleteMapping(ENDPOINT_DIPLOMAS_STUDENT+ENDPOINT_DIPLOMA)
    public ResponseEntity<String> deleteDiplomaStudent(
            @PathVariable("idStudent") Long idStudent,
            @PathVariable("idDiploma") Long idDiploma
    ) throws Exception {
        this.diplomaService.deleteDiplomaStudent(idStudent, idDiploma);
        return new ResponseEntity<>("Diploma deleted", HttpStatus.OK);
    }

    @DeleteMapping(ENDPOINT_DIPLOMAS_TEACHER+ENDPOINT_DIPLOMA)
    public ResponseEntity<String> deleteDiplomaTeacher(
            @PathVariable("idTeacher") Long idTeacher,
            @PathVariable("idDiploma") Long idDiploma
    ) {
        this.diplomaService.deleteDiplomaTeacher(idTeacher, idDiploma);
        return new ResponseEntity<>("Diploma deleted", HttpStatus.OK);
    }
}
