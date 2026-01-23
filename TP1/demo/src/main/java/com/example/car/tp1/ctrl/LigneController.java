package com.example.car.tp1.ctrl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class LigneController {

    private LigneService ligneService;

    public LigneController(LigneService ligneService) {
        this.ligneService = ligneService;
    }

    @PostMapping("/ligne/create")
    public RedirectView createLigne(@RequestParam Long commandeId, @RequestParam String libelle, @RequestParam int quantite, @RequestParam double prix) {

        ligneService.ajouterLigne(commandeId, libelle, quantite, prix);

        return new RedirectView("/commande/detail?commandeId=" + commandeId);
    }

    @GetMapping("/ligne/delete")
    public RedirectView deleteLigne(@RequestParam Long ligneId, @RequestParam Long commandeId) {
        ligneService.supprimerLigne(ligneId);
        return new RedirectView("/commande/detail?commandeId=" + commandeId);
    }
}

}
