package com.example.kitaverwaltung.dao;

import com.example.kitaverwaltung.db.DatabaseConnection;
import com.example.kitaverwaltung.model.Anwesenheit;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

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

    public static int insertArbeitstag(String date) {
        // Check if the date already exists
        String checkResponse = DatabaseConnection.getInstance().sendGetRequest("t_arbeitstage?datum=eq." + date);
        if (checkResponse != null && !checkResponse.isEmpty() && !checkResponse.equals("[]")) {
            JsonObject jsonObject = JsonParser.parseString(checkResponse).getAsJsonArray().get(0).getAsJsonObject();
            return jsonObject.get("arbeitstage_id").getAsInt();
        }

        // If the date does not exist, insert a new record
        String jsonData = String.format("{\"datum\": \"%s\"}", date);
        boolean success = DatabaseConnection.getInstance().sendPostRequest("t_arbeitstage", jsonData);
        if (success) {
            String response = DatabaseConnection.getInstance().sendGetRequest("t_arbeitstage?datum=eq." + date);
            if (response != null) {
                JsonObject jsonObject = JsonParser.parseString(response).getAsJsonArray().get(0).getAsJsonObject();
                return jsonObject.get("arbeitstage_id").getAsInt();
            }
        }
        return -1;
    }

    public static boolean insertStatus(String personType, String personName, int arbeitstageId, int statusId) {
        String tableName = getStatusTableNameByType(personType);
        String[] nameParts = personName.split(" ");
        if (nameParts.length < 2) {
            throw new IllegalArgumentException("Name must include both vorname and nachname");
        }
        String vorname = nameParts[0];
        String nachname = nameParts[1];
        try {
            String encodedVorname = URLEncoder.encode(vorname, "UTF-8");
            String encodedNachname = URLEncoder.encode(nachname, "UTF-8");
            String viewName = getViewNameByType(personType);
            String jsonResponse = dbConnection.sendGetRequest(viewName + "?vorname=eq." + encodedVorname + "&nachname=eq." + encodedNachname);
            //System.out.println("Response: " + jsonResponse);

            if (jsonResponse != null) {
                JsonObject jsonObject = JsonParser.parseString(jsonResponse).getAsJsonArray().get(0).getAsJsonObject();
                // dreckiger fix: Wenn personType = "Kinder", dann kind_id, sonst verwalter_id oder erzieher_id
                String key = personType.equals("Kinder") ? "kind_id" : personType.toLowerCase() + "_id";
                int personId = jsonObject.get(key).getAsInt();
                //System.out.println("personId = " + personId);
                String jsonData = String.format("{\"fk_arbeitstage_id\": %d, \"fk_status_id\": %d, \"fk_%s\": %d}", arbeitstageId, statusId, key, personId);
                return dbConnection.sendPostRequest(tableName, jsonData);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return false;
    }

    private static String getStatusTableNameByType(String type) {
        switch (type) {
            case "Verwalter":
                return "vt_verwalter_arbeitstage";
            case "Erzieher":
                return "vt_erzieher_arbeitstage";
            case "Kinder":
                return "vt_kind_arbeitstage";
            default:
                throw new IllegalArgumentException("Invalid type: " + type);
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

    public static boolean updateStatus(Anwesenheit selectedAnwesenheit, int statusId) {
       /* String tableName = getStatusTableNameByType(selectedAnwesenheit.getPersonType());
        String key = selectedAnwesenheit.getPersonType().toLowerCase() + "_arbeitstage_id";
        String jsonData = String.format("{\"fk_status_id\": %d}", statusId);
        return dbConnection.sendPatchRequest(tableName, key, selectedAnwesenheit.getPersonArbeitstageId(), jsonData);*/
        return true;
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