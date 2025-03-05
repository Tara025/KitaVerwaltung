package com.example.kitaverwaltung.controller;

import com.example.kitaverwaltung.dao.StandortDAO;
import com.example.kitaverwaltung.model.Standort;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class StandortController {

    @FXML private TableView<Standort> standortTable;
    @FXML private TableColumn<Standort, Integer> standort_idColumn;
    @FXML private TableColumn<Standort, String> nameColumn;
    @FXML private TableColumn<Standort, String> standortartColumn;
    @FXML private TableColumn<Standort, String> strasseColumn;
    @FXML private TableColumn<Standort, String> plzColumn;
    @FXML private TableColumn<Standort, String> stadtColumn;

    private ObservableList<Standort> standortList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {

        // Spalten mit den richtigen Attributen verknüpfen
        standort_idColumn.setCellValueFactory(new PropertyValueFactory<>("standort_id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        standortartColumn.setCellValueFactory(new PropertyValueFactory<>("standortart"));
        strasseColumn.setCellValueFactory(new PropertyValueFactory<>("strasse"));
        plzColumn.setCellValueFactory(new PropertyValueFactory<>("plz"));
        stadtColumn.setCellValueFactory(new PropertyValueFactory<>("stadt"));

        // Lade die Standort-Daten
        loadStandortData();
    }

    private void loadStandortData() {
        // Abrufen der Standort-Daten aus der DAO
        List<Standort> standorte = StandortDAO.getStandorte(); // Normale Liste abrufen

        if (standorte.isEmpty()) {
            System.out.println("❌ Keine Standort-Daten gefunden.");
        } else {
            System.out.println("✅ Standort-Daten erfolgreich geladen");
            // Konvertiere List in ObservableList und setze sie in die TableView
            standortList.setAll(FXCollections.observableArrayList(standorte));  // Setze die ObservableList direkt
        }

        // Aktualisiere die TableView mit der ObservableList
        standortTable.setItems(standortList); // TableView mit ObservableList verknüpfen
    }

    @FXML
    private void addStandort() {
        // Logik zum Hinzufügen eines neuen Standorts
    }

    @FXML
    private void editStandort() {
        // Logik zum Bearbeiten eines Standorts
    }

    @FXML
    private void deleteStandort() {
        // Logik zum Löschen eines Standorts
    }
}
