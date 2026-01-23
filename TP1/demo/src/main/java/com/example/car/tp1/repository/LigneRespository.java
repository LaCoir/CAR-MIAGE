package com.example.car.tp1.repository;

import org.springframework.data.repository.CrudRepository;

public interface LigneRespository extends CrudRepository<Ligne, long> {
    List<Ligne> findByCommande(Commande commande);
}
