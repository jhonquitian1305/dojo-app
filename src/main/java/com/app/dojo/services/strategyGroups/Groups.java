package com.app.dojo.services.strategyGroups;

import com.app.dojo.models.GroupClass;
import com.app.dojo.repositories.GroupClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
@Component
public class Groups implements GroupsStrategy {


    private final GroupClassRepository groupClassRepository;

    public Groups(GroupClassRepository groupClassRepository) {
        this.groupClassRepository = groupClassRepository;
    }

    @Override
    public Page<GroupClass> findGroups(Pageable pageable, Long idCondition) {
        return this.groupClassRepository.findAll(pageable);
    }
}
