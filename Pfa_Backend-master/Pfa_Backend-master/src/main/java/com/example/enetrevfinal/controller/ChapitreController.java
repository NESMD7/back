package com.example.enetrevfinal.controller;

import com.example.enetrevfinal.entity.Chapitre;
import com.example.enetrevfinal.message.ResponseFile;
import com.example.enetrevfinal.message.ResponseMessage;
import com.example.enetrevfinal.service.ChapitreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@Controller

public class ChapitreController {
    @Autowired
    private ChapitreService storageService;

    @PostMapping("/upload")
    @CrossOrigin(exposedHeaders="Access-Control-Allow-Origin")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file, @RequestParam Integer IdMatiere) {
        String message = "";
        try {
            storageService.store(file,IdMatiere);

            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

    @GetMapping("/files")
    @CrossOrigin(exposedHeaders="Access-Control-Allow-Origin")
    public ResponseEntity<List<ResponseFile>> getListFiles() {
        List<ResponseFile> files = storageService.getAllFiles().map(dbFile -> {
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/files/")
                    .path(dbFile.getId())
                    .toUriString();

            return new ResponseFile(dbFile.getNom(), fileDownloadUri, dbFile.getMatiere().getId(),dbFile.getData().length);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(files);
    }

    @GetMapping("/files/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable String id) {
        Chapitre fileDB = storageService.getFile(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getNom() + "\"")
                .body(fileDB.getData());
    }

    @GetMapping("/chapitres/{idMatiere}")
    @CrossOrigin(exposedHeaders="Access-Control-Allow-Origin")
    public ResponseEntity<List<ResponseFile>> getAllChapitresByMatiereId(@PathVariable long idMatiere) {
        List<Chapitre> chapitres = storageService.getAllChapitresByMatiereId(idMatiere);
        List<ResponseFile> responseFiles = chapitres.stream()
                .map(chapitre -> {
                    String fileDownloadUri = ServletUriComponentsBuilder
                            .fromCurrentContextPath()
                            .path("/files/")
                            .path(chapitre.getId())
                            .toUriString();
                    return new ResponseFile(chapitre.getNom(), fileDownloadUri, chapitre.getMatiere().getId(),chapitre.getData().length);
                })
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(responseFiles);
    }
}
