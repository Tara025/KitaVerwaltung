package com.example.kitaverwaltung.model;

public class Gruppe {

    private int gruppe_id;
    private int fk_standort_id;
    private String name;
    private int fk_erzieher_id;

    // Konstruktor
    public Gruppe(int gruppe_id, int fk_standort_id, String name, int fk_erzieher_id) {
        this.gruppe_id = gruppe_id;
        this.fk_standort_id = fk_standort_id;
        this.name = name;
        this.fk_erzieher_id = fk_erzieher_id;
    }

    // Getter und Setter
    public int getGruppe_id() {
        return gruppe_id;
    }

    public void setGruppe_id(int gruppe_id) {
        this.gruppe_id = gruppe_id;
    }

    public int getFk_standort_id() {
        return fk_standort_id;
    }

    public void setFk_standort_id(int fk_standort_id) {
        this.fk_standort_id = fk_standort_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFk_erzieher_id() {
        return fk_erzieher_id;
    }

    public void setFk_erzieher_id(int fk_erzieher_id) {
        this.fk_erzieher_id = fk_erzieher_id;
    }
}
