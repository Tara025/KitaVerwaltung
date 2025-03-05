package com.example.kitaverwaltung.dao;

import com.example.kitaverwaltung.config.Config;
import com.example.kitaverwaltung.model.Erzieher;
import com.google.gson.Gson;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class ErzieherDAO {

    private static final String SUPABASE_URL = "https://foedwwepqjbyhopvxmod.supabase.co";
    private static final String API_KEY = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImZvZWR3d2VwcWpieWhvcHZ4bW9kIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDA5ODc2MTAsImV4cCI6MjA1NjU2MzYxMH0.uugXr1Erwk6j7UukqDR96H83yCWyKjNppsF1UtI-j8w";
    private static final String TABLE_NAME = "t_erzieher";

    // Hole alle Erzieher
    public static List<Erzieher> getErzieher() {
        List<Erzieher> erzieherListe = new ArrayList<>();

        try {
            HttpClient client = HttpClient.newHttpClient();
            String url = SUPABASE_URL + "/rest/v1/" + TABLE_NAME;

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("apikey", API_KEY)
                    .header("Authorization", "Bearer " + API_KEY)
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                Gson gson = new Gson();
                Erzieher[] erzieherArray = gson.fromJson(response.body(), Erzieher[].class);

                for (Erzieher erzieher : erzieherArray) {
                    erzieherListe.add(erzieher);
                }
            } else {
                System.out.println("Fehler beim Abrufen der Daten. Status: " + response.statusCode());
            }
        } catch (Exception e) {
            System.out.println("Fehler bei der API-Anfrage: " + e.getMessage());
            e.printStackTrace();
        }

        return erzieherListe;
    }

    // Füge einen Erzieher hinzu
    public static void addErzieher(Erzieher erzieher) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            String url = SUPABASE_URL + "/rest/v1/" + TABLE_NAME;

            Gson gson = new Gson();
            String json = gson.toJson(erzieher);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("apikey", API_KEY)
                    .header("Authorization", "Bearer " + API_KEY)
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 201) {
                System.out.println("Erzieher erfolgreich hinzugefügt.");
            } else {
                System.out.println("Fehler beim Hinzufügen des Erziehers. Status: " + response.statusCode());
            }
        } catch (Exception e) {
            System.out.println("Fehler bei der API-Anfrage: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Bearbeite einen Erzieher
    public static void editErzieher(Erzieher erzieher, String erzieherId) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            String url = SUPABASE_URL + "/rest/v1/" + TABLE_NAME + "?eq=erzieher_id." + erzieherId;

            Gson gson = new Gson();
            String json = gson.toJson(erzieher);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("apikey", API_KEY)
                    .header("Authorization", "Bearer " + API_KEY)
                    .header("Content-Type", "application/json")
                    .method("PATCH", HttpRequest.BodyPublishers.ofString(json))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                System.out.println("Erzieher erfolgreich bearbeitet.");
            } else {
                System.out.println("Fehler beim Bearbeiten des Erziehers. Status: " + response.statusCode());
            }
        } catch (Exception e) {
            System.out.println("Fehler bei der API-Anfrage: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Lösche einen Erzieher
    public static void deleteErzieher(String erzieherId) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            String url = SUPABASE_URL + "/rest/v1/" + TABLE_NAME + "?eq=erzieher_id." + erzieherId;

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("apikey", API_KEY)
                    .header("Authorization", "Bearer " + API_KEY)
                    .method("DELETE", HttpRequest.BodyPublishers.noBody())
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                System.out.println("Erzieher erfolgreich gelöscht.");
            } else {
                System.out.println("Fehler beim Löschen des Erziehers. Status: " + response.statusCode());
            }
        } catch (Exception e) {
            System.out.println("Fehler bei der API-Anfrage: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
