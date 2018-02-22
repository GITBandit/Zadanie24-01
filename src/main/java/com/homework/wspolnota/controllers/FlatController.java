package com.homework.wspolnota.controllers;

import com.homework.wspolnota.model.Flat;
import com.homework.wspolnota.model.Occupant;
import com.homework.wspolnota.model.repositories.FlatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Controller
public class FlatController {

    @Autowired
    FlatRepository flatRepository;

    @GetMapping("/flat/{id}")
    private String flatInfo(@PathVariable("id") Long id, Model model){

        Flat flat = flatRepository.getOne(id);

        model.addAttribute("flat", flat);

        List<Occupant> occupantList = new ArrayList<>();

        occupantList = flat.getOccupantList();

        model.addAttribute("occupantList", occupantList);

        return "flat";
    }
}
