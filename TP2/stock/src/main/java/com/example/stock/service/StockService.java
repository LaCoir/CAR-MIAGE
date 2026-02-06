package com.example.stock.service;

import com.example.stock.model.Stock;
import com.example.stock.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockService {
    @Autowired
    private StockRepository stockRepository;
    public StockService(StockRepository stockRepository){
        this. stockRepository = stockRepository;
    }
    public void createStock(String name, Float quantity) {
        var stock= new Stock(name,quantity);
        stockRepository.save(stock);
    }

    public Iterable<Stock> readAllStock() {return stockRepository.findAll();
    }

}
