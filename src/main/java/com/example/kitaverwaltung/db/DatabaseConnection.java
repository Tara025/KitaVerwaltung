package com.example.kitaverwaltung.db;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.example.kitaverwaltung.config.Config;

public class DatabaseConnection {

    private static final String SUPABASE_URL = Config.SUPABASE_URL;
    private static final String API_KEY = Config.API_KEY;  // Ersetze mit deinem echten API-Key
    private static DatabaseConnection instance;
    private final HttpClient client;
    private Connection connection;

    private DatabaseConnection() {
        this.client = HttpClient.newHttpClient();
    }

    // Singleton-Methode für die Instanz
    public static DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    // Method to execute SQL queries
    public ResultSet executeQuery(String query) throws SQLException {
        Statement stmt = connection.createStatement();
        return stmt.executeQuery(query);

    }
    // Allgemeine Methode für API-Anfragen (GET)
    public String sendGetRequest(String tableName) {
        try {
            String url = SUPABASE_URL + "/rest/v1/" + tableName;
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("apikey", API_KEY)
                    .header("Authorization", "Bearer " + API_KEY)
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                return response.body();
            } else {
                System.out.println("❌ Fehler beim Abrufen der Daten. Status: " + response.statusCode());
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Allgemeine Methode für API-Anfragen (POST)
    public boolean sendPostRequest(String tableName, String jsonData) {
        try {
            String url = SUPABASE_URL + "/rest/v1/" + tableName;
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("apikey", API_KEY)
                    .header("Authorization", "Bearer " + API_KEY)
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(jsonData))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            return response.statusCode() == 201;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Methode für PUT-Anfragen (Update eines Datensatzes)
    public boolean sendPutRequest(String tableName, String jsonData) {
        try {
            String url = SUPABASE_URL + "/rest/v1/" + tableName;
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("apikey", API_KEY)
                    .header("Authorization", "Bearer " + API_KEY)
                    .header("Content-Type", "application/json")
                    .PUT(HttpRequest.BodyPublishers.ofString(jsonData))  // PUT für Updates
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            return response.statusCode() == 200;  // Erfolgreich bei Status 200
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Methode für DELETE-Anfragen (Löschen eines Datensatzes)
    public boolean sendDeleteRequest(String tableName, int recordId) {
        try {
            // System.out.println("Deleting record with ID: " + recordId);
            String url = SUPABASE_URL + "/rest/v1/" + tableName + "?" + tableName.substring(2) + "_id=eq." + recordId; // Beispiel für die URL mit ID
            System.out.println("DELETE URL: " + url);  // Log the URL

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("apikey", API_KEY)
                    .header("Authorization", "Bearer " + API_KEY)
                    .header("Content-Type", "application/json")
                    .method("PATCH", HttpRequest.BodyPublishers.ofString("{\"deleted\": true}"))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("Response Code: " + response.statusCode());  // Log the response code
            System.out.println("Response Body: " + response.body());  // Log the response body

            return response.statusCode() == 204;  // Erfolgreich bei Status 204 (No Content)
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
