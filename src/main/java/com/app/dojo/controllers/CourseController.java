package com.app.dojo.controllers;

import com.app.dojo.constants.EndPointsConstants;
import com.app.dojo.dtos.CourseDTO;
import com.app.dojo.dtos.CourseDTOResponse;
import com.app.dojo.mappers.MapperCourse;
import com.app.dojo.models.Course;
import com.app.dojo.services.Interfaces.CourseService;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
