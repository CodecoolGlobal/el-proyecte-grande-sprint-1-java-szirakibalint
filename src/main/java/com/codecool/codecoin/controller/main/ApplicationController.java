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

/**
 * Contains methods for handling page display for routes.
 */
@Controller
public class ApplicationController {

    private final APIController apiController;
    private final Calculator calculator;
    private static final String INVALID_ID_ERROR_MESSAGE = "No cryptocurrency with the given id exists. If you see this message after clicking on one of the cryptocurrencies on the main page, please let us know!";

    /**
     * Creates an {@link ApplicationController} instance.
     * @param apiController used to access the {@link Cryptocurrency} data
     */
    @Autowired
    public ApplicationController(APIController apiController, Calculator calculator) {
        this.apiController = apiController;
        this.calculator = calculator;
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
            model.addAttribute("message", INVALID_ID_ERROR_MESSAGE);
            return "error";
        } else {
            model.addAttribute("cryptocurrency", cryptocurrency);
            return "coin-details";
        }
    }

    /**
     * Display portfolio page with purchased cryptocurrencies.
     * @param model the object used to store data for the template
     * @return portfolio page
     */
    @GetMapping("/portfolio")
    public String portfolio(Model model) {
        Portfolio portfolio = apiController.getPortfolio();
        Map<CurrencyType, BigDecimal> currencies = portfolio.getCurrencies();
        Map<Cryptocurrency, BigDecimal> cryptocurrencies = portfolio.getCryptoCurrencies();
        BigDecimal totalBalance = calculator.calculateTotalBalance(portfolio, apiController);
        model.addAttribute("cryptocurrencies", cryptocurrencies);
        model.addAttribute("currencies", currencies);
        model.addAttribute("totalBalance", totalBalance);
        return "portfolio";
    }

    /**
     * Display coin page with buy option.
     * @param coinId an identifier string for a {@link Cryptocurrency} (e.g. "bitcoin")
     * @param model the object used to store data for the template
     * @return coin buy page template if coin exists, else error page template
     */
    @GetMapping("/coins/{coinId}/buy")
    public String buyCoin(@PathVariable String coinId, Model model) {
        Portfolio portfolio = apiController.getPortfolio();
        Cryptocurrency cryptocurrency = apiController.getCurrencyById(coinId);
        Map<Cryptocurrency, BigDecimal> cryptocurrencies = portfolio.getCryptoCurrencies();
        if (cryptocurrency == null) {
            model.addAttribute("message", INVALID_ID_ERROR_MESSAGE);
            return "error";
        } else {
            model.addAttribute("balance", portfolio.getCurrencies().get(CurrencyType.USD));
            model.addAttribute("cryptocurrency", cryptocurrency);
            model.addAttribute("amount", cryptocurrencies.getOrDefault(cryptocurrency, BigDecimal.ZERO));
            return "buy";
        }
    }

    /**
     * Display coin page with sell option.
     * @param coinId an identifier string for a {@link Cryptocurrency} (e.g. "bitcoin")
     * @param model the object used to store data for the template
     * @return coin sell page template if coin exists, else error page template
     */
    @GetMapping("/coins/{coinId}/sell")
    public String sellCoin(@PathVariable String coinId, Model model) {
        Portfolio portfolio = apiController.getPortfolio();
        Map<CurrencyType, BigDecimal> currencies = portfolio.getCurrencies();
        Map<Cryptocurrency, BigDecimal> cryptocurrencies = portfolio.getCryptoCurrencies();
        Cryptocurrency cryptocurrency = apiController.getCurrencyById(coinId);
        if (cryptocurrency == null) {
            model.addAttribute("message", INVALID_ID_ERROR_MESSAGE);
            return "error";
        } else {
            model.addAttribute("balance", currencies.get(CurrencyType.USD));
            model.addAttribute("cryptocurrency", cryptocurrency);
            model.addAttribute("amount", cryptocurrencies.getOrDefault(cryptocurrency, BigDecimal.ZERO));
            return "sell";
        }
    }
}
