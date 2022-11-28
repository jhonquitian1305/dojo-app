package com.app.dojo.services.strategyGroups;

import com.app.dojo.models.GroupClass;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GroupsStrategy {
    Page<GroupClass> findGroups(Pageable pageable, Long idCondition) throws Exception;
}
