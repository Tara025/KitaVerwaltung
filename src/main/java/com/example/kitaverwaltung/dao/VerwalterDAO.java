package com.example.kitaverwaltung.dao;


import com.example.kitaverwaltung.config.Config;
import com.example.kitaverwaltung.model.Verwalter;
import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class VerwalterDAO {

    private static final String SUPABASE_URL = "https://foedwwepqjbyhopvxmod.supabase.co";
    private static final String API_KEY = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImZvZWR3d2VwcWjieWhvcHZ4bW9kIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDA5ODc2MTAsImV4cCI6MjA1NjU2MzYxMH0.uugXr1Erwk6j7UukqDR96H83yCWyKjNppsF1UtI-j8w";
    private static final String TABLE_NAME = "t_verwalter";  // Deine Tabelle

    public static List<Verwalter> getVerwalter() {
        List<Verwalter> verwalterListe = new ArrayList<>();

        try {
            HttpClient client = HttpClient.newHttpClient();
            String url = SUPABASE_URL + "/rest/v1/" + TABLE_NAME;

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("apikey", Config.API_KEY)
                    .header("Authorization", "Bearer " + Config.API_KEY)
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                String responseBody = response.body();
                System.out.println("Daten empfangen: " + responseBody);

                // JSON-Verarbeitung mit Gson
                Gson gson = new Gson();
                Verwalter[] verwalterArray = gson.fromJson(responseBody, Verwalter[].class);

                // Verwalter-Objekte zur Liste hinzufügen
                for (Verwalter verwalter : verwalterArray) {
                    verwalterListe.add(verwalter);
                }
            } else {
                System.out.println("Fehler beim Abrufen der Daten. Status: " + response.statusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return verwalterListe;
    }
}
