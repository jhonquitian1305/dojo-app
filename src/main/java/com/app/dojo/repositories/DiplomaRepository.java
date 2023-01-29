package com.app.dojo.repositories;

import com.app.dojo.models.Diploma;
import com.app.dojo.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiplomaRepository extends JpaRepository<Diploma, Long> {
    Boolean existsByDiplomaName(String diplomaName);
    Page<Diploma> findByUser(User user, Pageable pageable);
}
