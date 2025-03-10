package com.example.kitaverwaltung.dao;

import com.example.kitaverwaltung.db.DatabaseConnection;
import com.example.kitaverwaltung.model.Anwesenheit;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AnwesenheitDAO {

    private static final DatabaseConnection dbConnection = DatabaseConnection.getInstance();
    private static final Gson gson = new Gson();

    public static List<String> getNamesByType(String type) {
        String tableName = getTableNameByType(type);
        String jsonResponse = dbConnection.sendGetRequest(tableName);
        List<String> names = new ArrayList<>();
        if (jsonResponse != null) {
            Person[] personArray = gson.fromJson(jsonResponse, Person[].class);
            for (Person person : personArray) {
                names.add(person.getVorname() + " " + person.getNachname());
            }
            // Sort names alphabetically
            Collections.sort(names, Comparator.naturalOrder());
        }
        return names;
    }

    public static List<Anwesenheit> getAnwesenheitByTypeAndName(String type, String name) {
        String viewName = getViewNameByType(type);
        String[] nameParts = name.split(" ");
        if (nameParts.length < 2) {
            throw new IllegalArgumentException("Name must include both vorname and nachname");
        }
        String vorname = nameParts[0];
        String nachname = nameParts[1];
        try {
            String encodedVorname = URLEncoder.encode(vorname, "UTF-8");
            String encodedNachname = URLEncoder.encode(nachname, "UTF-8");
            String jsonResponse = dbConnection.sendGetRequest(viewName + "?vorname=eq." + encodedVorname + "&nachname=eq." + encodedNachname);
            List<Anwesenheit> anwesenheitList = new ArrayList<>();
            if (jsonResponse != null) {
                Anwesenheit[] anwesenheitArray = gson.fromJson(jsonResponse, Anwesenheit[].class);
                for (Anwesenheit anwesenheit : anwesenheitArray) {
                    anwesenheitList.add(anwesenheit);
                }
            }
            return anwesenheitList;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    private static String getTableNameByType(String type) {
        switch (type) {
            case "Verwalter":
                return "t_verwalter";
            case "Erzieher":
                return "t_erzieher";
            case "Kinder":
                return "t_kind";
            default:
                throw new IllegalArgumentException("Invalid type: " + type);
        }
    }

    private static String getViewNameByType(String type) {
        switch (type) {
            case "Verwalter":
                return "v_anwesenheit_verwalter";
            case "Erzieher":
                return "v_anwesenheit_erzieher";
            case "Kinder":
                return "v_anwesenheit_kinder";
            default:
                throw new IllegalArgumentException("Invalid type: " + type);
        }
    }

    public static class Person {
        private String vorname;
        private String nachname;

        public String getVorname() {
            return vorname;
        }

        public String getNachname() {
            return nachname;
        }
    }
}