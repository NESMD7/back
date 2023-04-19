package com.example.enetrevfinal.controller;

import com.example.enetrevfinal.entity.Matiere;
import com.example.enetrevfinal.service.MatiereService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/matieres")
public class MatiereController {

    @Autowired
    private MatiereService matiereService;

    @GetMapping
    public List<Matiere> getAllMatieres() {
        return matiereService.getAllMatieres();
    }

    @GetMapping("/section/{sectionId}")
    public List<Matiere> getMatieresBySection(@PathVariable Long sectionId) {
        return matiereService.getMatieresBySection(sectionId);
    }
    @GetMapping("/periode/{periodeName}")
    public List<Matiere> getMatieresByPeriodeName(@PathVariable String periodeName) {
        return matiereService.getMatieresByPeriodeName(periodeName);
    }

    @PostMapping("/section/{sectionId}/periode/{periodeId}/matiere")
    public Matiere addMatiere(@RequestBody Matiere matiere, @PathVariable Long sectionId, @PathVariable Long periodeId) {
        return matiereService.createMatiere(matiere, sectionId, periodeId);
    }

    @PostMapping("/sections/matieres")
    public List<Matiere> addMatieresToSections(@RequestBody List<Matiere> matieres) {
        return matiereService.addMatieresToSections(matieres);
    }


    @GetMapping("/{sectionName}")
    public List<Matiere> findBySection(@PathVariable String sectionName) {
        return matiereService.findBySection(sectionName);
    }


    @PostMapping("/matiere")
    public Matiere addMatiere(@RequestBody Matiere matiere) {
        String sectionName = matiere.getSection().getName();
        String periodeName = matiere.getPeriode().getName();
        return matiereService.createMatiere2(matiere, sectionName, periodeName);
    }




}
