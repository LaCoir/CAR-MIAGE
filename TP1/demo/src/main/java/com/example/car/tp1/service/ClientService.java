package com.example.car.tp1.service;

import com.example.car.tp1.model.Client;
import com.example.car.tp1.respository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRespository;
    // Spring tự động tiêm ClientRepository vào đây (Constructor Injection)
    public Client findByEmailAndPassword(String email, String password) {
        return clientRespository.findByEmailAndPassword(email, password);
    }

    public void createClient(Client client) {
       clientRespository.save(client);
    }

    public Iterable<Client> readAllClients() {
        return clientRespository.findAll();
    }



}
