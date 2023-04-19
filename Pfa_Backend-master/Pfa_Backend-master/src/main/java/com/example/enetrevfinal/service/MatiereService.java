package com.example.enetrevfinal.service;

import com.example.enetrevfinal.entity.Matiere;
import com.example.enetrevfinal.entity.Periode;
import com.example.enetrevfinal.entity.Section;
import com.example.enetrevfinal.repository.MatiereRepository;
import com.example.enetrevfinal.repository.PeriodeRepository;
import com.example.enetrevfinal.repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.*;

@Service
public class MatiereService {

    @Autowired
    private MatiereRepository matiereRepository;

    @Autowired
    private SectionRepository sectionRepository;

    @Autowired
    private PeriodeRepository periodeRepository;

    public List<Matiere> getAllMatieres() {
        return matiereRepository.findAll();
    }

    public List<Matiere> getMatieresBySection(Long sectionId) {
        Section section = sectionRepository.findById(sectionId).orElse(null);
        if (section != null) {
            return matiereRepository.findBySection(section);
        }
        return new ArrayList<>();
    }

    public List<Matiere> getMatieresByPeriodeName(String periodeName) {
        Periode periode = periodeRepository.findByName(periodeName);
        if (periode != null) {
            return matiereRepository.findByPeriode(periode);
        }
        return new ArrayList<>();
    }


    public Matiere createMatiere2(Matiere matiere, String sectionName, String periodeName) {
        Section section = sectionRepository.findSectionByName(sectionName);
        if (section == null) {
            throw new IllegalArgumentException("Section not found with name: " + sectionName);
        }

        Periode periode = periodeRepository.findByName(periodeName);
        if (periode == null) {
            throw new IllegalArgumentException("Periode not found with name: " + periodeName);
        }

        matiere.setSection(section);
        matiere.setPeriode(periode);
        return matiereRepository.save(matiere);
    }

    public Matiere createMatiere(Matiere matiere, Long sectionId, Long periodeId) {
        Section section = sectionRepository.findById(sectionId).orElse(null);
        Periode periode = periodeRepository.findById(periodeId).orElse(null);
        if (section != null && periode != null) {
            matiere.setSection(section);
            matiere.setPeriode(periode);

            return matiereRepository.save(matiere);
        }
        return null;
    }

    public List<Matiere> addMatieresToSections(List<Matiere> matieres) {
        Map<Section, List<Matiere>> matieresBySection = new HashMap<>();
        for (Matiere matiere : matieres) {
            Section section = matiere.getSection();
            if (section != null) {
                List<Matiere> matieresForSection = matieresBySection.getOrDefault(section, new ArrayList<>());
                matieresForSection.add(matiere);
                matieresBySection.put(section, matieresForSection);
            }
        }

        List<Matiere> savedMatieres = new ArrayList<>();
        for (Map.Entry<Section, List<Matiere>> entry : matieresBySection.entrySet()) {
            Section section = entry.getKey();
            List<Matiere> matieresForSection = entry.getValue();

            for (Matiere matiere : matieresForSection) {
                matiere.setSection(section);
            }

            savedMatieres.addAll(matiereRepository.saveAll(matieresForSection));
        }

        return savedMatieres;
    }

    public List<Matiere> findBySection(String sectionName) {
        Section section = new Section();
        section.setName(sectionName);
        return matiereRepository.findBySection(section);
    }




}


