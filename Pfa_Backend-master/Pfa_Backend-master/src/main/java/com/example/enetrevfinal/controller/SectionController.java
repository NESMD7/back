package com.example.enetrevfinal.controller;

import com.example.enetrevfinal.entity.Section;
import com.example.enetrevfinal.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sections")
public class SectionController {

    @Autowired
    private SectionService sectionService;

    @GetMapping
    public List<Section> getAllSections() {
        return sectionService.getAllSections();
    }

    @PostMapping("/addsections")
    public Section createSection(@RequestBody Section section) {
        return sectionService.createSection(section);
    }
}
