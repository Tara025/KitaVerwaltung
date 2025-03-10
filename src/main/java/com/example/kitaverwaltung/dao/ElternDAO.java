package com.example.kitaverwaltung.dao;

import com.example.kitaverwaltung.db.DatabaseConnection;
import com.example.kitaverwaltung.model.Eltern;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class ElternDAO {

    private static final String TABLE_NAME = "t_eltern";
    private static final DatabaseConnection dbConnection = DatabaseConnection.getInstance();
    private static final Gson gson = new Gson();

    // Hole alle Eltern
    public static List<Eltern> getEltern() {
        List<Eltern> elternListe = new ArrayList<>();

        String jsonResponse = dbConnection.sendGetRequest(TABLE_NAME);
        if (jsonResponse != null) {
            Eltern[] elternArray = gson.fromJson(jsonResponse, Eltern[].class);
            for (Eltern eltern : elternArray) {
                elternListe.add(eltern);
            }
        }
        return elternListe;
    }

    public static boolean deleteEltern(int eltern_id) {
        return dbConnection.sendDeleteRequest(TABLE_NAME, eltern_id);

    }




}
