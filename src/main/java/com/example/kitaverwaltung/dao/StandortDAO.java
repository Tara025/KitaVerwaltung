package com.example.kitaverwaltung.dao;


import com.example.kitaverwaltung.db.DatabaseConnection;
import com.example.kitaverwaltung.model.Standort;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;



public class StandortDAO {

    private static final String TABLE_NAME = "t_standort";
    private static final DatabaseConnection dbConnection = DatabaseConnection.getInstance();
    private static final Gson gson = new Gson();



    public static List<Standort> getStandorte() {
        List<Standort> standortList = new ArrayList<>();

        String jsonResponse = dbConnection.sendGetRequest(TABLE_NAME);
        if (jsonResponse != null) {
            Standort[] standortArray = gson.fromJson(jsonResponse, Standort[].class);
            for (Standort standort : standortArray) {
                standortList.add(standort);
            }
        }
        return standortList;
    }

    // Füge einen Standort hinzu
    public static boolean addStandort(Standort standort) {
        String json = gson.toJson(standort);
        return dbConnection.sendPostRequest(TABLE_NAME, json);
    }
}