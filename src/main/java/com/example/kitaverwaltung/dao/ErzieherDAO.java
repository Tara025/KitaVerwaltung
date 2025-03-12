package com.example.kitaverwaltung.dao;

import com.example.kitaverwaltung.db.DatabaseConnection;
import com.example.kitaverwaltung.model.Erzieher;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class ErzieherDAO {

    private static final String TABLE_NAME = "t_erzieher";
    private static final String VIEW_NAME = "v_erzieher";
    private static final DatabaseConnection dbConnection = DatabaseConnection.getInstance();
    private static final Gson gson = new Gson();

    // Hole alle Erzieher
    public static List<Erzieher> getErzieher() {
        List<Erzieher> erzieherListe = new ArrayList<>();

        // Anfrage an die API senden und Antwort empfangen
        String jsonResponse = dbConnection.sendGetRequest(VIEW_NAME);

        if (jsonResponse != null && !jsonResponse.isEmpty()) {
            // JSON-Antwort in ein Erzieher-Array umwandeln
            Erzieher[] erzieherArray = gson.fromJson(jsonResponse, Erzieher[].class);
            for (Erzieher erzieher : erzieherArray) {
                erzieherListe.add(erzieher);
            }
        }
        return erzieherListe; // Rückgabe der Liste mit Erziehern
    }

    public static Erzieher getErzieherByEmailAndPassword(String email, String password) {
        try {
            String encodedEmail = URLEncoder.encode(email, "UTF-8");
            String encodedPassword = URLEncoder.encode(password, "UTF-8");
            String requestUrl = TABLE_NAME + "?email=eq." + encodedEmail + "&passwort=eq." + encodedPassword;
            System.out.println("Request URL: " + requestUrl); // Log the request URL

            String jsonResponse = dbConnection.sendGetRequest(requestUrl);

            if (jsonResponse != null && !jsonResponse.isEmpty()) {
                Erzieher[] erzieherArray = gson.fromJson(jsonResponse, Erzieher[].class);
                if (erzieherArray.length > 0) {
                    return erzieherArray[0];
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Füge einen Erzieher hinzu
    public static boolean addErzieher(Erzieher erzieher) {
        String json = gson.toJson(erzieher); // Erzieher in JSON umwandeln
        return dbConnection.sendPostRequest(TABLE_NAME, json); // POST-Anfrage senden
    }

    // Bearbeite einen Erzieher
    public static boolean editErzieher(Erzieher erzieher) {
        String json = gson.toJson(erzieher); // Erzieher in JSON umwandeln
        return dbConnection.sendPutRequest(TABLE_NAME, json); // PUT-Anfrage senden
    }

    // Lösche einen Erzieher
    public static boolean deleteErzieher(int erzieher_id) {
        return dbConnection.sendDeleteRequest(TABLE_NAME, erzieher_id); // DELETE-Anfrage senden
    }
}