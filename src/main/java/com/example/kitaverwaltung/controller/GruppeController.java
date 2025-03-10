package com.example.kitaverwaltung.controller;

import com.example.kitaverwaltung.dao.GruppeDAO;
import com.example.kitaverwaltung.model.Gruppe;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class GruppeController {

    @FXML private TableView<Gruppe> gruppeTable;
    @FXML private TableColumn<Gruppe, String> erzieher;
    @FXML private TableColumn<Gruppe, String> gruppenname;
    @FXML private TableColumn<Gruppe, String> standort_name;

    private ObservableList<Gruppe> gruppeList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        gruppenname.setCellValueFactory(new PropertyValueFactory<>("gruppenname"));
        erzieher.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getErzieherFullName()));
        standort_name.setCellValueFactory(new PropertyValueFactory<>("standortName"));

        // Lade die Gruppe-Daten
        loadGruppeData();
    }

    private void loadGruppeData() {
        List<Gruppe> gruppen = GruppeDAO.getGruppen();

        if (gruppen.isEmpty()) {
            System.out.println("❌ Keine Gruppen-Daten gefunden.");
        } else {
            System.out.println("✅ Gruppen-Daten erfolgreich geladen.");
            gruppeList.setAll(FXCollections.observableArrayList(gruppen));
        }

        gruppeTable.setItems(gruppeList);
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