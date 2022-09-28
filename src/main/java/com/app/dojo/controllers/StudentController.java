package com.app.dojo.controllers;

import com.app.dojo.dtos.StudentDTO;
import com.app.dojo.models.Student;
import com.app.dojo.services.Interfaces.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.app.dojo.constants.EndPointsConstants.*;

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
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable("id") Long id) throws Exception {
        return new ResponseEntity<>(studentService.getStudentById(id), HttpStatus.OK);
    }

    @GetMapping(ENDPOINT_STUDENT_BY_DNI)
    public ResponseEntity<StudentDTO> getStudentByDni(@RequestBody StudentDTO studentDTO) throws Exception {
        return new ResponseEntity<>(studentService.getStudentByDni(studentDTO), HttpStatus.OK);
    }

    @DeleteMapping(ENDPOINT_ID)
    public ResponseEntity<String> deleteStudent(@PathVariable("id") Long id) throws Exception {
        this.studentService.deleteStudent(id);
        return new ResponseEntity<>("Student Deleted", HttpStatus.OK);
    }
}
