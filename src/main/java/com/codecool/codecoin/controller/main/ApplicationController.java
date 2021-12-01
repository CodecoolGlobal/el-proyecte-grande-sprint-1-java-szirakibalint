package com.codecool.codecoin.controller.main;

import com.codecool.codecoin.controller.APIController;
import com.codecool.codecoin.model.Cryptocurrency;
import com.codecool.codecoin.model.CurrencyType;
import com.codecool.codecoin.model.Portfolio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Map;

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

    /**
     * Display detailed coin page when visiting "/coins/{coinId}"
     * @param coinId an identifier string for a {@link Cryptocurrency} (e.g. "bitcoin")
     * @param model the object used to store data for the template
     * @return coin details page template if coin exists, else error page template
     */
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

    @GetMapping("/portfolio")
    public String portfolio(Model model) {
        Portfolio portfolio = apiController.getPortfolio();
        Map<CurrencyType, BigDecimal> currencies = portfolio.getCurrencies();
        Map<Cryptocurrency, BigDecimal> cryptocurrencies = portfolio.getCryptoCurrencies();
        BigDecimal totalBalance = currencies.get(CurrencyType.USD);
        for (Map.Entry<Cryptocurrency, BigDecimal> entry : cryptocurrencies.entrySet()) {
            BigDecimal value = entry.getKey().getCurrentPrice().multiply(entry.getValue());
            totalBalance = totalBalance.add(value);
        }
        model.addAttribute("cryptocurrencies", cryptocurrencies);
        model.addAttribute("currencies", currencies);
        model.addAttribute("totalBalance", totalBalance);
        return "portfolio";
    }
}
