package com.example.kitaverwaltung.model;

import com.google.gson.annotations.Expose;

public class Gruppe {
    @Expose(serialize = false)
    private int gruppe_id;
    @Expose
    private String name;
    @Expose
    private String gruppenname;
    @Expose
    private int fk_erzieher_id;
    @Expose
    private int fk_standort_id;
    @Expose
    private String erzieher_vorname;
    @Expose
    private String erzieher_nachname;
    @Expose
    private String standort_name;


    // Default constructor for Get (View)
    public Gruppe(int gruppe_id, String name, int fk_erzieher_id, String erzieher_vorname, String erzieher_nachname, int fk_standort_id, String standort_name) {
        this.gruppe_id = gruppe_id;
        this.name = name;
        this.fk_erzieher_id = fk_erzieher_id;
        this.erzieher_vorname = erzieher_vorname;
        this.erzieher_nachname = erzieher_nachname;
        this.fk_standort_id = fk_standort_id;
        this.standort_name = standort_name;
    }

    // Default constructor for Post (Add)
    public Gruppe(String name, int fk_erzieher_id, int fk_standort_id) {
        this.name = name;
        this.fk_erzieher_id = fk_erzieher_id;
        this.fk_standort_id = fk_standort_id;
    }


    // Getters and Setters

    public int getGruppe_id() {
        return gruppe_id;
    }
    public String getName() {
        return name;
    }

    public String getGruppenname() {
        return gruppenname;
    }

    public void setGruppenname(String gruppenname) {
        this.gruppenname = gruppenname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFk_erzieher_id() {
        return fk_erzieher_id;
    }

    public void setFk_erzieher_id(int fk_erzieher_id) {
        this.fk_erzieher_id = fk_erzieher_id;
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

    public int getFk_standort_id() {
        return fk_standort_id;
    }

    public void setFk_standort_id(int fk_standort_id) {
        this.fk_standort_id = fk_standort_id;
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