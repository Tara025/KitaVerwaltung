package com.example.kitaverwaltung.dao;

import com.example.kitaverwaltung.db.DatabaseConnection;
import com.example.kitaverwaltung.model.Kind;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

public class KindDAO {

    private static final String TABLE_NAME = "t_kind";
    private static final String VIEW_NAME = "v_kind";
    private static final String CONNECTION_TABLE_NAME = "vt_eltern_kind";
    private static final DatabaseConnection dbConnection = DatabaseConnection.getInstance();
    private static final Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

    // Hole alle Kinder aus der View
    public static List<Kind> getKinder() {
        List<Kind> kinderListe = new ArrayList<>();

        String jsonResponse = dbConnection.sendGetRequest(VIEW_NAME);
        if (jsonResponse != null && !jsonResponse.isEmpty()) {
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

    // Füge eine Verbindung zwischen Kind und Eltern hinzu
    public static boolean addElternKindConnection(int kindId, int elternId) {
        String jsonData = String.format("{\"fk_kind_id\": %d, \"fk_eltern_id\": %d}", kindId, elternId);
        return dbConnection.sendPostRequest(CONNECTION_TABLE_NAME, jsonData);
    }

    // Hole die ID des zuletzt eingefügten Kindes
    public static int getLastInsertedKindId() {
        String jsonResponse = dbConnection.sendGetRequest(TABLE_NAME + "?order=kind_id.desc&limit=1");
        if (jsonResponse != null && !jsonResponse.isEmpty()) {
            Kind[] kinderArray = gson.fromJson(jsonResponse, Kind[].class);
            if (kinderArray.length > 0) {
                return kinderArray[0].getKind_id();
            }
        }
        return -1;
    }
}