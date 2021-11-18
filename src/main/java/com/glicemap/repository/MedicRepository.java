package com.glicemap.repository;

import com.glicemap.model.Medic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicRepository extends JpaRepository<Medic, Long> {

    @Query("SELECT m FROM Medic m WHERE m.CRM = :crm")
    Medic findByDocumentNumber(@Param("crm") String crm);

}
