package com.example.kitaverwaltung.controller;

import com.example.kitaverwaltung.dao.VerwalterDAO;
import com.example.kitaverwaltung.model.Verwalter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class VerwalterController {

    @FXML private TableView<Verwalter> verwalterTable;
    @FXML private TableColumn<Verwalter, Integer> verwalter_idColumn;
    @FXML private TableColumn<Verwalter, Integer> fk_standort_idColumn;
    @FXML private TableColumn<Verwalter, String> vornameColumn;
    @FXML private TableColumn<Verwalter, String> nachnameColumn;
    @FXML private TableColumn<Verwalter, String> emailColumn;
    @FXML private TableColumn<Verwalter, Double> gehaltColumn;
    @FXML private TableColumn<Verwalter, String> adresseColumn;

    private final ObservableList<Verwalter> verwalterListe = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Spalten mit den richtigen Attributen verknüpfen
        verwalter_idColumn.setCellValueFactory(new PropertyValueFactory<>("verwalter_id"));
        fk_standort_idColumn.setCellValueFactory(new PropertyValueFactory<>("fk_standort_id"));
        vornameColumn.setCellValueFactory(new PropertyValueFactory<>("vorname"));
        nachnameColumn.setCellValueFactory(new PropertyValueFactory<>("nachname"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        gehaltColumn.setCellValueFactory(new PropertyValueFactory<>("gehalt"));
        adresseColumn.setCellValueFactory(new PropertyValueFactory<>("adresse"));

        // Daten laden
        loadVerwalterData();
    }

    private void loadVerwalterData() {
        List<Verwalter> verwalterList = VerwalterDAO.getVerwalter();

        if (verwalterList.isEmpty()) {
            System.out.println("❌ Keine Verwalter-Daten gefunden.");
        } else {
            System.out.println("✅ Verwalter-Daten erfolgreich geladen: " + verwalterList.size() + " Einträge");
            verwalterListe.setAll(verwalterList);
        }

        verwalterTable.setItems(verwalterListe);
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
                    verwalterListe.remove(selectedVerwalter);
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
