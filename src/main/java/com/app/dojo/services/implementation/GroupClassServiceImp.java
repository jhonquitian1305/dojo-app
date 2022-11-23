package com.app.dojo.services.implementation;

import com.app.dojo.dtos.GroupClassDTO;
import com.app.dojo.dtos.GroupClassResponse;
import com.app.dojo.models.GroupClass;
import com.app.dojo.repositories.GroupClassRepository;
import com.app.dojo.services.Interfaces.CourseService;
import com.app.dojo.services.Interfaces.GroupClassService;
import com.app.dojo.services.Interfaces.RoomService;
import com.app.dojo.services.Interfaces.ScheduleServcie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupClassServiceImp implements GroupClassService {

    @Autowired
    private GroupClassRepository groupClassRepository;
    @Autowired
    private CourseService courseService;
    @Autowired
    private RoomService roomService;
    @Autowired
    private ScheduleServcie scheduleServcie;

    @Override
    public GroupClass create(GroupClassDTO groupClassDTO) {
        return null;
    }

    @Override
    public GroupClass getOne(Long id) {
        return null;
    }

    @Override
    public GroupClassResponse getAll(int numberPage, int pageSize) {
        return null;
    }

    @Override
    public GroupClassResponse getByCourse(Long idCourse, int numberPage, int pageSize) {
        return null;
    }

    @Override
    public GroupClassResponse getByRoom(Long idRoom, int numberPage, int pageSize) {
        return null;
    }

    @Override
    public GroupClassResponse getBySchedule(Long idSchedule, int numberPage, int pageSize) {
        return null;
    }

    @Override
    public GroupClass update(Long id, GroupClassDTO groupClassDTO) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
