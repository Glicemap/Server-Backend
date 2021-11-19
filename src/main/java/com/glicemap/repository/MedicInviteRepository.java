package com.glicemap.repository;

import com.glicemap.model.Medic;
import com.glicemap.model.MedicInvite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicInviteRepository extends JpaRepository<MedicInvite, Long> {

    @Query("SELECT mi FROM MedicInvite mi WHERE mi.code = :code AND mi.status = 1 AND GETDATE() < DateADD(day, 2, mi.createdDate)")
    MedicInvite findByCode(@Param("code") String code);

    @Query("SELECT mi FROM MedicInvite mi WHERE mi.medic = :medic AND mi.status = true")
    MedicInvite findActiveByMedic(@Param("medic") Medic medic);
}
