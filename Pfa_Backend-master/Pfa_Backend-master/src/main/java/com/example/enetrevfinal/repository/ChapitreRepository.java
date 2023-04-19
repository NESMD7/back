package com.example.enetrevfinal.repository;

import com.example.enetrevfinal.entity.Chapitre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChapitreRepository extends JpaRepository<Chapitre,String> {
    List<Chapitre> findAllByMatiereId(long idMatiere);
}


