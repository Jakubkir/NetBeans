/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.openx;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class FakeStoreApi {

    private static final String USER_API_URL = "https://fakestoreapi.com/users";
    private static final String CARTS_API_URL = "https://fakestoreapi.com/carts";
    private static final String PRODUCTS_API_URL = "https://fakestoreapi.com/products";

    public static void main(String[] args) {
        String userJson = fetchData(USER_API_URL);
        String cartJson = fetchData(CARTS_API_URL);
        String productJson = fetchData(PRODUCTS_API_URL);

        JSONArray users = new JSONArray(userJson);
        JSONArray carts = new JSONArray(cartJson);
        JSONArray products = new JSONArray(productJson);

        System.out.println("Number of Users: " + users.length());
        System.out.println("Number of Carts: " + carts.length());
        System.out.println("Number of Products: " + products.length());

        // Perform further processing as required
    }

    private static String fetchData(String apiUrl) {
        try {
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            int responseCode = conn.getResponseCode();
            if (responseCode != 200) {
                throw new RuntimeException("Failed to fetch data from API " + apiUrl + " Reason: " + conn.getResponseMessage());
            }
            Scanner scanner = new Scanner(url.openStream());
            StringBuilder response = new StringBuilder();
            while (scanner.hasNext()) {
                response.append(scanner.nextLine());
            }
            return response.toString();
        } catch (IOException ex) {
            throw new RuntimeException("Failed to fetch data from API " + apiUrl + " Reason: " + ex.getMessage());
        }
    }
	
	public static Map<String, Double> getProductCategoryValues() {
        String productsUrl = "https://fakestoreapi.com/products";
        JSONArray products = new JSONArray(fetchData(productsUrl));

        Map<String, Double> categoryValues = new HashMap<>();

        for (int i = 0; i < products.length(); i++) {
            JSONObject product = products.getJSONObject(i);
            String category = product.getString("category");
            double price = product.getDouble("price");

            categoryValues.put(category, categoryValues.getOrDefault(category, 0.0) + price);
        }

        return categoryValues;
    }
	
	public static Map<String, Object> getHighestValueCart() throws IOException {
        JSONArray users = HttpUtil.getJSONArrayFromUrl(USERS_URL);
        JSONArray carts = HttpUtil.getJSONArrayFromUrl(CARTS_URL);
        JSONArray products = HttpUtil.getJSONArrayFromUrl(PRODUCTS_URL);

        // Create a map of user IDs to user objects for easy lookup
        Map<Long, JSONObject> userMap = new HashMap<>();
        for (int i = 0; i < users.length(); i++) {
            JSONObject user = users.getJSONObject(i);
            userMap.put(user.getLong("id"), user);
        }

        // Calculate the total value of each cart
        Map<Long, Double> cartValues = new HashMap<>();
        for (int i = 0; i < carts.length(); i++) {
            JSONObject cart = carts.getJSONObject(i);
            double totalValue = 0.0;
            JSONArray items = cart.getJSONArray("products");
            for (int j = 0; j < items.length(); j++) {
                JSONObject item = items.getJSONObject(j);
                long productId = item.getLong("productId");
                double price = getProductPrice(products, productId);
                totalValue += price * item.getInt("quantity");
            }
            cartValues.put(cart.getLong("userId"), totalValue);
        }

        // Find the user with the highest value cart
        long highestValueUserId = -1;
        double highestValue = 0.0;
        for (Map.Entry<Long, Double> entry : cartValues.entrySet()) {
            long userId = entry.getKey();
            double cartValue = entry.getValue();
            if (cartValue > highestValue) {
                highestValue = cartValue;
                highestValueUserId = userId;
            }
        }

        // Get the user object for the highest value user ID and return the result
        JSONObject highestValueUser = userMap.get(highestValueUserId);
        String fullName = highestValueUser.getString("name") + " " + highestValueUser.getString("username");
        Map<String, Object> result = new HashMap<>();
        result.put("fullName", fullName);
        result.put("cartValue", highestValue);
        return result;
    }

    // Helper method to get the price of a product with a given ID
    private static double getProductPrice(JSONArray products, long productId) {
        for (int i = 0; i < products.length(); i++) {
            JSONObject product = products.getJSONObject(i);
            if (product.getLong("id") == productId) {
                return product.getDouble("price");
            }
        }
        return 0.0;
    }
	
	public static void findFurthestUsers() throws IOException {
        String userJson = fetchData(USER_API_URL);
        JSONArray users = new JSONArray(userJson);

        // Create a list of user objects with their ID and coordinates
        List<User> userList = new ArrayList<>();
        for (int i = 0; i < users.length(); i++) {
            JSONObject userJsonObj = users.getJSONObject(i);
            long id = userJsonObj.getLong("id");
            JSONObject addressJsonObj = userJsonObj.getJSONObject("address");
            double lat = addressJsonObj.getDouble("lat");
            double lng = addressJsonObj.getDouble("lng");
            userList.add(new User(id, lat, lng));
        }

        // Find the two users with the maximum distance between them
        double maxDistance = 0.0;
        User user1 = null;
        User user2 = null;
        for (int i = 0; i < userList.size(); i++) {
            for (int j = i + 1; j < userList.size(); j++) {
                User u1 = userList.get(i);
                User u2 = userList.get(j);
                double distance = calculateDistance(u1, u2);
                if (distance > maxDistance) {
                    maxDistance = distance;
                    user1 = u1;
                    user2 = u2;
                }
            }
        }

        // Print the result
        System.out.println("The two users living furthest away from each other are:");
        System.out.println(user1);
        System.out.println(user2);
    }

    private static double calculateDistance(User u1, User u2) {
        // Calculate the distance between two users using the Haversine formula
        double lat1 = Math.toRadians(u1.getLat());
        double lng1 = Math.toRadians(u1.getLng());
        double lat2 = Math.toRadians(u2.getLat());
        double lng2 = Math.toRadians(u2.getLng());

        double dLat = lat2 - lat1;
        double dLng = lng2 - lng1;
        double a = Math.pow(Math.sin(dLat / 2), 2)
                + Math.cos(lat1) * Math.cos(lat2)
                * Math.pow(Math.sin(dLng / 2), 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double earthRadius = 6371.0; // in kilometers
        return earthRadius * c;
    }
