package com.app.dojo.services.strategyGroups;

import com.app.dojo.models.Course;
import com.app.dojo.models.GroupClass;
import com.app.dojo.repositories.GroupClassRepository;
import com.app.dojo.services.Interfaces.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class GroupsCourse implements GroupsStrategy{
    private final GroupClassRepository groupClassRepository;
    private final CourseService courseService;

    public GroupsCourse(GroupClassRepository groupClassRepository, CourseService courseService) {
        this.groupClassRepository = groupClassRepository;
        this.courseService = courseService;
    }

    @Override
    public Page<GroupClass> findGroups(Pageable pageable, Long idCondition) {
        Course courseFound=this.courseService.getOne(idCondition);
        return this.groupClassRepository.findByCourse(courseFound,pageable);
    }
}
