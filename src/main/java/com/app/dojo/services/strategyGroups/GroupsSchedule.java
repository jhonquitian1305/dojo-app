package com.app.dojo.services.strategyGroups;

import com.app.dojo.models.GroupClass;
import com.app.dojo.models.Schedule;
import com.app.dojo.repositories.GroupClassRepository;
import com.app.dojo.services.Interfaces.ScheduleServcie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class GroupsSchedule implements GroupsStrategy {
    private  final GroupClassRepository groupClassRepository;

    private  final ScheduleServcie scheduleServcie;

    public GroupsSchedule(GroupClassRepository groupClassRepository, ScheduleServcie scheduleServcie) {
        this.groupClassRepository = groupClassRepository;
        this.scheduleServcie = scheduleServcie;
    }

    @Override
    public Page<GroupClass> findGroups(Pageable pageable, Long idCondition) throws Exception {
        Schedule scheduleFound=this.scheduleServcie.findOne(idCondition);
        return this.groupClassRepository.findBySchedules(scheduleFound,pageable);
    }
}
