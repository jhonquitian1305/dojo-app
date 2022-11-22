package com.app.dojo.services.implementation;

import com.app.dojo.dtos.CourseDTO;
import com.app.dojo.dtos.CourseResponse;
import com.app.dojo.models.Course;
import com.app.dojo.models.Schedule;
import com.app.dojo.repositories.CourseRepository;
import com.app.dojo.services.Interfaces.CourseService;
import com.app.dojo.services.Interfaces.LevelService;
import com.app.dojo.services.Interfaces.RoomService;
import com.app.dojo.services.Interfaces.ScheduleServcie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImp  implements CourseService {

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private LevelService levelService;
    @Autowired
    private ScheduleServcie scheduleServcie;
    @Autowired
    private RoomService roomService;

    @Override
    public Course create(CourseDTO courseDTO) {
        return null;
    }

    @Override
    public Course getOne(Long id) {
        return null;
    }

    @Override
    public Course getByName(String name) {
        return null;
    }

    @Override
    public CourseResponse getAll() {
        return null;
    }

    @Override
    public Course update(Long id, CourseDTO courseDTO) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
