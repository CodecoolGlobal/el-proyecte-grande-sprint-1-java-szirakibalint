package com.codecool.codecoin.controller.main;

import com.codecool.codecoin.controller.APIController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ApplicationController {

    private APIController apiController;

    @Autowired
    public ApplicationController(APIController apiController) {
        this.apiController = apiController;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/coins")
    public String coins(Model model) {
        model.addAttribute("allCurrencies", apiController.getAll());
        return "coins";
    }
}
