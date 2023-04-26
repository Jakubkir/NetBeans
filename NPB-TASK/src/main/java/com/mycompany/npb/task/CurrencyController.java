/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.npb.task;

@RestController
public class CurrencyController {

    @Autowired
    private NbpService nbpService;

    @GetMapping("/exchange-rate")
    public ExchangeRate getExchangeRate(@RequestParam String date, @RequestParam String currencyCode) {
        return nbpService.getExchangeRate(date, currencyCode);
    }

    @GetMapping("/average-min-max-rates")
    public Map<String, Object> getAverageMinMaxRates(@RequestParam String currencyCode, @RequestParam int numDays) {
        return nbpService.getAverageMinMaxRates(currencyCode, numDays);
    }

    @GetMapping("/buy-sell-rate-diff")
    public Map<String, Object> getBuySellRateDiff(@RequestParam String currencyCode, @RequestParam int numDays) {
        return nbpService.getBuySellRateDiff(currencyCode, numDays);
    }

}

