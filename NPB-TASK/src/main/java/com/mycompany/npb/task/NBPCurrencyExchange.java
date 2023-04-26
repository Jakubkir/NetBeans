/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.npb.task;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class NBPCurrencyExchange {
    private static final String API_URL = "http://api.nbp.pl/api/exchangerates/rates/c/%s/last/%d/?format=json";
    
    public static void main(String[] args) {
        String currencyCode = "USD"; // kod waluty
        int numberOfRecords = 5; // liczba ostatnich notowań
        
        String urlStr = String.format(API_URL, currencyCode, numberOfRecords);
        try {
            URL url = new URL(urlStr);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder(); // zmiana na StringBuilder, który jest bardziej wydajny
            
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            
            in.close();
            connection.disconnect();
            
            String jsonResponse = response.toString();
            // przetwarzanie JSONa i obliczenie różnicy między kursem kupna i sprzedaży
            
        } catch (Exception e) {
            System.out.println("Błąd pobierania danych z API NBP: " + e.getMessage());
        }
    }
    
}
