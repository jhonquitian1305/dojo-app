package com.app.dojo.controllers;

import com.app.dojo.constants.EndPointsConstants;
import com.app.dojo.constants.PaginationRequest;
import com.app.dojo.dtos.CourseDTO;
import com.app.dojo.dtos.CourseDTOResponse;
import com.app.dojo.dtos.CourseResponse;
import com.app.dojo.mappers.MapperCourse;
import com.app.dojo.models.Course;
import com.app.dojo.services.Interfaces.CourseService;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(EndPointsConstants.ENDPOINT_COURSES)
public class CourseController {
    @Autowired
    private CourseService courseService;

    @Autowired
    private MapperCourse mapperCourse;

    @PostMapping()
    public ResponseEntity<CourseDTOResponse> create(@Valid @RequestBody CourseDTO courseDTO) throws Exception {
        CourseDTOResponse courseSaved=this.mapperCourse.mapperCourseDTOResponse(this.courseService.create(courseDTO));
        return new ResponseEntity<>(courseSaved, HttpStatus.CREATED);
    }

    @GetMapping()
    public  ResponseEntity<CourseResponse> getAll(
            @RequestParam(value = "numberPage", defaultValue = PaginationRequest.DEFAULT_NUMBER_PAGE, required = false) int numberPage,
            @RequestParam(value = "pageSize",defaultValue = PaginationRequest.DEFAULT_PAGE_SIZE, required = false)int pageSize
    ){
        return ResponseEntity.ok(this.courseService.getAll(numberPage,pageSize));
    }

    @GetMapping(EndPointsConstants.ENDPOINT_ID)
    public ResponseEntity<CourseDTOResponse> getOne(@PathVariable("id") Long id){
        Course courseFound=this.courseService.getOne(id);
        return ResponseEntity.ok(mapperCourse.mapperCourseDTOResponse(courseFound));
    }

    @PutMapping(EndPointsConstants.ENDPOINT_ID)
    public  ResponseEntity<CourseDTOResponse> update(@PathVariable("id") Long id, @RequestBody() CourseDTO courseDTO){
        Course courseUpdated=this.courseService.update(id, courseDTO);
        return ResponseEntity.ok(mapperCourse.mapperCourseDTOResponse(courseUpdated));
    }

    @DeleteMapping(EndPointsConstants.ENDPOINT_ID)
    public ResponseEntity<Void> delete(@PathVariable("id")Long id){
        this.courseService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
