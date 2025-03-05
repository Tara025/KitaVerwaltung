package com.example.kitaverwaltung.controller;

import com.example.kitaverwaltung.dao.VerwalterDAO;
import com.example.kitaverwaltung.model.Verwalter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class VerwalterController {

    @FXML private TableView<Verwalter> verwalterTable;
    @FXML private TableColumn<Verwalter, Integer> verwalter_idColumn;
    @FXML private TableColumn<Verwalter, Integer> fk_standort_idColumn;
    @FXML private TableColumn<Verwalter, String> vornameColumn;
    @FXML private TableColumn<Verwalter, String> nachnameColumn;
    @FXML private TableColumn<Verwalter, String> emailColumn;
    @FXML private TableColumn<Verwalter, Double> gehaltColumn;
    @FXML private TableColumn<Verwalter, String> adresseColumn;

    private ObservableList<Verwalter> verwalterListe = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // CellValueFactories setzen (damit die Spalten wissen, welche Daten sie anzeigen sollen)
        verwalter_idColumn.setCellValueFactory(new PropertyValueFactory<>("verwalter_id"));
        fk_standort_idColumn.setCellValueFactory(new PropertyValueFactory<>("fk_standort_id"));
        vornameColumn.setCellValueFactory(new PropertyValueFactory<>("vorname"));
        nachnameColumn.setCellValueFactory(new PropertyValueFactory<>("nachname"));
        adresseColumn.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        gehaltColumn.setCellValueFactory(new PropertyValueFactory<>("gehalt"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        // Daten aus der DAO laden
        loadVerwalterData();
    }

    private void loadVerwalterData() {
        ObservableList<Verwalter> verwalterList = FXCollections.observableArrayList(VerwalterDAO.getVerwalter());

        if (verwalterList.isEmpty()) {
            System.out.println("❌ Keine Verwalter-Daten gefunden.");
        } else {
            System.out.println("✅ Verwalter-Daten erfolgreich geladen: " + verwalterList.size() + " Einträge");
        }

        verwalterTable.setItems(verwalterList);
    }
}
