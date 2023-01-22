package com.app.dojo.mappers;

import com.app.dojo.builders.builderDTO.CourseDTOResponseBuilder;
import com.app.dojo.dtos.CourseDTO;
import com.app.dojo.dtos.CourseDTOResponse;
import com.app.dojo.models.Course;
import com.app.dojo.models.Level;
import org.springframework.stereotype.Component;

import java.rmi.dgc.Lease;

@Component
public class MapperCourse {

    public CourseDTOResponse mapperCourseDTOResponse(Course course){
        return new CourseDTOResponseBuilder()
                .setId(course.getId())
                .setName(course.getName())
                .setPrice(course.getPrice())
                .setStartDate(course.getStartDate())
                .setFinishDate(course.getFinishDate())
                .setLevel(course.getLevel())
                .setTeachers(course.getTeachers())
                .build();
    }

    public Course updateInformation(Course courseFound, CourseDTO course, Level level){
        courseFound.setFinishDate(course.getFinishDate());
        courseFound.setStartDate(course.getStartDate());
        courseFound.setPrice(course.getPrice());
        courseFound.setName(course.getName().toUpperCase());
        courseFound.setLevel(level);
        return courseFound;
    }
}
