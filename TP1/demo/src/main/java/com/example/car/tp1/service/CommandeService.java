package com.example.car.tp1.service;

import com.example.car.tp1.model.Client;
import com.example.car.tp1.model.Commande;
import com.example.car.tp1.repository.CommandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommandeService {
    @Autowired
    private CommandeRepository commandeRepository;


    public void createCommande(Commande commande) {
        commandeRepository.save(commande);
    }

    public Commande getCommandeById(Long id) {
        return commandeRepository.findById(id).orElse(null);
    }
    public Iterable<Commande> readAllCommande() {return commandeRepository.findAll();
    }

    public java.util.List<Commande> getCommandesByClient(Client client) {
        return commandeRepository.findByClient(client);
    }

    public void deleteCommande(Long id) {
        commandeRepository.deleteById(id);
    }

}
