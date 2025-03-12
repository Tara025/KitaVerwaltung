package com.example.kitaverwaltung.model;

import java.util.Date;


public class Anwesenheit {

    private Date datum;
    private String status;

    public Anwesenheit(Date datum, String status) {

        this.datum = datum;
        this.status = status;
    }

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