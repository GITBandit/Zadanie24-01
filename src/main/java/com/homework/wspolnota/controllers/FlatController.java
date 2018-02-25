package com.homework.wspolnota.controllers;

import com.homework.wspolnota.model.Flat;
import com.homework.wspolnota.model.HousingAssociation;
import com.homework.wspolnota.model.Occupant;
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

import java.util.ArrayList;
import java.util.List;

@Controller
public class FlatController {

    @Autowired
    FlatRepository flatRepository;
    @Autowired
    OccupantRepository occupantRepository;
    @Autowired
    HousingAssociationRepository housingAssociationRepository;

    @GetMapping("/flat/{id}")
    private String flatInfo(@PathVariable("id") Long id, Model model){

        Flat flat = flatRepository.getOne(id);

        model.addAttribute("flat", flat);

        List<Occupant> occupantList = new ArrayList<>();

        occupantList = flat.getOccupantList();

        model.addAttribute("occupantList", occupantList);

        return "flat";
    }

    @GetMapping("/flat/edit/{id}")
    private String editFlat(@PathVariable("id")Long id, Model model){

        Flat flat = flatRepository.findOne(id);

        model.addAttribute("flat", flat);

        List<Occupant> occupantList = occupantRepository.findAll();

        model.addAttribute("occupants", occupantList);

        List<HousingAssociation> housingAssociationList = housingAssociationRepository.findAll();

        model.addAttribute("associations", housingAssociationList);

        return "flat_edit_form";
    }

    @PostMapping("/flat/edit/{id}")
    @ResponseBody
    private String updateFlat(@PathVariable("id")Long id, Flat flat){

        flatRepository.save(flat);

        return "edited";
    }

    @GetMapping("/flat/delete/{id}")
    @ResponseBody
    private String deleteFlat(@PathVariable("id")Long id){

        flatRepository.delete(id);

        return "deleted";
    }

    @GetMapping("/flat/add")
    private String addFlat(Model model){

        Flat flat = new Flat();

        model.addAttribute("flat", flat);

        List<Occupant> occupantList = occupantRepository.findAll();

        List<HousingAssociation> associations = housingAssociationRepository.findAll();

        model.addAttribute("associations", associations);

        return "flat_add_form";
    }

    @PostMapping("/flat/add")
    @ResponseBody
    private String updateFlat(Flat flat){

        flatRepository.save(flat);

        return "added";
    }

}
