package com.app.dojo.controllers;

import com.app.dojo.constants.PaginationRequest;
import com.app.dojo.dtos.TeacherDTO;
import com.app.dojo.dtos.TeacherResponse;
import com.app.dojo.mappers.MapperTeacher;
import com.app.dojo.models.Teacher;
import com.app.dojo.services.Interfaces.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static com.app.dojo.constants.EndPointsConstants.*;

@RestController
@RequestMapping(ENDPOINT_TEACHERS)
public class TeacherController {
    @Autowired
    TeacherService teacherService;

    @Autowired
    MapperTeacher mapperTeacher;

    @PostMapping
    public ResponseEntity<TeacherDTO> save(@RequestBody TeacherDTO teacherDTO){
        TeacherDTO teacherSaved = this.mapperTeacher.mapTeacherDTO(this.teacherService.save(teacherDTO));
        return new ResponseEntity<>(teacherSaved, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<TeacherResponse> getAll(
            @RequestParam(value = "pageNo", defaultValue = PaginationRequest.DEFAULT_NUMBER_PAGE, required = false) int numberPage,
            @RequestParam(value = "pageSize", defaultValue = PaginationRequest.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = PaginationRequest.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = PaginationRequest.DEFAULT_SORT_DIR, required = false) String sortDir
    ){
        return new ResponseEntity<>(this.teacherService.getAll(numberPage, pageSize, sortBy, sortDir), HttpStatus.OK);
    }

    @GetMapping(ENDPOINT_ID)
    public ResponseEntity<TeacherDTO> getById(@PathVariable("id") Long id){
        TeacherDTO teacherFound = this.mapperTeacher.mapTeacherDTO(this.teacherService.getById(id));
        return new ResponseEntity<>(teacherFound, HttpStatus.OK);
    }

    @GetMapping(ENDPOINT_DNI)
    public ResponseEntity<TeacherDTO> getByDni(@RequestBody TeacherDTO teacherDTO){
        TeacherDTO teacherFound = this.mapperTeacher.mapTeacherDTO(this.teacherService.getByDni(teacherDTO));
        return new ResponseEntity<>(teacherFound, HttpStatus.OK);
    }

    @GetMapping(ENDPOINT_EMAIL)
    public ResponseEntity<TeacherDTO> getByEmail(@RequestBody TeacherDTO teacherDTO){
        TeacherDTO teacherFound = this.mapperTeacher.mapTeacherDTO(this.teacherService.getByEmail(teacherDTO));
        return new ResponseEntity<>(teacherFound, HttpStatus.OK);
    }

    @PutMapping(ENDPOINT_ID)
    public ResponseEntity<TeacherDTO> updateOne(@PathVariable("id") Long id, @RequestBody TeacherDTO teacherDTO){
        TeacherDTO teacherUpdated = this.mapperTeacher.mapTeacherDTO(this.teacherService.updateOne(id, teacherDTO));
        return new ResponseEntity<>(teacherUpdated, HttpStatus.OK);
    }
}
