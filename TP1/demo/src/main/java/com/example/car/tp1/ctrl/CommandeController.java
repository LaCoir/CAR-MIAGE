package com.example.car.tp1.ctrl;

import com.example.car.tp1.model.Client;
import com.example.car.tp1.model.Commande;
import com.example.car.tp1.service.ClientService;
import com.example.car.tp1.service.CommandeService;
import jakarta.servlet.http.HttpSession;
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

    @GetMapping("/commande")
    public ModelAndView commandeHome(@RequestParam(name = "clientId", required = false) String email, HttpSession httpSession) {
        var currentClients = new Client(); // tao client rong mac dinh neu ko commande se loi
        var emptyCommande = new Commande(); // để binding form
        var commande = commandeService.readAllCommande(); //list commande

        if (email != null && !email.isEmpty()) {
            Client found = clientService.findByEmail(email);
            if (found != null) {
                currentClients = found;
                emptyCommande.setClient(found);
            }
        }

        if (currentClients.getEmail() == null){
           Client loggedInClient = (Client) httpSession.getAttribute("loggedIn");
              if (loggedInClient != null) {
                    currentClients = loggedInClient;
                    emptyCommande.setClient(loggedInClient);
              }
        }
        if (currentClients.getEmail() != null){
            emptyCommande.setClient(currentClients);
        }


        var modelData = Map.of("commande", commande, "newCommande", emptyCommande, "clientInfo", currentClients);
        return new ModelAndView("commande", modelData);
    }

    @PostMapping("/commande/save")
    public String saveCommande(@ModelAttribute Commande commande) {
        commandeService.createCommande(commande);
        return "redirect:/commande"; // sau khi lưu xong quay về trang home
    }
}
