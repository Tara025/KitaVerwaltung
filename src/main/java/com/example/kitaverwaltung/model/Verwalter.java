package com.example.kitaverwaltung.model;

import com.google.gson.annotations.Expose;

public class Verwalter {
    @Expose
    private int verwalter_id;
    private int standort_id;
    private String vorname;
    private String nachname;
    private String adresse;
    private double gehalt;
    private String email;



   // Konstruktor


    public Verwalter(int verwalter_id, int standort_id, String vorname, String nachname, String adresse, double gehalt, String email) {
        this.verwalter_id = verwalter_id;
        this.standort_id = standort_id;
        this.vorname = vorname;
        this.nachname = nachname;
        this.adresse = adresse;
        this.gehalt = gehalt;
        this.email = email;
    }

    public int getVerwalter_id() {
        return verwalter_id;
    }

    public void setVerwalter_id(int verwalter_id) {
        this.verwalter_id = verwalter_id;
    }

    public int getStandort_id() {
        return standort_id;
    }

    public void setStandort_id(int standort_id) {
        this.standort_id = standort_id;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public double getGehalt() {
        return gehalt;
    }

    public void setGehalt(double gehalt) {
        this.gehalt = gehalt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
