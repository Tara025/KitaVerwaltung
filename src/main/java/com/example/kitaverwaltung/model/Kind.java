package com.example.kitaverwaltung.model;


import java.util.Date;

public class Kind {

    //private int fk_gruppe_id;
    private String gruppe_name;
    private int kind_id;
    private String vorname;
    private String nachname;
    private Date geburtsdatum;
    private String bemerkung;
    private String eltern;

    // Konstruktor
    public Kind(String gruppe_name, int kind_id, String vorname, String nachname, Date geburtsdatum, String bemerkung, String eltern) {
        //this.fk_gruppe_id = fk_gruppe_id;
        this.kind_id = kind_id;
        this.vorname = vorname;
        this.nachname = nachname;
        this.geburtsdatum = geburtsdatum;
        this.bemerkung = bemerkung;
        this.gruppe_name = gruppe_name;
        this.eltern = eltern;
    }

    // Getter und Setter

    public String getGruppe_name() {
        return gruppe_name;
    }
    public void setGruppe_name(String gruppe_name) {}


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

    public Date getGeburtsdatum() {
        return geburtsdatum;
    }

    public void setGeburtsdatum(Date geburtsdatum) {
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
}
