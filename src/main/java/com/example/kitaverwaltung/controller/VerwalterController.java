package com.example.kitaverwaltung.controller;

import com.example.kitaverwaltung.dao.VerwalterDAO;
import com.example.kitaverwaltung.model.Erzieher;
import com.example.kitaverwaltung.model.Verwalter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class VerwalterController {



    @FXML private TableView<Verwalter> verwalterTable;
    @FXML private TableColumn<Verwalter, Integer> verwalter_idColumn;
    @FXML private TableColumn<Verwalter, Integer> fk_standort_idColumn;
    @FXML private TableColumn<Verwalter, String> standort_nameColumn;
    @FXML private TableColumn<Verwalter, String> vornameColumn;
    @FXML private TableColumn<Verwalter, String> nachnameColumn;
    @FXML private TableColumn<Verwalter, String> emailColumn;
    @FXML private TableColumn<Verwalter, Double> gehaltColumn;
    @FXML private TableColumn<Verwalter, String> adresseColumn;

    public TextField vornameField;
    public TextField nachnameField;
    public TextField emailField;
    public TextField gehaltField;
    public TextField adresseField;

    private final ObservableList<Verwalter> verwalterList = FXCollections.observableArrayList();


    @FXML
    public void initialize() {
        // Spalten mit den richtigen Attributen verknüpfen
        verwalter_idColumn.setCellValueFactory(new PropertyValueFactory<>("verwalter_id"));
        fk_standort_idColumn.setCellValueFactory(new PropertyValueFactory<>("fk_standort_id"));
        standort_nameColumn.setCellValueFactory(new PropertyValueFactory<>("standort_name"));
        vornameColumn.setCellValueFactory(new PropertyValueFactory<>("vorname"));
        nachnameColumn.setCellValueFactory(new PropertyValueFactory<>("nachname"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        gehaltColumn.setCellValueFactory(new PropertyValueFactory<>("gehalt"));
        adresseColumn.setCellValueFactory(new PropertyValueFactory<>("adresse"));

        // Daten laden
        loadVerwalterData();
    }

    private void loadVerwalterData() {
        List<Verwalter> verwalter = VerwalterDAO.getVerwalter();

        if (verwalter == null || verwalter.isEmpty()) {
            System.out.println("❌ Keine Verwalter-Daten gefunden.");
        } else {
            System.out.println("✅ Verwalter-Daten erfolgreich geladen: " + verwalterList.size() + " Einträge");

            // Umwandlung der normalen Liste in eine ObservableList
            ObservableList<Verwalter> observableVerwalterListe = FXCollections.observableArrayList(verwalter);

            this.verwalterList.setAll(observableVerwalterListe); // Aktualisiere ObservableList

            verwalterTable.setItems(this.verwalterList);
        }

    }

    @FXML
    private void deleteSelectedVerwalter() {
        Verwalter selectedVerwalter = verwalterTable.getSelectionModel().getSelectedItem();
        if (selectedVerwalter != null) {
            // Confirmation alert
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Löschen bestätigen");
            alert.setHeaderText("Sind Sie sicher, dass Sie diesen Verwalter löschen möchten?");
            alert.setContentText(selectedVerwalter.getVorname() + " " + selectedVerwalter.getNachname());

            if (alert.showAndWait().get() == ButtonType.OK) {
                boolean success = VerwalterDAO.deleteVerwalter(selectedVerwalter.getVerwalter_id());
                if (success) {
                    verwalterList.remove(selectedVerwalter);
                    //showAlert(Alert.AlertType.INFORMATION, "Erfolg", "Verwalter wurde erfolgreich gelöscht.");
                } else {
                    showAlert(Alert.AlertType.ERROR, "Fehler", "Fehler beim Löschen des Verwalters.");
                }
            }
        } else {
            showAlert(Alert.AlertType.WARNING, "Warnung", "Bitte wählen Sie einen Verwalter aus.");
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
