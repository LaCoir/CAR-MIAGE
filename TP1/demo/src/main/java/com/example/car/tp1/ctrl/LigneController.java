package com.example.car.tp1.ctrl;

import com.example.car.tp1.service.CommandeService;
import com.example.car.tp1.model.Commande;
import com.example.car.tp1.service.LigneService;
import com.example.car.tp1.model.Ligne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import jakarta.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;


@Controller
public class LigneController {
    @Autowired
    private CommandeService commandeService;
    @Autowired
    private LigneService ligneService;

    @GetMapping("/commande/ligne")
    public ModelAndView ligne(@RequestParam("commandeId") Long commandeId, HttpSession session) {
        Commande father = commandeService.getCommandeById(commandeId);
        Ligne newLigne = new Ligne();
        newLigne.setCommande(father);

        List<Ligne> listLignes = (List<Ligne>) session.getAttribute("lignes_" + commandeId);
        if (listLignes == null) {
            listLignes = new ArrayList<>(father.getLignes() != null ? father.getLignes() : new ArrayList<>());
        }

        ModelAndView mav = new ModelAndView("lignes");
        mav.addObject("commandeFather", father);
        mav.addObject("newLigne", newLigne);
        mav.addObject("listLignes", listLignes);
        return mav;
    }
    @PostMapping("/commande/ligne/save")
    public String saveLigne(@ModelAttribute Ligne ligne, HttpSession session) {
        Long idFather = ligne.getCommande().getId();
        List<Ligne> lignes = (List<Ligne>) session.getAttribute("lignes_" + idFather);
        if (lignes == null) {
            lignes = new ArrayList<>();
        }
        lignes.add(ligne);
        session.setAttribute("lignes_" + idFather, lignes);
        return "redirect:/commande/ligne?commandeId=" + idFather;
    }

}

