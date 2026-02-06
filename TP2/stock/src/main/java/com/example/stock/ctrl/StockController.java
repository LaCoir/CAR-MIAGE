package com.example.stock.ctrl;

import com.example.stock.repository.StockRepository;
import com.example.stock.service.StockService;
import com.example.stock.model.Stock;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Map;

@Controller
public class StockController {
    private StockService stockService;

    public StockController(StockService stockService){
        this.stockService = stockService;
    }
    @GetMapping("/home")
    public ModelAndView home(){
        var stocks=stockService.readAllStock();
        var model = Map.of("stock", stocks);
        return new ModelAndView("stock", model);
    }
    @PostMapping("/home/create")
    public RedirectView createStock(String name, Float quantity){
        stockService.createStock(name, quantity);
        return new RedirectView("/home");
    }
}
