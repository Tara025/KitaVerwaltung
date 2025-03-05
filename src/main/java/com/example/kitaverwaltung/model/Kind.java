package com.example.kitaverwaltung.model;

import java.util.Date;

public class Kind {

    private int fk_gruppe_id;
    private int kind_id;
    private String vorname;
    private String nachname;
    private Date geburtsdatum;
    private String bemerkung;

    // Konstruktor
    public Kind(int fk_gruppe_id, int kind_id, String vorname, String nachname, Date geburtsdatum, String bemerkung) {
        this.fk_gruppe_id = fk_gruppe_id;
        this.kind_id = kind_id;
        this.vorname = vorname;
        this.nachname = nachname;
        this.geburtsdatum = geburtsdatum;
        this.bemerkung = bemerkung;
    }

    // Getter und Setter
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
}
