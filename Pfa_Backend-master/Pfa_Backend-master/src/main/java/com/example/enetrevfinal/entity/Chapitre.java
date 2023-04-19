package com.example.enetrevfinal.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "chapitre4")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Chapitre {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column(name = "Nom")
    private String Nom;
    @Column(name = "Type")
    private String Type;
    @Lob
    private byte[] data;
    @ManyToOne
    @JoinColumn(name="IdMatiere")
    private Matiere  matiere ;


}