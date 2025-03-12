package com.example.kitaverwaltung.model;
import com.google.gson.annotations.Expose;

public class Standort {

    @Expose(serialize = false)
    private int standort_id;
    @Expose
    private String name;
    @Expose
    private String standortart;
    @Expose
    private String strasse;
    @Expose
    private String plz;
    @Expose
    private String stadt;

    // Konstruktor
    public Standort(int standort_id, String name, String standortart, String strasse, String plz, String stadt) {
        this.standort_id = standort_id;
        this.name = name;
        this.standortart = standortart;
        this.strasse = strasse;
        this.plz = plz;
        this.stadt = stadt;
    }

    // Getter und Setter
    public int getStandort_id() {
        return standort_id;
    }

    public void setStandort_id(int standort_id) {
        this.standort_id = standort_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStandortart() {
        return standortart;
    }

    public void setStandortart(String standortart) {
        this.standortart = standortart;
    }

    public String getStrasse() {
        return strasse;
    }

    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }

    public String getPlz() {
        return plz;
    }

    public void setPlz(String plz) {
        this.plz = plz;
    }

    public String getStadt() {
        return stadt;
    }

    public void setStadt(String stadt) {
        this.stadt = stadt;
    }
}
