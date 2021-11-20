package com.glicemap.repository;

import com.glicemap.model.Medic;
import com.glicemap.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.documentNumber = :documentNumber")
    User findByDocumentNumber(@Param("documentNumber") String documentNumber);

    @Query("SELECT u FROM User u WHERE u.email = :email")
    User findByEmail(@Param("email") String email);

    @Query("SELECT u FROM User u WHERE u.medic = :medic AND (lower(u.name) LIKE :nome% OR lower(u.lastName) LIKE :nome%) AND u.medicJoin >= :dateFrom AND u.medicJoin <= :dateTo")
    List<User> findByNameLikeAndDatesAndMedic(@Param("medic") Medic medic, @Param("nome") String nome, @Param("dateFrom") Date dateFrom, @Param("dateTo") Date dateTo);

    @Query("SELECT u FROM User u WHERE u.medic = :medic AND u.medicJoin >= :dateFrom AND u.medicJoin <= :dateTo")
    List<User> findByDatesAndMedic(@Param("medic") Medic medic, @Param("dateFrom") Date dateFrom, @Param("dateTo") Date dateTo);
}
