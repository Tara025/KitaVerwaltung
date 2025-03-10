package com.example.kitaverwaltung.model;

import java.util.Date;


public class Anwesenheit {
    //private String vorname;
    //private String nachname;
    private Date datum;
    private String status;

    /*public Anwesenheit(String vorname, String nachname, String date, String status) {
        this.vorname = vorname;
        this.nachname = nachname;
        this.date = date;
        this.status = status;
    }*/

    public Anwesenheit(Date datum, String status) {

        this.datum = datum;
        this.status = status;
    }

    /*public String getVorname() {
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
    }*/

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}