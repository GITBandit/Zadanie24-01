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
public class HousingAssociationController {

    @Autowired
    HousingAssociationRepository housingAssociationRepository;
    @Autowired
    OccupantRepository occupantRepository;
    @Autowired
    FlatRepository flatRepository;

    @GetMapping("/association/{id}")
    private String associations(@PathVariable("id") long id, Model model){

        HousingAssociation housingAssociation = housingAssociationRepository.findOne(id);

        model.addAttribute("association", housingAssociation);

        List<Flat> flatList = housingAssociation.getFlatList();

        model.addAttribute("flatList", flatList);

        double flatAreasSum = 0;
        List<Occupant> occupantsInAssociation = new ArrayList<>();

        for (Flat flat : flatList) {
            flatAreasSum += flat.getFlatArea();
            occupantsInAssociation.addAll(flat.getOccupantList());

        }

        model.addAttribute("flatAreas", flatAreasSum);

        model.addAttribute("occupantsList", occupantsInAssociation);

        return "association";
    }

    @GetMapping("/association/edit/{id}")
    private String editAssociation(@PathVariable("id") Long id, Model model){

        HousingAssociation association = housingAssociationRepository.getOne(id);

        model.addAttribute("association", association);

        return "association_form";
    }

    @PostMapping("/association/edit/{id}")
    @ResponseBody
    private String updateAssociation(@PathVariable("id") Long id, HousingAssociation housingAssociation){

        housingAssociationRepository.save(housingAssociation);

        return "updated";
    }


    @GetMapping("/association/delete/{id}")
    @ResponseBody
    private String deleteAssociation(@PathVariable("id")Long id){

        housingAssociationRepository.delete(id);

        return "deleted";
    }
}
