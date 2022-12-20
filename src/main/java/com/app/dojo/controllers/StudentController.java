package com.app.dojo.controllers;

import com.app.dojo.constants.PaginationRequest;
import com.app.dojo.dtos.StudentDTO;
import com.app.dojo.dtos.StudentResponse;
import com.app.dojo.mappers.MapperStudent;
import com.app.dojo.services.Interfaces.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.app.dojo.constants.EndPointsConstants.*;

@RestController
@RequestMapping(ENDPOINT_STUDENTS)
public class StudentController {
    @Autowired
    private StudentService studentService;

    @Autowired
    MapperStudent mapperStudent;

    @PostMapping
    public ResponseEntity<StudentDTO> saveStudent(@RequestBody StudentDTO studentDTO){
        StudentDTO studentSave = mapperStudent.mapStudentDTO(studentService.saveStudent(studentDTO));
        return new ResponseEntity<>(studentSave, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<StudentResponse> getAllStudents(
            @RequestParam(value = "pageNo", defaultValue = PaginationRequest.DEFAULT_NUMBER_PAGE, required = false) int numberPage,
            @RequestParam(value = "pageSize", defaultValue = PaginationRequest.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = PaginationRequest.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = PaginationRequest.DEFAULT_SORT_DIR, required = false) String sortDir
    ){
        return new ResponseEntity<>(studentService.getAllStudents(numberPage, pageSize, sortBy, sortDir), HttpStatus.OK);
    }

    @GetMapping(ENDPOINT_ID)
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable("id") Long id) throws Exception {
        StudentDTO studentFound = mapperStudent.mapStudentDTO(studentService.getStudentById(id));
        return new ResponseEntity<>(studentFound, HttpStatus.OK);
    }

    @GetMapping(ENDPOINT_DNI)
    public ResponseEntity<StudentDTO> getStudentByDni(@RequestBody StudentDTO studentDTO) throws Exception {
        StudentDTO studentFound = mapperStudent.mapStudentDTO(studentService.getStudentByDni(studentDTO));
        return new ResponseEntity<>(studentFound, HttpStatus.OK);
    }

    @GetMapping(ENDPOINT_EMAIL)
    public ResponseEntity<StudentDTO> getStudentByEmail(@RequestBody StudentDTO studentDTO){
        StudentDTO studentFound = mapperStudent.mapStudentDTO(studentService.getStudentByEmail(studentDTO));
        return new ResponseEntity<>(studentFound, HttpStatus.OK);
    }

    @DeleteMapping(ENDPOINT_ID)
    public ResponseEntity<String> deleteStudent(@PathVariable("id") Long id) throws Exception {
        this.studentService.deleteStudent(id);
        return new ResponseEntity<>("Student Deleted", HttpStatus.OK);
    }
}
