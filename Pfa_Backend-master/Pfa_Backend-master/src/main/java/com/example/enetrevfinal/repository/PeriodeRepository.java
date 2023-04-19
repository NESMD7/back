package com.example.enetrevfinal.repository;

import com.example.enetrevfinal.entity.Periode;
import com.example.enetrevfinal.entity.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PeriodeRepository extends JpaRepository<Periode, Long> {
    List<Periode> findAll();
    @Query("SELECT p.name FROM Periode p")
    List<String> findAllNames();
    Periode findByName(String name);

    @Query("SELECT p.name FROM Periode p WHERE p.id = :periodeId")
    String findNameById(@Param("periodeId") Long periodeId);

}
