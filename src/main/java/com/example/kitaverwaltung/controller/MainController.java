package com.example.kitaverwaltung.controller;

import com.example.kitaverwaltung.dao.VerwalterDAO;
import com.example.kitaverwaltung.model.Verwalter;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MainController {

    @FXML
    private TableView<Verwalter> verwalterTable;

    @FXML
    private TableColumn<Verwalter, String> vornameColumn;

    @FXML
    private TableColumn<Verwalter, String> nachnameColumn;

    private ObservableList<Verwalter> verwalterListe = FXCollections.observableArrayList();


    @FXML
    public void initialize() {
        // Setze die CellValueFactory für die Vorname-Spalte
        vornameColumn.setCellValueFactory(new PropertyValueFactory<>("vorname"));

        // Setze die CellValueFactory für die Nachname-Spalte
        nachnameColumn.setCellValueFactory(new PropertyValueFactory<>("nachname"));

        // Lade die Verwalter-Daten aus der DAO
        ObservableList<Verwalter> verwalterList = FXCollections.observableArrayList(VerwalterDAO.getVerwalter());


        // Debugging: Überprüfen, ob die Liste leer ist
        if (verwalterList.isEmpty()) {
            System.out.println("Keine Verwalter-Daten gefunden.");
        } else {
            for (Verwalter verwalter : verwalterList) {
                System.out.println("Verwalter Name: " + verwalter.getVorname() + " " + verwalter.getNachname());
            }
        }
        // Setze die Daten in die TableView
        verwalterTable.setItems(verwalterList);

    }
}
// Verwalter-Daten von der DAO abrufen
// verwalterListe.setAll(VerwalterDAO.getVerwalter());

// Die geladenen Daten in das TableView setzen
//verwalterTable.setItems(verwalterListe);