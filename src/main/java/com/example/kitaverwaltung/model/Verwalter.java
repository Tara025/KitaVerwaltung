package com.example.kitaverwaltung.model;

import com.google.gson.annotations.Expose;

public class Verwalter {
    @Expose
    private String vorname;
    private String nachname;



        // Konstruktor
        public Verwalter(String vorname, String nachname) {
            this.vorname = vorname;
            this.nachname = nachname;
        }

        // Getter für den Vornamen
        public String getVorname() {
            return vorname;
        }

        // Getter für den Nachnamen
        public String getNachname() {
            return nachname;
        }
    }
