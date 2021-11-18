package com.glicemap.repository;

import com.glicemap.model.Measure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface MeasureRepository extends JpaRepository<Measure, Long>{

    @Query("SELECT m FROM Measure m WHERE m.user = :documentNumber AND MONTH(m.createdDate) = MONTH(:date)")
    List<Measure> findByMonth(@Param("documentNumber") String documentNumber, @Param("date") Date date);

    @Query("SELECT m FROM Measure m WHERE m.user = :documentNumber AND m.createdDate = :date")
    List<Measure> findByDate(@Param("documentNumber") String documentNumber, @Param("date") Date date);
}
