package com.example.car.tp1.ctrl;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

public class CommandeController {
    private CommandeService commandeService;

    private LigneService ligneService;

    public CommandeController(CommandeService commandeService, LigneService ligneService) {
        this.commandeService = commandeService;
        this.ligneService = ligneService;
    }

    @GetMapping("/commande")
    public ModelAndView commande(HttpSession session) {
        Client client = (Client) session.getAttribute("clientConnecte");

        if (client == null) {
            return new ModelAndView("redirect:/store/home");
        }
        var commandes = commandeService.getCommandesByClient(client);
        return new ModelAndView("commande", Map.of("client", client, "commandes", commandes));
    }

    @PostMapping("/commande/create")
    public RedirectView createCommande(@RequestParam String titre, HttpSession session) {
        Client client = (Client) session.getAttribute("clientConnecte");
        if (client != null) {
            commandeService.createCommande(client,titre);
        }
        return new RedirectView("/store/commande");
    }

    @GetMapping("/commande/detail")
    public ModelAndView detailCommande(@RequestParam Long commandeId, HttpSession session) {
        Client client = (Client) session.getAttribute("clientConnecte");
        if (client == null) return new ModelAndView("redirect:/store/home");

        Commande commande = commandeService.getCommandeById(commandeId);
        List<Ligne> lignes = ligneService.getLignesByCommande(commande);

        return new ModelAndView("detail", Map.of("commande", commande, "lignes", lignes));
    }

    @GetMapping("/commande/print")
    public ModelAndView printCommande(@RequestParam Long commandeId, HttpSession session) {
        Client client = (Client) session.getAttribute("clientConnecte");
        if (client == null) return new ModelAndView("redirect:/store/home");

        Commande commande = commandeService.getCommandeById(commandeId);
        List<Ligne> lignes = ligneService.getLignesByCommande(commande); // <-- IMPORTANT si tu es en manuel

        var model = Map.of("client", client, "commande", commande, "lignes", lignes);

        return new ModelAndView("print", model);
    }
}
