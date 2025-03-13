package com.example.kitaverwaltung.model;

import com.google.gson.annotations.Expose;

import java.util.Date;

public class Kind {


    @Expose(serialize = false)
    private int kind_id;
    @Expose
    private int fk_gruppe_id;
    @Expose
    private String vorname;
    @Expose
    private String nachname;
    @Expose
    private String geburtsdatum;
    @Expose
    private String bemerkung;
    @Expose
    private String eltern;
    @Expose
    private String gruppe_name;

    // Constructor for view
    public Kind(int fk_gruppe_id, int kind_id, String vorname, String nachname, String geburtsdatum, String bemerkung, String eltern, String gruppe_name) {
        this.fk_gruppe_id = fk_gruppe_id;
        this.kind_id = kind_id;
        this.vorname = vorname;
        this.nachname = nachname;
        this.geburtsdatum = geburtsdatum;
        this.bemerkung = bemerkung;
        this.eltern = eltern;
        this.gruppe_name = gruppe_name;
    }

    // Constructor
    public Kind(int fk_gruppe_id, String vorname, String nachname, String geburtsdatum, String bemerkung) {
        this.fk_gruppe_id = fk_gruppe_id;
        this.vorname = vorname;
        this.nachname = nachname;
        this.geburtsdatum = geburtsdatum;
        this.bemerkung = bemerkung;
    }

    // Getters and Setters
    public int getFk_gruppe_id() {
        return fk_gruppe_id;
    }

    public void setFk_gruppe_id(int fk_gruppe_id) {
        this.fk_gruppe_id = fk_gruppe_id;
    }

    public int getKind_id() {
        return kind_id;
    }

    public void setKind_id(int kind_id) {
        this.kind_id = kind_id;
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

    public String getGeburtsdatum() {
        return geburtsdatum;
    }

    public void setGeburtsdatum(String geburtsdatum) {
        this.geburtsdatum = geburtsdatum;
    }

    public String getBemerkung() {
        return bemerkung;
    }

    public void setBemerkung(String bemerkung) {
        this.bemerkung = bemerkung;
    }

    public String getEltern() {
        return eltern;
    }

    public void setEltern(String eltern) {
        this.eltern = eltern;
    }

    public String getGruppe_name() {
        return gruppe_name;
    }

    public void setGruppe_name(String gruppe_name) {
        this.gruppe_name = gruppe_name;
    }
}