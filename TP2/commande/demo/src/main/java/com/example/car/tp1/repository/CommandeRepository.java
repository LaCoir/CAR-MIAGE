package com.example.car.tp1.repository;

import com.example.car.tp1.model.Client;
import com.example.car.tp1.model.Commande;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommandeRepository extends CrudRepository<Commande, Long> {

        // đã có sẵn save và findAll từ CrudRepository nên ko cần viết gì thêm
        // in order to login we have to find client by email and password

    List<Commande> findByClient(Client client);

}