package com.example.kitaverwaltung.dao;

import com.example.kitaverwaltung.config.Config;
import com.example.kitaverwaltung.model.Kind;
import com.google.gson.Gson;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class KindDAO {

    private static final String SUPABASE_URL = "https://foedwwepqjbyhopvxmod.supabase.co";
    private static final String API_KEY = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImZvZWR3d2VwcWjieWhvcHZ4bW9kIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDA5ODc2MTAsImV4cCI6MjA1NjU2MzYxMH0.uugXr1Erwk6j7UukqDR96H83yCWyKjNppsF1UtI-j8w";
    private static final String TABLE_NAME = "t_kind";

    // Hole alle Kinder
    public static List<Kind> getKinder() {
        List<Kind> kinderListe = new ArrayList<>();

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
                Kind[] kindArray = gson.fromJson(responseBody, Kind[].class);

                // Kind-Objekte zur Liste hinzufügen
                for (Kind kind : kindArray) {
                    kinderListe.add(kind);
                }
            } else {
                System.out.println("Fehler beim Abrufen der Daten. Status: " + response.statusCode());
            }
        } catch (Exception e) {
            System.out.println("Fehler bei der API-Anfrage: " + e.getMessage());
            e.printStackTrace();
        }

        return kinderListe;
    }

    // Füge ein Kind hinzu
    public static void addKind(Kind kind) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            String url = SUPABASE_URL + "/rest/v1/" + TABLE_NAME;

            // JSON-Verarbeitung mit Gson
            Gson gson = new Gson();
            String json = gson.toJson(kind);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("apikey", Config.API_KEY)
                    .header("Authorization", "Bearer " + Config.API_KEY)
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 201) {
                System.out.println("Kind erfolgreich hinzugefügt.");
            } else {
                System.out.println("Fehler beim Hinzufügen des Kindes. Status: " + response.statusCode());
            }
        } catch (Exception e) {
            System.out.println("Fehler bei der API-Anfrage: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Bearbeite ein Kind
    public static void editKind(Kind kind, String kindId) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            String url = SUPABASE_URL + "/rest/v1/" + TABLE_NAME + "?eq=kinder_id." + kindId;

            // JSON-Verarbeitung mit Gson
            Gson gson = new Gson();
            String json = gson.toJson(kind);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("apikey", Config.API_KEY)
                    .header("Authorization", "Bearer " + Config.API_KEY)
                    .header("Content-Type", "application/json")
                    .method("PATCH", HttpRequest.BodyPublishers.ofString(json))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                System.out.println("Kind erfolgreich bearbeitet.");
            } else {
                System.out.println("Fehler beim Bearbeiten des Kindes. Status: " + response.statusCode());
            }
        } catch (Exception e) {
            System.out.println("Fehler bei der API-Anfrage: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Lösche ein Kind
    public static void deleteKind(String kindId) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            String url = SUPABASE_URL + "/rest/v1/" + TABLE_NAME + "?eq=kinder_id." + kindId;

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("apikey", Config.API_KEY)
                    .header("Authorization", "Bearer " + Config.API_KEY)
                    .method("DELETE", HttpRequest.BodyPublishers.noBody())
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                System.out.println("Kind erfolgreich gelöscht.");
            } else {
                System.out.println("Fehler beim Löschen des Kindes. Status: " + response.statusCode());
            }
        } catch (Exception e) {
            System.out.println("Fehler bei der API-Anfrage: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
