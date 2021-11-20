package com.glicemap.repository;

import com.glicemap.model.Medic;
import com.glicemap.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    @Query("SELECT n FROM Notification n WHERE n.medic = :medic and n.status = true")
    List<Notification> findAllByMedic(@Param("medic") Medic medic);

    @Query("SELECT n FROM Notification n WHERE n.code = :code")
    Notification findByCode(@Param("code") int code);
}
