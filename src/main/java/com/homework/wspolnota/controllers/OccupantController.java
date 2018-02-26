package com.homework.wspolnota.controllers;

import com.homework.wspolnota.model.Flat;
import com.homework.wspolnota.model.Occupant;
import com.homework.wspolnota.model.Sex;
import com.homework.wspolnota.model.repositories.FlatRepository;
import com.homework.wspolnota.model.repositories.HousingAssociationRepository;
import com.homework.wspolnota.model.repositories.OccupantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class OccupantController {

    @Autowired
    OccupantRepository occupantRepository;
    @Autowired
    FlatRepository flatRepository;
    @Autowired
    HousingAssociationRepository housingAssociationRepository;

    @GetMapping("/occupant/{id}")
    private String occupantInfo(@PathVariable("id") Long id, Model model){

        Occupant occupant = occupantRepository.getOne(id);

        model.addAttribute("occupant", occupant);

        Flat flat = occupant.getFlat();

        model.addAttribute("flat", flat);

        return "occupant/occupant";
    }

    @GetMapping("/occupant/edit/{id}")
    private String editOccupant(@PathVariable("id") Long id, Model model){

        Occupant occupant = occupantRepository.getOne(id);

        model.addAttribute("occupant",occupant);

        model.addAttribute("sexes", Sex.values());

        List<Flat> flatList = flatRepository.findAll();

        model.addAttribute("flats", flatList);


        return "occupant/occupant_edit_form";
    }

    @PostMapping("/occupant/edit/{id}")
    private String updateOccupant(@PathVariable("id") Long id, Occupant occupant){

        occupantRepository.save(occupant);

        if (occupant.getFlat().getHousingAssociation() != null) {

            Long associationID = occupant.getFlat().getHousingAssociation().getId();

            return "redirect:/output?entity=occupant&operation=update&id=" + associationID;

        }

        return "redirect:/output?entity=occupant&operation=update";
    }

    @GetMapping("/occupant/delete/{id}")
    private String deleteOccupant(@PathVariable("id")Long id){

        String url;

        Long associationID = housingAssociationRepository.findOne(occupantRepository.getOne(id).getFlat().getHousingAssociation().getId()).getId();

        if (associationID != 0){
            url = "redirect:/output?entity=occupant&operation=delete&id=" + associationID;
        } else {
            url = "redirect:/output?entity=occupant&operation=delete";
        }

        occupantRepository.delete(id);

        return url;
    }

    @GetMapping("/occupant/add")
    private String editOccupant(Model model){

        Occupant occupant = new Occupant();

        model.addAttribute("occupant",occupant);

        model.addAttribute("sexes", Sex.values());

        List<Flat> flatList = flatRepository.findAll();

        model.addAttribute("flats", flatList);


        return "occupant/occupant_add_form";
    }

    @PostMapping("/occupant/add")
    private String addOccupant(Occupant occupant){

        occupantRepository.save(occupant);

        if (occupant.getFlat().getHousingAssociation() != null) {

            Long associationID = occupant.getFlat().getHousingAssociation().getId();

            return "redirect:/output?entity=occupant&operation=add&id=" + associationID;

        }

        return "redirect:/output?entity=occupant&operation=add";
    }

}
