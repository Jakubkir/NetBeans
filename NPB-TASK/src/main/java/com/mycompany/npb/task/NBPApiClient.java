/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.npb.task;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class NBPApiClient {
    private final RestTemplate restTemplate;

    public NBPApiClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<ExchangeRate> getExchangeRatesForDateAndCurrency(String date, String currencyCode) {
        String url = String.format("http://api.nbp.pl/api/exchangerates/rates/a/%s/%s/", currencyCode, date);
        TableAExchangeRatesResponse response = restTemplate.getForObject(url, TableAExchangeRatesResponse.class);
        return response.getRates();
    }

    public List<ExchangeRate> getExchangeRatesForLastNDaysAndCurrency(String currencyCode, int days) {
        String url = String.format("http://api.nbp.pl/api/exchangerates/rates/a/%s/last/%d/", currencyCode, days);
        TableAExchangeRatesResponse response = restTemplate.getForObject(url, TableAExchangeRatesResponse.class);
        return response.getRates();
    }

    public List<CurrencyRate> getCurrencyRatesForLastNDaysAndCurrency(String currencyCode, int days) {
        String url = String.format("http://api.nbp.pl/api/exchangerates/rates/c/%s/last/%d/", currencyCode, days);
        TableCExchangeRatesResponse response = restTemplate.getForObject(url, TableCExchangeRatesResponse.class);
        return response.getRates();
    }

    private static class TableAExchangeRatesResponse {
        private String table;
        private String currency;
        private List<ExchangeRate> rates;

        public String getTable() {
            return table;
        }

        public String getCurrency() {
            return currency;
        }

        public List<ExchangeRate> getRates() {
            return rates;
        }
    }

    private static class TableCExchangeRatesResponse {
        private String table;
        private String currency;
        private List<CurrencyRate> rates;

        public String getTable() {
            return table;
        }

        public String getCurrency() {
            return currency;
        }

        public List<CurrencyRate> getRates() {
            return rates;
        }
    }

    public static class ExchangeRate {
        private String no;
        private String effectiveDate;
        private float mid;

        public String getNo() {
            return no;
        }

        public String getEffectiveDate() {
            return effectiveDate;
        }

        public float getMid() {
            return mid;
        }
    }

    public static class CurrencyRate {
        private String no;
        private String effectiveDate;
        @JsonProperty("bid")
        private float buyingRate;
        @JsonProperty("ask")
        private float sellingRate;

        public String getNo() {
            return no;
        }

        public String getEffectiveDate() {
            return effectiveDate;
        }

        public float getBuyingRate() {
            return buyingRate;
        }

        public float getSellingRate() {
            return sellingRate;
        }
    }
}
