package com.app.dojo.mappers;

import com.app.dojo.builders.builderDTO.CourseDTOResponseBuilder;
import com.app.dojo.dtos.CourseDTOResponse;
import com.app.dojo.models.Course;
import org.springframework.stereotype.Component;

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
                .build();
    }
}
