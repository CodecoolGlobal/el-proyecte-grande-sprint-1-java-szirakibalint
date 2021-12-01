package com.codecool.codecoin.controller.main;

import com.codecool.codecoin.controller.APIController;
import com.codecool.codecoin.logic.Calculator;
import com.codecool.codecoin.model.Cryptocurrency;
import com.codecool.codecoin.model.CurrencyType;
import com.codecool.codecoin.model.Portfolio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;
import java.util.Map;

@Controller
public class ApplicationController {

    private final APIController apiController;
    private final Calculator calculator;

    @Autowired
    public ApplicationController(APIController apiController, Calculator calculator) {
        this.apiController = apiController;
        this.calculator = calculator;
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
        BigDecimal totalBalance = calculator.calculateTotalBalance(portfolio);
        model.addAttribute("cryptocurrencies", cryptocurrencies);
        model.addAttribute("currencies", currencies);
        model.addAttribute("totalBalance", totalBalance);
        return "portfolio";
    }

    @GetMapping("/coins/{coinId}/buy")
    public String buyCoin(@PathVariable String coinId, Model model) {
        Portfolio portfolio = apiController.getPortfolio();
        Cryptocurrency cryptocurrency = apiController.getCurrencyById(coinId);
        if (cryptocurrency == null) {
            return "error";
        } else {
            model.addAttribute("balance", portfolio.getCurrencies().get(CurrencyType.USD));
            model.addAttribute("cryptocurrency", cryptocurrency);
            return "buy";
        }
    }

    @GetMapping("/coins/{coinId}/sell")
    public String sellCoin(@PathVariable String coinId, Model model) {
        Portfolio portfolio = apiController.getPortfolio();
        Map<CurrencyType, BigDecimal> currencies = portfolio.getCurrencies();
        Map<Cryptocurrency, BigDecimal> cryptocurrencies = portfolio.getCryptoCurrencies();
        Cryptocurrency cryptocurrency = apiController.getCurrencyById(coinId);
        if (cryptocurrency == null) {
            return "error";
        } else {
            model.addAttribute("balance", currencies.get(CurrencyType.USD));
            model.addAttribute("cryptocurrency", cryptocurrency);
            model.addAttribute("amount", cryptocurrencies.getOrDefault(cryptocurrency, BigDecimal.ZERO));
            return "sell";
        }
    }
}
