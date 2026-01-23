package com.example.car.tp1.service;

import org.springframework.stereotype.Service;

@Service
public class LigneService {
    private LigneRepository ligneRepository;
    private CommandeRepository commandeRepository;

    public LigneService(LigneRepository ligneRepository, CommandeRepository commandeRepository) {
        this.ligneRepository = ligneRepository;
        this.commandeRepository = commandeRepository;
    }

    public void ajouterLigne(Long commandeId, String libelle, int qte, double prix) {
        Commande commande = commandeRepository.findById(commandeId).orElse(null);

        if (commande != null) {
            Ligne ligne = new Ligne(libelle, qte, prix, commande);
            ligneRepository.save(ligne);
        }
    }

    public void supprimerLigne(Long ligneId) {
        ligneRepository.deleteById(ligneId);
    }

    public List<Ligne> getLignesByCommande(Commande commande) {
        return ligneRepository.findByCommande(commande);
    }
}
