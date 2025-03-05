package com.example.kitaverwaltung.model;

import com.google.gson.annotations.Expose;

public class Erzieher {

    private int erzieher_id;
    private String vorname;
    private String nachname;
    private String adresse;
    private double gehalt;
    private String email;

    // Konstruktor
    public Erzieher(int erzieher_id, String vorname, String nachname, String adresse, double gehalt, String email) {
        this.erzieher_id = erzieher_id;
        this.vorname = vorname;
        this.nachname = nachname;
        this.adresse = adresse;
        this.gehalt = gehalt;
        this.email = email;
    }

    // Getter und Setter
    public int getErzieher_id() {
        return erzieher_id;
    }

    public void setErzieher_id(int erzieher_id) {
        this.erzieher_id = erzieher_id;
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
