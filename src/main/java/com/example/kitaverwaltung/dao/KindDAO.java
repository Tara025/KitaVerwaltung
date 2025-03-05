package com.example.kitaverwaltung.dao;

import com.example.kitaverwaltung.db.DatabaseConnection;
import com.example.kitaverwaltung.model.Kind;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class KindDAO {

    private static final String TABLE_NAME = "t_kind";
    private static final DatabaseConnection dbConnection = DatabaseConnection.getInstance();
    private static final Gson gson = new Gson();

    // Hole alle Kinder
    public static List<Kind> getKinder() {
        List<Kind> kinderListe = new ArrayList<>();

        // Anfrage an die Datenbank senden und Antwort erhalten
        String jsonResponse = dbConnection.sendGetRequest(TABLE_NAME);
        if (jsonResponse != null) {
            // JSON-Antwort in ein Kind-Array umwandeln
            Kind[] kinderArray = gson.fromJson(jsonResponse, Kind[].class);
            for (Kind kind : kinderArray) {
                kinderListe.add(kind);
            }
        }
        return kinderListe;
    }

    // Füge ein Kind hinzu
    public static boolean addKind(Kind kind) {
        String jsonData = gson.toJson(kind);
        return dbConnection.sendPostRequest(TABLE_NAME, jsonData);
    }

    // Bearbeite ein Kind
    public static boolean editKind(Kind kind) {
        String jsonData = gson.toJson(kind);
        return dbConnection.sendPutRequest(TABLE_NAME, jsonData);
    }

    // Lösche ein Kind
    public static boolean deleteKind(int kindId) {
        return dbConnection.sendDeleteRequest(TABLE_NAME, kindId);
    }
}
