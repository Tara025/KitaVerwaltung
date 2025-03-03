package com.example.kitaverwaltung.model;

import com.google.gson.annotations.Expose;

public class Verwalter {
    @Expose
    private String name;

    // Standard-Konstruktor (Gson braucht ihn!)
    public Verwalter() {}

    public Verwalter(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {  // Setter hinzufügen
        this.name = name;
    }
}