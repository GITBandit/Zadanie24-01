package com.homework.wspolnota.controllers;

import com.homework.wspolnota.model.HousingAssociation;
import com.homework.wspolnota.model.repositories.HousingAssociationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    HousingAssociationRepository housingAssociationRepository;

    @GetMapping("/")
    private String mainMenu(Model model){

        List<HousingAssociation> housingAssociationList = housingAssociationRepository.findAll();

        model.addAttribute("housingAssociationList", housingAssociationList);

        return "index";
    }

}
