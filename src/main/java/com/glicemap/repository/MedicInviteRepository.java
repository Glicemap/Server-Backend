package com.glicemap.repository;

import com.glicemap.model.MedicInvite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicInviteRepository extends JpaRepository<MedicInvite, Long> {

}
