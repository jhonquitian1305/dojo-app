package com.app.dojo.services.Interfaces;

import com.app.dojo.dtos.GroupClassDTO;
import com.app.dojo.dtos.GroupClassResponse;
import com.app.dojo.models.GroupClass;

public interface GroupClassService {
    GroupClass create(GroupClassDTO groupClassDTO) throws Exception;
    GroupClass getOne(Long id);
    GroupClassResponse getAll(int numberPage, int pageSize,Long idCondition, String modelCondition) throws Exception;
    GroupClass update(Long id, GroupClassDTO groupClassDTO);
    void delete(Long id);

}
