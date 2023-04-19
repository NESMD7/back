package com.example.enetrevfinal.service;

import com.example.enetrevfinal.entity.Periode;
import com.example.enetrevfinal.repository.PeriodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class PeriodeService {
    @Autowired
    private PeriodeRepository periodeRepository;

    public List<Periode> getAllPeriode() {
        return periodeRepository.findAll();
    }
    public Periode createPeriode(Periode periode) {
        if (periode.getName() == null) {
            throw new IllegalArgumentException("Periode name cannot be null");
        }
        return periodeRepository.save(periode);
    }

}
