package com.example.kitaverwaltung.model;



public class Eltern {
    private transient int eltern_id; // Marked as transient to exclude from serialization
    private String vorname;
    private String nachname;
    private String adresse;
    private String email;
    private String rolle;
    private String kinder;

    public Eltern( String vorname, String nachname, String adresse, String email, String rolle, String kinder) {
        this.vorname = vorname;
        this.nachname = nachname;
        this.adresse = adresse;
        this.email = email;
        this.rolle = rolle;
        this.kinder = kinder;
    }

    public Eltern( String vorname, String nachname, String adresse, String email, String rolle) {
        this.vorname = vorname;
        this.nachname = nachname;
        this.adresse = adresse;
        this.email = email;
        this.rolle = rolle;
        this.kinder = null;
    }

    public int getEltern_id() { return eltern_id; }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRolle() {
        return rolle;
    }

    public void setRolle(String rolle) {
        this.rolle = rolle;
    }

    public String getKinder() { return kinder; }

    public void setKinder(String kinder) { this.kinder = kinder; }
}

