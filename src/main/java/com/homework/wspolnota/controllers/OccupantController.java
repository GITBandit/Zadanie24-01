package com.homework.wspolnota.controllers;

import com.homework.wspolnota.model.Flat;
import com.homework.wspolnota.model.Occupant;
import com.homework.wspolnota.model.repositories.OccupantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class OccupantController {

    @Autowired
    OccupantRepository occupantRepository;

    @GetMapping("/occupant/{id}")
    private String occupantInfo(@PathVariable("id") Long id, Model model){

        Occupant occupant = occupantRepository.getOne(id);

        model.addAttribute("occupant", occupant);

        Flat flat = occupant.getFlat();

        model.addAttribute("flat", flat);

        return "occupant";
    }
}
