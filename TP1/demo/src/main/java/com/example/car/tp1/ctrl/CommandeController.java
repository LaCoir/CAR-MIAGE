package com.example.car.tp1.ctrl;

import com.example.car.tp1.model.Client;
import com.example.car.tp1.model.Commande;
import com.example.car.tp1.service.ClientService;
import com.example.car.tp1.service.CommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class CommandeController {
    @Autowired
    private CommandeService commandeService;

    @Autowired
    private ClientService clientService;

    @GetMapping("/home/commande/")
    public ModelAndView commandeHome(@RequestParam(name = "clientId", required = false) String email) {
        var currentClients = new Client(); // tao client rong mac dinh neu ko commande se loi
        var commande = commandeService.readAllCommande(); //list commande
        var emptyCommande = new Commande(); // để binding form
        if (email != null && !email.isEmpty()) {
            Client client = clientService.findByEmail(email);
            if (client != null) {
                currentClients = client;
                emptyCommande.setClient(client);
            }
        }

        var modelData = Map.of("commande", commande, "newCommande", emptyCommande, "clients", currentClients);
        return new ModelAndView("commande", modelData);
    }

    @PostMapping("/home/commande/save")
    public String saveCommande(@ModelAttribute Commande commande) {
        commandeService.createCommande(commande);
        return "redirect:/home/commande"; // sau khi lưu xong quay về trang home
    }
}
