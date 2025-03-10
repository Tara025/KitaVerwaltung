package com.example.kitaverwaltung.model;



public class Eltern {
    private int eltern_id;
    private String vorname;
    private String nachname;
    private String adresse;
    private String email;
    private String rolle;

    public Eltern(int eltern_id, String vorname, String nachname, String adresse, String email, String rolle) {
        this.eltern_id = eltern_id;
        this.vorname = vorname;
        this.nachname = nachname;
        this.adresse = adresse;
        this.email = email;
        this.rolle = rolle;
    }

    public int getEltern_id() {
        return eltern_id;
    }

    public void setEltern_id(int eltern_id) {
        this.eltern_id = eltern_id;
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
}

