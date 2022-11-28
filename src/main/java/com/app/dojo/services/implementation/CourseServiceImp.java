package com.app.dojo.services.implementation;

import com.app.dojo.builders.builderDTO.CourseResponseBuilder;
import com.app.dojo.builders.builderModels.CourseBuilder;
import com.app.dojo.constants.Message;
import com.app.dojo.dtos.CourseDTO;
import com.app.dojo.dtos.CourseResponse;
import com.app.dojo.exception.errors.BadRequest;
import com.app.dojo.exception.errors.NotFoundException;
import com.app.dojo.mappers.MapperCourse;
import com.app.dojo.models.Course;
import com.app.dojo.models.Level;
import com.app.dojo.models.Room;
import com.app.dojo.models.Schedule;
import com.app.dojo.repositories.CourseRepository;
import com.app.dojo.services.Interfaces.CourseService;
import com.app.dojo.services.Interfaces.LevelService;
import com.app.dojo.services.Interfaces.RoomService;
import com.app.dojo.services.Interfaces.ScheduleServcie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseServiceImp  implements CourseService {

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private LevelService levelService;
    @Autowired
    private MapperCourse mapperCourse;

    @Override
    public Course create(CourseDTO courseDTO) throws Exception {
        if(!courseDTO.getFinishDate().after(courseDTO.getStartDate())) throw new BadRequest(Message.MESSAGE_BAD_REQUEST_COURSES_DATE);
        if(courseRepository.existsCourseByName(courseDTO.getName().toUpperCase())) throw  new BadRequest(Message.MESSAGE_BAD_REQUEST_COURSES_NAME);
        Level levelFound=this.levelService.getOne(courseDTO.getLevel());
       return this.courseRepository.save(
                new CourseBuilder()
                        .setPrice(courseDTO.getPrice())
                        .setName(courseDTO.getName().toUpperCase())
                        .setStartDate(courseDTO.getStartDate())
                        .setFinishDate(courseDTO.getFinishDate())
                        .setLevel(levelFound)
                        .build()
        );
    }

    @Override
    public Course getOne(Long id) {
        Optional<Course> courseFound=this.courseRepository.findById(id);
        if(courseFound.isEmpty()) throw new NotFoundException(Message.MESSAGE_NOT_FOUND_BY_ID_COURSES.formatted(id));
        return courseFound.get();
    }

    @Override
    public CourseResponse getAll(int numberPage, int pageSize) {
        Pageable pageable= PageRequest.of(numberPage,pageSize);
        Page<Course> coursesFound=this.courseRepository.findAll(pageable);
        return new CourseResponseBuilder()
                .setContent(coursesFound.getContent())
                .setLastOne(coursesFound.isLast())
                .setSizePage(coursesFound.getSize())
                .setTotalElements(coursesFound.getTotalElements())
                .setNumberPage(coursesFound.getNumber())
                .setTotalPages(coursesFound.getTotalPages())
                .build();
    }

    @Override
    public Course update(Long id, CourseDTO courseDTO) {
        Course courseFound=getOne(id);

        if(courseRepository.existsCourseByName(courseDTO.getName().toUpperCase())) throw  new BadRequest(Message.MESSAGE_BAD_REQUEST_COURSES_NAME);
        if(!courseDTO.getFinishDate().after(courseDTO.getStartDate())) throw new BadRequest(Message.MESSAGE_BAD_REQUEST_COURSES_DATE);

        Level levelFound=this.levelService.getOne(courseDTO.getLevel());
        return this.courseRepository.save(this.mapperCourse.updateInformation(courseFound,courseDTO,levelFound));
    }

    @Override
    public CourseResponse findByLevel(Long idLevel, int numberPage, int pageSize) {
        Level levelFound=levelService.getOne(idLevel);
        Pageable pageable= PageRequest.of(numberPage,pageSize);
        Page<Course> coursesFound=this.courseRepository.findByLevel(levelFound,pageable);
        if (coursesFound.getContent().size()==0) throw new NotFoundException(Message.MESSAGE_BAD_REQUEST_COURSES_BY_LEVEL.formatted(levelFound.getName()));
        return new CourseResponseBuilder()
                .setContent(coursesFound.getContent())
                .setTotalPages(coursesFound.getTotalPages())
                .setTotalElements(coursesFound.getTotalElements())
                .setNumberPage(coursesFound.getNumber())
                .setSizePage(coursesFound.getSize())
                .setLastOne(coursesFound.isLast())
                .build();
    }

    @Override
    public void delete(Long id) {
        getOne(id);
        this.courseRepository.deleteById(id);
    }

}
