package com.example.car.tp1.respository;


import com.example.car.tp1.model.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ClientRepository extends CrudRepository<Client, String> {
    // đã có sẵn save và findAll từ CrudRepository nên ko cần viết gì thêm
    // in order to login we have to find client by email and password
    Client findByEmailAndPassword(String email, String password);
}
