package com.example.kitaverwaltung.dao;

import com.example.kitaverwaltung.db.DatabaseConnection;
import com.example.kitaverwaltung.model.Eltern;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

public class ElternDAO {

    private static final String VIEW_NAME = "v_eltern";
    private static final String TABLE_NAME = "t_eltern";
    private static final DatabaseConnection dbConnection = DatabaseConnection.getInstance();
    private static final Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

    // Hole alle Eltern
    public static List<Eltern> getEltern() {
        List<Eltern> elternListe = new ArrayList<>();

        String jsonResponse = dbConnection.sendGetRequest(VIEW_NAME);
        if (jsonResponse != null) {
            Eltern[] elternArray = gson.fromJson(jsonResponse, Eltern[].class);
            for (Eltern eltern : elternArray) {
                elternListe.add(eltern);
            }
        }
        return elternListe;
    }

    // Füge ein Elternteil hinzu
    public static boolean addEltern(Eltern eltern) {
        String jsonData = gson.toJson(eltern);
        return dbConnection.sendPostRequest(TABLE_NAME, jsonData);
    }

    // Bearbeite ein Elternteil
    public static boolean editEltern(Eltern eltern) {
        String jsonData = gson.toJson(eltern);
        return dbConnection.sendPutRequest(TABLE_NAME, jsonData);
    }

    // Lösche ein Elternteil
    public static boolean deleteEltern(int eltern_id) {
        return dbConnection.sendDeleteRequest(TABLE_NAME, eltern_id);
    }




}
