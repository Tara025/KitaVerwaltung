package com.example.kitaverwaltung.dao;

import com.example.kitaverwaltung.db.DatabaseConnection;
import com.example.kitaverwaltung.model.Verwalter;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class VerwalterDAO {

    private static final String TABLE_NAME = "t_verwalter";
    private static final DatabaseConnection dbConnection = DatabaseConnection.getInstance();
    private static final Gson gson = new Gson();

    // Hole alle Verwalter
    public static List<Verwalter> getVerwalter() {
        List<Verwalter> verwalterListe = new ArrayList<>();

        String jsonResponse = dbConnection.sendGetRequest(TABLE_NAME);
        if (jsonResponse != null) {
            Verwalter[] verwalterArray = gson.fromJson(jsonResponse, Verwalter[].class);
            for (Verwalter verwalter : verwalterArray) {
                verwalterListe.add(verwalter);
            }
        }
        return verwalterListe;
    }

    // Füge einen Verwalter hinzu
    public static boolean addVerwalter(Verwalter verwalter) {
        String json = gson.toJson(verwalter);
        return dbConnection.sendPostRequest(TABLE_NAME, json);
    }
}
