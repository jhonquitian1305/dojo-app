package com.app.dojo.controllers;

import com.app.dojo.dtos.TeacherDTO;
import com.app.dojo.mappers.MapperTeacher;
import com.app.dojo.services.Interfaces.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.app.dojo.constants.EndPointsConstants.ENDPOINT_TEACHERS;

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
}
