package com.app.dojo.controllers;

import com.app.dojo.dtos.StudentDTO;
import com.app.dojo.models.Student;
import com.app.dojo.services.Interfaces.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.app.dojo.constants.EndPointsConstants.ENDPOINT_ID;
import static com.app.dojo.constants.EndPointsConstants.ENDPOINT_STUDENTS;

@RestController
@RequestMapping(ENDPOINT_STUDENTS)
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping
    public ResponseEntity<StudentDTO> saveStudent(@RequestBody StudentDTO studentDTO){
        return new ResponseEntity<>(studentService.saveStudent(studentDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents(){
        return new ResponseEntity<>(studentService.getAllStudents(), HttpStatus.OK);
    }

    @GetMapping(ENDPOINT_ID)
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable("id") Long id){
        return new ResponseEntity<>(studentService.getStudentById(id), HttpStatus.OK);
    }

    @DeleteMapping(ENDPOINT_ID)
    public ResponseEntity<String> deleteStudent(@PathVariable("id") Long id){
        this.studentService.deleteStudent(id);
        return new ResponseEntity<>("Student Deleted", HttpStatus.OK);
    }
}
