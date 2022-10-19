package com.app.dojo.repositories;

import com.app.dojo.models.Day;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DayRespository extends JpaRepository<Day,Long> {
    Day findByDay(String day);
}
