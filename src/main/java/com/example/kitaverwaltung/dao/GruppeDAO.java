package com.example.kitaverwaltung.dao;

import com.example.kitaverwaltung.config.Config;
import com.example.kitaverwaltung.model.Gruppe;
import com.google.gson.Gson;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class GruppeDAO {

    private static final String SUPABASE_URL = "https://foedwwepqjbyhopvxmod.supabase.co";
    private static final String API_KEY = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImZvZWR3d2VwcWjieWhvcHZ4bW9kIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDA5ODc2MTAsImV4cCI6MjA1NjU2MzYxMH0.uugXr1Erwk6j7UukqDR96H83yCWyKjNppsF1UtI-j8w";
    private static final String TABLE_NAME = "t_gruppe";

    // Hole alle Gruppen
    public static List<Gruppe> getGruppen() {
        List<Gruppe> gruppenListe = new ArrayList<>();

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
                Gruppe[] gruppeArray = gson.fromJson(responseBody, Gruppe[].class);

                // Gruppe-Objekte zur Liste hinzufügen
                for (Gruppe gruppe : gruppeArray) {
                    gruppenListe.add(gruppe);
                }
            } else {
                System.out.println("Fehler beim Abrufen der Daten. Status: " + response.statusCode());
            }
        } catch (Exception e) {
            System.out.println("Fehler bei der API-Anfrage: " + e.getMessage());
            e.printStackTrace();
        }

        return gruppenListe;
    }

    // Füge eine Gruppe hinzu
    public static void addGruppe(Gruppe gruppe) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            String url = SUPABASE_URL + "/rest/v1/" + TABLE_NAME;

            // JSON-Verarbeitung mit Gson
            Gson gson = new Gson();
            String json = gson.toJson(gruppe);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("apikey", Config.API_KEY)
                    .header("Authorization", "Bearer " + Config.API_KEY)
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 201) {
                System.out.println("Gruppe erfolgreich hinzugefügt.");
            } else {
                System.out.println("Fehler beim Hinzufügen der Gruppe. Status: " + response.statusCode());
            }
        } catch (Exception e) {
            System.out.println("Fehler bei der API-Anfrage: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Bearbeite eine Gruppe
    public static void editGruppe(Gruppe gruppe, String gruppeId) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            String url = SUPABASE_URL + "/rest/v1/" + TABLE_NAME + "?eq=gruppe_id." + gruppeId;

            // JSON-Verarbeitung mit Gson
            Gson gson = new Gson();
            String json = gson.toJson(gruppe);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("apikey", Config.API_KEY)
                    .header("Authorization", "Bearer " + Config.API_KEY)
                    .header("Content-Type", "application/json")
                    .method("PATCH", HttpRequest.BodyPublishers.ofString(json))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                System.out.println("Gruppe erfolgreich bearbeitet.");
            } else {
                System.out.println("Fehler beim Bearbeiten der Gruppe. Status: " + response.statusCode());
            }
        } catch (Exception e) {
            System.out.println("Fehler bei der API-Anfrage: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Lösche eine Gruppe
    public static void deleteGruppe(String gruppeId) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            String url = SUPABASE_URL + "/rest/v1/" + TABLE_NAME + "?eq=gruppe_id." + gruppeId;

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("apikey", Config.API_KEY)
                    .header("Authorization", "Bearer " + Config.API_KEY)
                    .method("DELETE", HttpRequest.BodyPublishers.noBody())
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                System.out.println("Gruppe erfolgreich gelöscht.");
            } else {
                System.out.println("Fehler beim Löschen der Gruppe. Status: " + response.statusCode());
            }
        } catch (Exception e) {
            System.out.println("Fehler bei der API-Anfrage: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
