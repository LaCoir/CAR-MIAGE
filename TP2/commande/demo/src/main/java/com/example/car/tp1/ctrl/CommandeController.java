package com.example.car.tp1.ctrl;

import com.example.car.tp1.model.Client;
import com.example.car.tp1.model.Commande;
import com.example.car.tp1.model.Ligne;
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

import java.util.ArrayList;
import java.util.List;
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
        Iterable<Commande> commande;
        if (email != null && !email.isEmpty()) {
            Client found = clientService.findByEmail(email);
            if (found != null) {
                currentClients = found;
                emptyCommande.setClient(found);
                commande = commandeService.getCommandesByClient(found);
            } else {
                commande = java.util.Collections.emptyList();
            }
        } else {
            Client loggedInClient = (Client) httpSession.getAttribute("loggedIn");
            if (loggedInClient != null) {
                currentClients = loggedInClient;
                emptyCommande.setClient(loggedInClient);
                commande = commandeService.getCommandesByClient(loggedInClient);
            } else {
                commande = java.util.Collections.emptyList();
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

    @GetMapping("/commande/print")
    public ModelAndView printCommande(@RequestParam("id") Long id, HttpSession session) {
        Commande commande = commandeService.getCommandeById(id);
        if (commande == null) {
            return new ModelAndView("redirect:/commande");
        }
        List<Ligne> lignes = (List<Ligne>) session.getAttribute("lignes_" + id);
        if (lignes == null) {
            lignes = commande.getLignes() != null ? new ArrayList<>(commande.getLignes()) : new ArrayList<>();
        }
        var modelData = Map.of("commande", commande, "lignes", lignes);
        return new ModelAndView("print", modelData);
    }

    @GetMapping("/commande/delete")
    public String deleteCommande(@RequestParam("id") Long id, HttpSession session) {
        Client loggedIn = (Client) session.getAttribute("loggedIn");
        Commande commande = commandeService.getCommandeById(id);
        if (commande != null && loggedIn != null && commande.getClient() != null && commande.getClient().getEmail().equals(loggedIn.getEmail())) {
            commandeService.deleteCommande(id);
        }
        return "redirect:/commande";
    }
}
