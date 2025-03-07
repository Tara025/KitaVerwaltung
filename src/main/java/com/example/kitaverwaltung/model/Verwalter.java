package com.example.kitaverwaltung.model;

import com.google.gson.annotations.Expose;

public class Verwalter {
    @Expose
    private int verwalter_id;
    private int fk_standort_id;
    private String vorname;
    private String nachname;
    private String adresse;
    private double gehalt;
    private String email;
    private boolean deleted; //noch hinzugefügt, für das Login handling



   // Konstruktor
    public Verwalter(int verwalter_id, int fk_standort_id, String vorname, String nachname, String adresse, double gehalt, String email, boolean deleted) {
        this.verwalter_id = verwalter_id;
        this.fk_standort_id = fk_standort_id;
        this.vorname = vorname;
        this.nachname = nachname;
        this.adresse = adresse;
        this.gehalt = gehalt;
        this.email = email;
        this.deleted = false;
    }


    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public int getVerwalter_id() {
        return verwalter_id;
    }

    public void setVerwalter_id(int verwalter_id) {
        this.verwalter_id = verwalter_id;
    }

    public int getFk_standort_id() {
        return fk_standort_id;
    }

    public void setFk_standort_id(int fk_standort_id) {
        this.fk_standort_id = fk_standort_id;
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
