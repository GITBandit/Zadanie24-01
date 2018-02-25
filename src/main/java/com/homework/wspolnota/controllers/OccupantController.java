package com.homework.wspolnota.controllers;

import com.homework.wspolnota.model.Flat;
import com.homework.wspolnota.model.Occupant;
import com.homework.wspolnota.model.Sex;
import com.homework.wspolnota.model.repositories.FlatRepository;
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

    @GetMapping("/occupant/{id}")
    private String occupantInfo(@PathVariable("id") Long id, Model model){

        Occupant occupant = occupantRepository.getOne(id);

        model.addAttribute("occupant", occupant);

        Flat flat = occupant.getFlat();

        model.addAttribute("flat", flat);

        return "occupant";
    }

    @GetMapping("/occupant/edit/{id}")
    private String editOccupant(@PathVariable("id") Long id, Model model){

        Occupant occupant = occupantRepository.getOne(id);

        model.addAttribute("occupant",occupant);

        model.addAttribute("sexes", Sex.values());

        List<Flat> flatList = flatRepository.findAll();

        model.addAttribute("flats", flatList);


        return "occupant_edit_form";
    }

    @PostMapping("/occupant/edit/{id}")
    @ResponseBody
    private String updateOccupant(@PathVariable("id") Long id, Occupant occupant){

        occupantRepository.save(occupant);


        return "updated 2";
    }

    @GetMapping("/occupant/delete/{id}")
    @ResponseBody
    private String deleteOccupant(@PathVariable("id")Long id){

        occupantRepository.delete(id);

        return "deleted";
    }

    @GetMapping("/occupant/add")
    private String editOccupant(Model model){

        Occupant occupant = new Occupant();

        model.addAttribute("occupant",occupant);

        model.addAttribute("sexes", Sex.values());

        List<Flat> flatList = flatRepository.findAll();

        model.addAttribute("flats", flatList);


        return "occupant_add_form";
    }

    @PostMapping("/occupant/add")
    @ResponseBody
    private String updateOccupant(Occupant occupant){

        occupantRepository.save(occupant);


        return "saved";
    }

}
