package com.example.stock.repository;

import com.example.stock.model.Stock;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StockRepository extends CrudRepository<Stock, Long> {
    Optional<Stock> findByName(String name);
}
