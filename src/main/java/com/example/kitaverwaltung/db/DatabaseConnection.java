package com.example.kitaverwaltung.db;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;
import com.google.gson.Gson;

public class DatabaseConnection {

    private static final String SUPABASE_URL = "https://foedwwepqjbyhopvxmod.supabase.co"; // Deine Supabase-URL
    private static final String API_KEY = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImZvZWR3d2VwcWpieWhvcHZ4bW9kIiwicm9sZSI6InNlcnZpY2Vfcm9sZSIsImlhdCI6MTc0MDk4NzYxMCwiZXhwIjoyMDU2NTYzNjEwfQ.W5SdMlCr7NwkHsoGSFr_IQqRDtL1pJwkwIO1rbL6oLQ";  // Dein API-Schlüssel
    private static final String TABLE_NAME = "t_verwalter";  // Deine Tabelle

    // Methode zum Abrufen von Daten (GET)
    public static void connectAndFetchData() {
        try {
            // HTTP Client für die API-Abfrage erstellen
            HttpClient client = HttpClient.newHttpClient();

            // Die URL für den Supabase API-Endpunkt
            String url = SUPABASE_URL + "/rest/v1/" + TABLE_NAME;

            // GET-Anfrage zum Abrufen von Daten
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("apikey", API_KEY)
                    .header("Authorization", "Bearer " + API_KEY) // Authentifizierung
                    .build();

            // Anfrage senden und Antwort erhalten
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Antwort verarbeiten
            if (response.statusCode() == 200) {
                String responseBody = response.body();
                //System.out.println("Daten empfangen: ");
                //System.out.println();
               // System.out.println(responseBody);

                // Beispiel: JSON-Verarbeitung mit Gson
                Gson gson = new Gson();
                Map[] data = gson.fromJson(responseBody, Map[].class);

                // Weiterverarbeitung der Daten
                for (Map record : data) {
                    System.out.println(record);
                }
            } else {
                System.out.println("Fehler beim Abrufen der Daten. Status: " + response.statusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Methode zum Erstellen von Daten (POST)
    public static void createData() {
        try {
            // Die URL für den Supabase API-Endpunkt
            String url = SUPABASE_URL + "/rest/v1/" + TABLE_NAME;

            // Die JSON-Daten für das Erstellen eines neuen Datensatzes
            String jsonBody = "{\"name\": \"Max Mustermann\", \"email\": \"max@example.com\"}";

            // POST-Anfrage zum Erstellen von Daten
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("apikey", API_KEY)
                    .header("Authorization", "Bearer " + API_KEY)
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                    .build();

            // Anfrage senden und Antwort erhalten
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

            // Antwort verarbeiten
            if (response.statusCode() == 201) {
                System.out.println("Daten erfolgreich erstellt: " + response.body());
            } else {
                System.out.println("Fehler beim Erstellen der Daten. Status: " + response.statusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Daten abrufen
        connectAndFetchData();

        // Daten erstellen (Beispiel: Max Mustermann)
        createData();
    }
}
