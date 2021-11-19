package com.glicemap.repository;

import com.glicemap.model.MedicInvite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicInviteRepository extends JpaRepository<MedicInvite, Long> {

    @Query(value = "SELECT * FROM codigo_medico cm WHERE cm.codigo = ?1 AND cm.status_codigo = 1 AND GETDATE() < DateADD(day, 2, cm.data_criacao)", nativeQuery=true)
    MedicInvite findByCode(String code);

    @Query(value = "SELECT * FROM codigo_medico cm WHERE cm.crm_medico = ?1 AND cm.status_codigo = 1", nativeQuery=true)
    MedicInvite findActiveByMedic(String crm);
}
