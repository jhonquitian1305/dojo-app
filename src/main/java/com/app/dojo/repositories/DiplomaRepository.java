package com.app.dojo.repositories;

import com.app.dojo.dtos.DiplomaById;
import com.app.dojo.models.Diploma;
import com.app.dojo.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DiplomaRepository extends JpaRepository<Diploma, Long> {
    Boolean existsByDiplomaName(String diplomaName);
    Page<Diploma> findByUser(User user, Pageable pageable);

    @Query("select d.id as id, d.diplomaName as diplomaName, d.user as user from Diploma d inner join User u on d.user = u.id where u.id = ?1 and d.id = ?2")
    DiplomaById findOneDiploma(Long idUser, Long idDiploma);
}
