package com.app.dojo.services.strategyGroups;

import com.app.dojo.models.GroupClass;
import com.app.dojo.models.Room;
import com.app.dojo.repositories.GroupClassRepository;
import com.app.dojo.services.Interfaces.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class GroupsRoom implements GroupsStrategy{

    private final GroupClassRepository groupClassRepository;
    private final RoomService roomService;

    public GroupsRoom(GroupClassRepository groupClassRepository, RoomService roomService) {
        this.groupClassRepository = groupClassRepository;
        this.roomService = roomService;
    }

    @Override
    public Page<GroupClass> findGroups(Pageable pageable, Long idCondition) throws Exception {
        Room roomFound=this.roomService.findById(idCondition);
        return this.groupClassRepository.findByRooms(roomFound,pageable);
    }
}
