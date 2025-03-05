package com.example.kitaverwaltung.controller;

import com.example.kitaverwaltung.dao.GruppeDAO;
import com.example.kitaverwaltung.model.Gruppe;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class GruppeController {

    @FXML private TableView<Gruppe> gruppeTable;
    @FXML private TableColumn<Gruppe, Integer> gruppe_idColumn;
    @FXML private TableColumn<Gruppe, Integer> fk_standort_idColumn;
    @FXML private TableColumn<Gruppe, String> nameColumn;
    @FXML private TableColumn<Gruppe, Integer> fk_erzieher_idColumn;

    private ObservableList<Gruppe> gruppeListe = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Initialisiere die TableColumns
        initializeTableColumns();

        // Lade die Gruppe-Daten
        loadGruppeData();
    }

    private void initializeTableColumns() {
        gruppe_idColumn.setCellValueFactory(new PropertyValueFactory<>("gruppe_id"));
        fk_standort_idColumn.setCellValueFactory(new PropertyValueFactory<>("fk_standort_id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        fk_erzieher_idColumn.setCellValueFactory(new PropertyValueFactory<>("fk_erzieher_id"));
    }

    private void loadGruppeData() {
        // Gruppe-Daten aus der DAO laden
        gruppeListe.setAll(GruppeDAO.getGruppen());

        if (gruppeListe.isEmpty()) {
            System.out.println("❌ Keine Gruppe-Daten gefunden.");
        } else {
            System.out.println("✅ Gruppe-Daten erfolgreich geladen.");
        }

        gruppeTable.setItems(gruppeListe);
    }

    @FXML
    private void addGruppe() {
        // Logik zum Hinzufügen einer neuen Gruppe
    }

    @FXML
    private void editGruppe() {
        // Logik zum Bearbeiten einer Gruppe
    }

    @FXML
    private void deleteGruppe() {
        // Logik zum Löschen einer Gruppe
    }
}
