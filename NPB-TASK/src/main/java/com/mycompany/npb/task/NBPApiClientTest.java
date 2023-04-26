/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.npb.task;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class NBPApiClientTest {

    @Mock
    private RestTemplate restTemplate;

    private NBPApiClient nbpApiClient;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        nbpApiClient = new NBPApiClient(restTemplate);
    }

    @Test
    public void getExchangeRatesForDateAndCurrency_returnsExchangeRates() {
        // given
        String date = "2022-04-25";
        String currencyCode = "USD";
        String url = String.format("http://api.nbp.pl/api/exchangerates/rates/a/%s/%s/", currencyCode, date);

        ExchangeRate exchangeRate = new ExchangeRate();
        exchangeRate.setNo("071/A/NBP/2022");
        exchangeRate.setEffectiveDate("2022-04-25");
        exchangeRate.setMid(3.7897f);

        TableAExchangeRatesResponse response = new TableAExchangeRatesResponse();
        response.setTable("A");
        response.setCurrency("dolar amerykański");
        response.setRates(Arrays.asList(exchangeRate));

        when(restTemplate.getForObject(url, TableAExchangeRatesResponse.class)).thenReturn(response);

        // when
        List<ExchangeRate> exchangeRates = nbpApiClient.getExchangeRatesForDateAndCurrency(date, currencyCode);

        // then
        assertEquals(1, exchangeRates.size());
        assertEquals(exchangeRate.getNo(), exchangeRates.get(0).getNo());
        assertEquals(exchangeRate.getEffectiveDate(), exchangeRates.get(0).getEffectiveDate());
        assertEquals(exchangeRate.getMid(), exchangeRates.get(0).getMid());
    }

    @Test
    public void getExchangeRatesForLastNDaysAndCurrency_returnsExchangeRates() {
        // given
        String currencyCode = "USD";
        int days = 5;
        String url = String.format("http://api.nbp.pl/api/exchangerates/rates/a/%s/last/%d/", currencyCode, days);

        ExchangeRate exchangeRate1 = new ExchangeRate();
        exchangeRate1.setNo("070/A/NBP/2022");
        exchangeRate1.setEffectiveDate("2022-04-20");
        exchangeRate1.setMid(3.8248f);

        ExchangeRate exchangeRate2 = new ExchangeRate();
        exchangeRate2.setNo("071/A/NBP/2022");
        exchangeRate2.setEffectiveDate("2022-04-21");
        exchangeRate2.setMid(3.8179f);

        TableAExchangeRatesResponse response = new TableAExchangeRatesResponse();
        response.setTable("A");
        response.setCurrency("dolar amerykański");
        response.setRates(Arrays.asList(exchangeRate1, exchangeRate2));

        when(restTemplate.getForObject(url, TableAExchangeRatesResponse.class)).thenReturn(response);

        // when
        List<ExchangeRate> exchangeRates = nbpApiClient.getExchangeRatesForLastNDaysAndCurrency(currencyCode, days);

        // then
        assertEquals(expectedRates, actualRates);

