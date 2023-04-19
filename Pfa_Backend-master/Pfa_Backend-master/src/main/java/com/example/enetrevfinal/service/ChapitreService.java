package com.example.enetrevfinal.service;

import com.example.enetrevfinal.entity.Chapitre;
import com.example.enetrevfinal.repository.ChapitreRepository;
import com.example.enetrevfinal.repository.MatiereRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

@Service
public class ChapitreService {
    @Autowired
    private ChapitreRepository fileDBRepository;
    @Autowired
    private MatiereRepository matiereRepository;

    public Chapitre store(MultipartFile file, Integer matiere) throws IOException {
        Chapitre c = new Chapitre();
        c.setMatiere(matiereRepository.findById(matiere));
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        c.setNom(fileName);
        c.setData(file.getBytes());


        return fileDBRepository.save(c);
    }

    public Chapitre getFile(String id) {
        return fileDBRepository.findById(id).get();
    }

    public Stream<Chapitre> getAllFiles() {
        return fileDBRepository.findAll().stream();
    }

    public List<Chapitre> getAllChapitresByMatiereId(long idMatiere) {
        return fileDBRepository.findAllByMatiereId(idMatiere);
    }
}
