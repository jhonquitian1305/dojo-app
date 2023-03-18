package com.app.dojo.services.strategyGroups;

import com.app.dojo.repositories.GroupClassRepository;
import com.app.dojo.services.Interfaces.CourseService;
import com.app.dojo.services.Interfaces.RoomService;
import com.app.dojo.services.Interfaces.ScheduleServcie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GroupsContext {
    private GroupsStrategy strategy;
    @Autowired
    private GroupClassRepository groupClassRepository;
    @Autowired
    private CourseService courseService;
    @Autowired
    private RoomService roomService;
    @Autowired
    private ScheduleServcie scheduleServcie;

    public GroupsStrategy loadStrategy(String modelCondition){
        switch (modelCondition){
            case "ROOM":
                strategy= new GroupsRoom(groupClassRepository,roomService);
                break;
            case "COURSE":
                strategy= new GroupsCourse(groupClassRepository,courseService);
                break;
            case "SCHEDULE":
                strategy= new GroupsSchedule(groupClassRepository,scheduleServcie);
                break;
            default:
                strategy= new Groups(groupClassRepository);
        }
        return strategy;
    }
}
