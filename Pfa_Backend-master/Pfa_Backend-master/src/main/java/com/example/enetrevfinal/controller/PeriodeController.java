package com.example.enetrevfinal.controller;

import com.example.enetrevfinal.entity.Periode;
import com.example.enetrevfinal.entity.Section;
import com.example.enetrevfinal.repository.PeriodeRepository;
import com.example.enetrevfinal.service.PeriodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/periodes")
public class PeriodeController {
    @Autowired
    private PeriodeService periodeService;
    private PeriodeRepository periodeRepository;



    @GetMapping
    public List<Periode> getAllPeriodeNames() {
        return periodeService.getAllPeriode();
    }



    @PostMapping("/addperiodes")
    public Periode createPeriode(@RequestBody Periode periode) {
        return periodeService.createPeriode(periode);
    }
}
