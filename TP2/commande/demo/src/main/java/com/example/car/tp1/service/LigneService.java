package com.example.car.tp1.service;

import com.example.car.tp1.model.Ligne;
import com.example.car.tp1.repository.LigneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LigneService {
    @Autowired
    private LigneRepository ligneRepository;


    public void createLigne(Ligne ligne) {
        ligneRepository.save(ligne);
    }

    public List<Ligne> readAllLigne() {
        return (List<Ligne>) ligneRepository.findAll();
    }

    public Ligne findById(Long id) {
        Optional<Ligne> optionalLigne = ligneRepository.findById(id);
        return optionalLigne.orElse(null);
    }
}
