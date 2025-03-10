package com.example.kitaverwaltung.controller;

import com.example.kitaverwaltung.dao.ElternDAO;
import com.example.kitaverwaltung.model.Eltern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class ElternController {

    @FXML
    private TableView<Eltern> elternTable;
    @FXML private TableColumn<Eltern, Integer> eltern_idColumn;
    @FXML private TableColumn<Eltern, String> rolColumn;
    @FXML private TableColumn<Eltern, String> vornameColumn;
    @FXML private TableColumn<Eltern, String> nachnameColumn;
    @FXML private TableColumn<Eltern, String> emailColumn;
    // @FXML private TableColumn<Eltern, Double> kindColumn;
    @FXML private TableColumn<Eltern, String> adresseColumn;

    private final ObservableList<Eltern> elternListe = FXCollections.observableArrayList();


@FXML
public void initialize() {
    // Spalten mit den richtigen Attributen verknüpfen
    eltern_idColumn.setCellValueFactory(new PropertyValueFactory<>("eltern_id"));
    rolColumn.setCellValueFactory(new PropertyValueFactory<>("rolle"));
    vornameColumn.setCellValueFactory(new PropertyValueFactory<>("vorname"));
    nachnameColumn.setCellValueFactory(new PropertyValueFactory<>("nachname"));
    emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
    //kindColumn.setCellValueFactory(new PropertyValueFactory<>("kind"));
    adresseColumn.setCellValueFactory(new PropertyValueFactory<>("adresse"));

    // Daten laden
    loadElternData();
}

    private void loadElternData() {
        List<Eltern> elternList = ElternDAO.getEltern(); // Lade Daten aus der DB

        if (elternList == null || elternList.isEmpty()) {
            System.out.println("❌ Keine Eltern-Daten gefunden.");
        } else {
            System.out.println("✅ Eltern-Daten erfolgreich geladen: " + elternList.size() + " Einträge");

            // Umwandlung der normalen Liste in eine ObservableList
            ObservableList<Eltern> observableElternListe = FXCollections.observableArrayList(elternList);

            this.elternListe.setAll(observableElternListe); // Aktualisiere ObservableList
            elternTable.setItems(this.elternListe); // Setze die Daten in die TableView
        }
    }


@FXML
private void deleteSelectedEltern() {
    Eltern selectedEltern = elternTable.getSelectionModel().getSelectedItem();
    if (selectedEltern != null) {
        // Confirmation alert
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Löschen bestätigen");
        alert.setHeaderText("Sind Sie sicher, dass Sie dieses Elternteil löschen möchten?");
        alert.setContentText(selectedEltern.getVorname() + " " + selectedEltern.getNachname());

        if (alert.showAndWait().get() == ButtonType.OK) {
            boolean success = ElternDAO.deleteEltern(selectedEltern.getEltern_id());
            if (success) {
                elternListe.remove(selectedEltern);
                //showAlert(Alert.AlertType.INFORMATION, "Erfolg", "Elternteil wurde erfolgreich gelöscht.");
            } else {
                showAlert(Alert.AlertType.ERROR, "Fehler", "Fehler beim Löschen des Elternteils.");
            }
        }
    } else {
        showAlert(Alert.AlertType.WARNING, "Warnung", "Bitte wählen Sie ein Elternteil aus.");
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

