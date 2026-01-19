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
    // @Autowired là tùy chọn ở đây nếu chỉ có 1 constructor, nhưng viết vào cho rõ

    public void createClient(Client client) {
       clientRespository.save(client);
    }

    public Iterable<Client> readAllClients() {
        return clientRespository.findAll();
    }



}
