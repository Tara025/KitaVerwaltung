package com.example.kitaverwaltung.controller;

import com.example.kitaverwaltung.dao.KindDAO;
import com.example.kitaverwaltung.model.Kind;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class KindController {

    @FXML private TableView<Kind> kindTable;
    @FXML private TableColumn<Kind, Integer> kind_idColumn;
    @FXML private TableColumn<Kind, String> vornameColumn;
    @FXML private TableColumn<Kind, String> nachnameColumn;
    @FXML private TableColumn<Kind, String> geburtsdatumColumn;
    @FXML private TableColumn<Kind, String> fk_gruppe_idColumn;
    @FXML private TableColumn<Kind, String> bemerkungColumn;

    private ObservableList<Kind> kindList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        kind_idColumn.setCellValueFactory(new PropertyValueFactory<>("kind_id"));
        vornameColumn.setCellValueFactory(new PropertyValueFactory<>("vorname"));
        nachnameColumn.setCellValueFactory(new PropertyValueFactory<>("nachname"));
        geburtsdatumColumn.setCellValueFactory(new PropertyValueFactory<>("geburtsdatum"));
        fk_gruppe_idColumn.setCellValueFactory(new PropertyValueFactory<>("fk_gruppe_id"));
        bemerkungColumn.setCellValueFactory(new PropertyValueFactory<>("bemerkung"));

        // Lade die Kind-Daten
        loadKindData();
    }

    private void loadKindData() {
        List<Kind> kinder = KindDAO.getKinder();

        if (kinder.isEmpty()) {
            System.out.println("❌ Keine Kind-Daten gefunden.");
        } else {
            System.out.println("✅ Kind-Daten erfolgreich geladen.");
            kindList.setAll(FXCollections.observableArrayList(kinder));
        }

        kindTable.setItems(kindList);
    }

    @FXML
    private void addKind() {
        // Logik zum Hinzufügen eines neuen Kindes
    }

    @FXML
    private void editKind() {
        // Logik zum Bearbeiten eines Kindes
    }

    @FXML
    private void deleteKind() {
        // Logik zum Löschen eines Kindes
    }
}