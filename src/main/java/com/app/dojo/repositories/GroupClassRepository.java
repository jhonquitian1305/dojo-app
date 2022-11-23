package com.app.dojo.repositories;

import com.app.dojo.models.GroupClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupClassRepository extends JpaRepository<GroupClass,Long> {
}
