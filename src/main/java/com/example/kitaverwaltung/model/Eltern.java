package com.example.kitaverwaltung.model;


import com.google.gson.annotations.Expose;

public class Eltern {
    @Expose(serialize = false)
    private int eltern_id;
    @Expose
    private String vorname;
    @Expose
    private String nachname;
    @Expose
    private String adresse;
    @Expose
    private String email;
    @Expose
    private String rolle;
    @Expose
    private String kinder;


    // Default constructor für Get (View)
    public Eltern(int eltern_id, String vorname, String nachname, String adresse, String email, String rolle, String kinder) {
        this.eltern_id = eltern_id;
        this.vorname = vorname;
        this.nachname = nachname;
        this.adresse = adresse;
        this.email = email;
        this.rolle = rolle;
        this.kinder = kinder;
    }

    // Default constructor für Post (Hinzufügen)
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

