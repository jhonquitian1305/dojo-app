package com.app.dojo.repositories;

import com.app.dojo.models.Diploma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiplomaRepository extends JpaRepository<Diploma, Long> {
    Boolean existsByDiplomaName(String diplomaName);
}
