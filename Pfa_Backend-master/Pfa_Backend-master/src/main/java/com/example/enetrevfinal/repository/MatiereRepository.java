package com.example.enetrevfinal.repository;

import com.example.enetrevfinal.entity.Matiere;
import com.example.enetrevfinal.entity.Periode;
import com.example.enetrevfinal.entity.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository

public interface MatiereRepository extends JpaRepository<Matiere, Long> {
    List<Matiere> findBySection(Section section);
    List<Matiere> findByPeriode(Periode periode);
    Matiere findById(long id);

}