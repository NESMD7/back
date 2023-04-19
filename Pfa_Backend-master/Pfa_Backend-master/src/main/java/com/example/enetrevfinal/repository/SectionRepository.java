package com.example.enetrevfinal.repository;

import com.example.enetrevfinal.entity.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SectionRepository extends JpaRepository<Section, Long> {
    Optional<Section> findByName(String name);
    List<Section> findAll();

    @Query("SELECT s.name FROM Section s")
    List<String> findAllNames();

    Section findSectionByName(String name);

}