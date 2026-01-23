package com.example.car.tp1.service;

import com.example.car.tp1.model.Client;
import com.example.car.tp1.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;
    // Spring tự động tiêm ClientRepository vào đây (Constructor Injection)
    public Client findByEmailAndPassword(String email, String password) {
        return clientRepository.findByEmailAndPassword(email, password);
    }
    public Client findByEmail(String email) {
        return clientRepository.findByEmail(email);
    }

    public void createClient(Client client) {
       clientRepository.save(client);
    }

    public Iterable<Client> readAllClients() {
        return clientRepository.findAll();
    }



}
