package com.example.enetrevfinal.service;

import com.example.enetrevfinal.entity.Section;
import com.example.enetrevfinal.repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class SectionService {

    @Autowired
    private SectionRepository sectionRepository;

    public List<Section> getAllSections() {
        return sectionRepository.findAll();
    }


    public Section createSection(Section section) {
        if (section.getName() == null || section.getName().isEmpty()) {
            // handle null or empty value
            throw new IllegalArgumentException("Section name cannot be null or empty");
        }
        return sectionRepository.save(section);
    }


}
