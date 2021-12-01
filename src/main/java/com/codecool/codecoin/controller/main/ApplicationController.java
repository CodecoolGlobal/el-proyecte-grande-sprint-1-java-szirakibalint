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
