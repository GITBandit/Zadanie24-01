package com.homework.wspolnota.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class OutputController {

    @GetMapping("/output")
    public String added(Model model,
                        @RequestParam(value = "entity") String entity,
                        @RequestParam (value = "operation") String operation,
                        @RequestParam (value = "id", required = false) Long id){

        String outputEntity;
        String outputOperation;
        String urlEntity;
        int outputID;

        if(entity.equals("association")){
            outputEntity = "Wspólnota mieszkaniowa";
            urlEntity = "association";
        } else if (entity.equals("flat")){
            outputEntity = "Mieszkanie";
            urlEntity = "association";
        } else {
            outputEntity = "Mieszkaniec";
            urlEntity = "association";
        }

        if(operation.equals("delete")){
            outputOperation = " - usunieto";
        } else if (operation.equals("update")){
            outputOperation = " - zaktualizowano";
        } else {
            outputOperation = " - dodano";
        }



        model.addAttribute("entity", outputEntity);
        model.addAttribute("operation", outputOperation);
        model.addAttribute("id", id);
        model.addAttribute("urlEntity", urlEntity);


        return "output/change_confirmation";
    }

}
