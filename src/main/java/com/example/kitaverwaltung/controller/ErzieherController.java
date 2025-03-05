package com.example.kitaverwaltung.controller;

import com.example.kitaverwaltung.dao.ErzieherDAO;
import com.example.kitaverwaltung.dao.VerwalterDAO;
import com.example.kitaverwaltung.model.Erzieher;
import com.example.kitaverwaltung.model.Verwalter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ErzieherController {

    @FXML private TableView<Erzieher> erzieherTable;
    @FXML private TableColumn<Erzieher, Integer> erzieher_idColumn;
    @FXML private TableColumn<Erzieher, String> vornameColumn;
    @FXML private TableColumn<Erzieher, String> nachnameColumn;
    @FXML private TableColumn<Erzieher, String> adresseColumn;
    @FXML private TableColumn<Erzieher, Double> gehaltColumn;
    @FXML private TableColumn<Erzieher, String> emailColumn;

    private ObservableList<Erzieher> erzieherListe = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        erzieher_idColumn.setCellValueFactory(new PropertyValueFactory<>("erzieher_id"));
        vornameColumn.setCellValueFactory(new PropertyValueFactory<>("vorname"));
        nachnameColumn.setCellValueFactory(new PropertyValueFactory<>("nachname"));
        adresseColumn.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        gehaltColumn.setCellValueFactory(new PropertyValueFactory<>("gehalt"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        // Initialisiere die TableColumns
        //initializeTableColumns();

        // Lade die Erzieher-Daten
        loadErzieherData();
    }


    private void loadErzieherData() {
        // Erzieher-Daten aus der DAO laden
        //erzieherListe.setAll(ErzieherDAO.getErzieher());
        ObservableList<Erzieher> erzieherListe = FXCollections.observableArrayList(ErzieherDAO.getErzieher());

        if (erzieherListe.isEmpty()) {
            System.out.println("❌ Keine Erzieher-Daten gefunden.");
        } else {
            System.out.println("✅ Erzieher-Daten erfolgreich geladen.");
        }

        erzieherTable.setItems(erzieherListe);
    }

    @FXML
    private void addErzieher() {
        // Logik zum Hinzufügen eines neuen Erziehers
    }

    @FXML
    private void editErzieher() {
        // Logik zum Bearbeiten eines Erziehers
    }

    @FXML
    private void deleteErzieher() {
        // Logik zum Löschen eines Erziehers
    }
}
