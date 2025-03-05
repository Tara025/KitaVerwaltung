package com.example.kitaverwaltung.controller;

import com.example.kitaverwaltung.dao.StandortDAO;
import com.example.kitaverwaltung.dao.VerwalterDAO;
import com.example.kitaverwaltung.model.Standort;
import com.example.kitaverwaltung.model.Verwalter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class StandortController {

    @FXML private TableView<Standort> standortTable;
    @FXML private TableColumn<Standort, Integer> standort_idColumn;
    @FXML private TableColumn<Standort, String> nameColumn;
    @FXML private TableColumn<Standort, String> standortartColumn;
    @FXML private TableColumn<Standort, String> strasseColumn;
    @FXML private TableColumn<Standort, String> plzColumn;
    @FXML private TableColumn<Standort, String> stadtColumn;

    private ObservableList<Standort> standortListe = FXCollections.observableArrayList();

    @FXML
    public void initialize() {

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
        ObservableList<Standort> standortList = FXCollections.observableArrayList(StandortDAO.getStandorte());


        if (standortListe.isEmpty()) {
            System.out.println("❌ Keine Standort-Daten gefunden.");
        } else {
            System.out.println("✅ Standort-Daten erfolgreich geladen.");
        }

        standortTable.setItems(standortListe);
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
