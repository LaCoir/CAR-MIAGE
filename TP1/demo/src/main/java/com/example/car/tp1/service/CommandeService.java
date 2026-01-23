package com.example.car.tp1.service;

import com.example.car.tp1.model.Client;
import com.example.car.tp1.repository.CommandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommandeService {
    private CommandeRepository commandeRepository;

    public CommandeService(CommandeRepository commandeRepository){
        this.commandeRepository=commandeRepository;
    }

    public void createCommande(Client client) {
            Commande commande = new Commande(client);
            commande.setTitre(titre);
            commandeRepository.save(commande);
    }

   public Commande getCommandebyId(Long id) {
       return commandeRepository.findById(id).orElse(null);
   }

   public Commande getCommandebyClient(Client client) {
       return commandeRepository.findByClient(client);
   }

}
