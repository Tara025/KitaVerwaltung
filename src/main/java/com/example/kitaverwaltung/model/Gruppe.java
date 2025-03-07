package com.example.kitaverwaltung.model;

public class Gruppe {
    private String gruppenname;
    private String erzieher_vorname;
    private String erzieher_nachname;
    private String standort_name;

    // Getters and Setters
    public String getGruppenname() {
        return gruppenname;
    }

    public void setGruppenname(String gruppenname) {
        this.gruppenname = gruppenname;
    }

    public String getErzieher_vorname() {
        return erzieher_vorname;
    }

    public void setErzieher_vorname(String erzieher_vorname) {
        this.erzieher_vorname = erzieher_vorname;
    }

    public String getErzieher_nachname() {
        return erzieher_nachname;
    }

    public void setErzieher_nachname(String erzieher_nachname) {
        this.erzieher_nachname = erzieher_nachname;
    }

    public String getStandortName() {
        return standort_name;
    }

    public void setStandortName(String standortName) {
        this.standort_name = standortName;
    }

    // Method to get the full name of the Erzieher
    public String getErzieherFullName() {
        return erzieher_vorname + " " + erzieher_nachname;
    }
}