package com.example.kitaverwaltung.dao;

import com.example.kitaverwaltung.db.DatabaseConnection;
import com.example.kitaverwaltung.model.Verwalter;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
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

    public static Verwalter getVerwalterByEmailAndPassword(String email, String password) {
        try {
            String encodedEmail = URLEncoder.encode(email, "UTF-8");
            String encodedPassword = URLEncoder.encode(password, "UTF-8");
            String requestUrl = TABLE_NAME + "?email=eq." + encodedEmail + "&passwort=eq." + encodedPassword;
            System.out.println("Request URL: " + requestUrl); // Log the request URL

            String jsonResponse = dbConnection.sendGetRequest(requestUrl);

            if (jsonResponse != null && !jsonResponse.isEmpty()) {
                Verwalter[] verwalterArray = gson.fromJson(jsonResponse, Verwalter[].class);
                if (verwalterArray.length > 0) {
                    return verwalterArray[0];
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Füge einen Verwalter hinzu
    public static boolean addVerwalter(Verwalter verwalter) {
        String json = gson.toJson(verwalter);
        return dbConnection.sendPostRequest(TABLE_NAME, json);
    }

    // Bearbeite einen Verwalter
    public static boolean editVerwalter(Verwalter verwalter) {
        String json = gson.toJson(verwalter);
        return dbConnection.sendPutRequest(TABLE_NAME, json);
    }

    // Lösche einen Verwalter
    public static boolean deleteVerwalter(int verwalterId) {
        return dbConnection.sendDeleteRequest(TABLE_NAME, verwalterId);
    }
}