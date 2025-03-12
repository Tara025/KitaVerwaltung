package com.example.kitaverwaltung.model;


import com.google.gson.annotations.Expose;

public class Erzieher {

    @Expose(serialize = false)
    private int erzieher_id;
    @Expose
    private String vorname;
    @Expose
    private String nachname;
    @Expose
    private String adresse;
    @Expose
    private double gehalt;
    @Expose
    private String email;
    @Expose
    private String gruppe_name;


    // Konstruktor
    public Erzieher(int erzieher_id, String vorname, String nachname, String adresse, double gehalt, String email, String gruppe_name) {
        this.erzieher_id = erzieher_id;
        this.vorname = vorname;
        this.nachname = nachname;
        this.adresse = adresse;
        this.gehalt = gehalt;
        this.email = email;
        this.gruppe_name = gruppe_name;
    }

    public Erzieher( String vorname, String nachname, String adresse, double gehalt, String email) {
        this.vorname = vorname;
        this.nachname = nachname;
        this.adresse = adresse;
        this.gehalt = gehalt;
        this.email = email;
        this.gruppe_name = null;
    }

    // Getter und Setter
    public int getErzieher_id() {
        return erzieher_id;
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

    public String getErzieherFullName() {
        return vorname + " " + nachname;
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

    public String getGruppe_name() { return gruppe_name; }

    public void setGruppe_name(String gruppe_name) { this.gruppe_name = gruppe_name; }

}
