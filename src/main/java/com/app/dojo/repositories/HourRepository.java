package com.app.dojo.repositories;

import com.app.dojo.models.Hour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HourRepository extends JpaRepository<Hour,Long> {
    Hour findByHour(String hour);
}
