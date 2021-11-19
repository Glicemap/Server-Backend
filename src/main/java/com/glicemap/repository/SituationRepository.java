package com.glicemap.repository;

import com.glicemap.model.Situation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SituationRepository extends JpaRepository<Situation, Long> {

    @Query("SELECT s FROM Situation s WHERE s.code = :id")
    Situation findByCode(@Param("id") int id);

    @Query("SELECT s FROM Situation s WHERE s.situation = :situation")
    Situation findBySituation(@Param("situation") String situation);

}
