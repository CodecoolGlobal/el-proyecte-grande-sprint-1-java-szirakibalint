package com.codecool.codecoin.controller.main;

import com.codecool.codecoin.controller.APIController;
import com.codecool.codecoin.model.Cryptocurrency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Contains methods for handling page display for routes.
 */
@Controller
public class ApplicationController {

    private APIController apiController;

    /**
     * Creates an {@link ApplicationController} instance.
     * @param apiController used to access the {@link Cryptocurrency} data
     */
    @Autowired
    public ApplicationController(APIController apiController) {
        this.apiController = apiController;
    }

    /**
     * Display index page when visiting "/"
     * @return index page template
     */
    @GetMapping("/")
    public String index() {
        return "index";
    }

    /**
     * Display list of coins when visiting "/coins"
     * @param model the object used to store data for the template
     * @return coins page template
     */
    @GetMapping("/coins")
    public String coins(Model model) {
        model.addAttribute("allCurrencies", apiController.getAll());
        return "coins";
    }

    @GetMapping("/coins/{coinId}")
    public String coinDetails(@PathVariable String coinId, Model model) {
        Cryptocurrency cryptocurrency = apiController.getCurrencyById(coinId);
        if (cryptocurrency == null) {
            return "error";
        } else {
            model.addAttribute("cryptocurrency", cryptocurrency);
            return "coin-details";
        }
    }
}
