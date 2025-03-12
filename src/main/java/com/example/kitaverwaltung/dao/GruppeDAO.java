package com.example.kitaverwaltung.dao;

import com.example.kitaverwaltung.db.DatabaseConnection;
import com.example.kitaverwaltung.model.Gruppe;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.GsonBuilder;

public class GruppeDAO {

    private static final String VIEW_NAME = "v_gruppen";
    private static final String TABLE_NAME = "t_gruppe";
    private static final DatabaseConnection dbConnection = DatabaseConnection.getInstance();
    private static final Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

    // Hole alle Gruppen
    public static List<Gruppe> getGruppen() {
        List<Gruppe> gruppeListe = new ArrayList<>();

    String jsonResponse = dbConnection.sendGetRequest(VIEW_NAME);

        if (jsonResponse != null && !jsonResponse.isEmpty()) {
            // JSON-Antwort in ein Gruppe-Array umwandeln
            Gruppe[] gruppeArray = gson.fromJson(jsonResponse, Gruppe[].class);
            for (Gruppe gruppe : gruppeArray) {
                gruppeListe.add(gruppe);
            }
        }
        return gruppeListe;
    }


    // Füge eine Gruppe hinzu
    public static boolean addGruppe(Gruppe gruppe) {
        String jsonData = gson.toJson(gruppe);
        return dbConnection.sendPostRequest(TABLE_NAME, jsonData);
    }

    // Bearbeite eine Gruppe
    public static boolean editGruppe(Gruppe gruppe) {
        String jsonData = gson.toJson(gruppe);
        return dbConnection.sendPutRequest(TABLE_NAME, jsonData);
    }

    // Lösche eine Gruppe
    public static boolean deleteGruppe(int gruppe_id) {
        return dbConnection.sendDeleteRequest(TABLE_NAME, gruppe_id);
    }
}
