package com.example.kitaverwaltung.controller;

import com.example.kitaverwaltung.dao.ErzieherDAO;
import com.example.kitaverwaltung.model.Erzieher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class ErzieherController {

    @FXML private TableView<Erzieher> erzieherTable;
    @FXML private TableColumn<Erzieher, Integer> erzieher_idColumn;
    @FXML private TableColumn<Erzieher, String> vornameColumn;
    @FXML private TableColumn<Erzieher, String> nachnameColumn;
    @FXML private TableColumn<Erzieher, String> adresseColumn;
    @FXML private TableColumn<Erzieher, Double> gehaltColumn;
    @FXML private TableColumn<Erzieher, String> emailColumn;
    @FXML private TableColumn<Erzieher, String> gruppeNameColumn;

    @FXML private TextField vornameField;
    @FXML private TextField nachnameField;
    @FXML private TextField adresseField;
    @FXML private TextField emailField;
    @FXML private TextField gehaltField;

    private final ObservableList<Erzieher> erzieherList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Verknüpfe die Spalten mit den Attributen der Erzieher-Objekte
        erzieher_idColumn.setCellValueFactory(new PropertyValueFactory<>("erzieher_id"));
        vornameColumn.setCellValueFactory(new PropertyValueFactory<>("vorname"));
        nachnameColumn.setCellValueFactory(new PropertyValueFactory<>("nachname"));
        adresseColumn.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        gehaltColumn.setCellValueFactory(new PropertyValueFactory<>("gehalt"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        gruppeNameColumn.setCellValueFactory(new PropertyValueFactory<>("gruppe_name"));

        // Lade die Erzieher-Daten
        loadErzieherData();
    }

    private void loadErzieherData() {
        List<Erzieher> erzieher = ErzieherDAO.getErzieher(); // Lade Daten aus der DB

        if (erzieher == null || erzieher.isEmpty()) {
            System.out.println("❌ Keine Erzieher-Daten gefunden.");
        } else {
            System.out.println("✅ Erzieher-Daten erfolgreich geladen: " + erzieher.size() + " Einträge");

            // Umwandlung der normalen Liste in eine ObservableList
            ObservableList<Erzieher> observableErzieherListe = FXCollections.observableArrayList(erzieher);

            this.erzieherList.setAll(observableErzieherListe); // Aktualisiere ObservableList
            erzieherTable.setItems(this.erzieherList); // Setze die Daten in die TableView
        }
    }

    @FXML
private void addErzieher() {
    String vorname = vornameField.getText();
    String nachname = nachnameField.getText();
    String adresse = adresseField.getText();
    String email = emailField.getText();
    double gehalt;


    try {
        gehalt = Double.parseDouble(gehaltField.getText());
    } catch (NumberFormatException e) {
        showAlert(Alert.AlertType.ERROR,"Fehler", "Ungültiges Gehalt.");
        return;
    }

    if (vorname.isEmpty() || nachname.isEmpty() || adresse.isEmpty() || email.isEmpty() || gehaltField.getText().isEmpty()) {
        showAlert(Alert.AlertType.ERROR,"Fehler", "Alle Felder müssen ausgefüllt sein!");
        return;
    }

    Erzieher neuerErzieher = new Erzieher(vorname, nachname, adresse, gehalt, email);

    boolean success = ErzieherDAO.addErzieher(neuerErzieher);

    if (success) {
        loadErzieherData();
    } else {
        showAlert(Alert.AlertType.ERROR,"Fehler", "Erzieher konnte nicht hinzugefügt werden.");
    }
}

    @FXML
    private void editErzieher() {
        // Beispiel für Bearbeiten eines Erziehers
        Erzieher ausgewählterErzieher = erzieherTable.getSelectionModel().getSelectedItem();

        if (ausgewählterErzieher != null) {
            // Bearbeite den ausgewählten Erzieher (z. B. Vorname ändern)
            ausgewählterErzieher.setVorname("Maximilian");

            boolean success = ErzieherDAO.editErzieher(ausgewählterErzieher);

            if (success) {
                // Bei Erfolg die Liste neu laden
                loadErzieherData();
            } else {
                showAlert(Alert.AlertType.ERROR,"Fehler", "Erzieher konnte nicht bearbeitet werden.");
            }
        }
    }

   @FXML
    private void deleteErzieher() {
        Erzieher selectedErzieher = erzieherTable.getSelectionModel().getSelectedItem();
        if (selectedErzieher != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Löschen bestätigen");
            alert.setHeaderText("Sind Sie sicher, dass Sie diesen Erzieher löschen möchten?");
            alert.setContentText(selectedErzieher.getVorname() + " " + selectedErzieher.getNachname());

            if (alert.showAndWait().get() == ButtonType.OK) {
                boolean success = ErzieherDAO.deleteErzieher(selectedErzieher.getErzieher_id());
                if (success) {
                    erzieherList.remove(selectedErzieher);
                } else {
                    showAlert(Alert.AlertType.ERROR, "Fehler", "Fehler beim Löschen des Erziehers.");
                }
            }
        } else {
            showAlert(Alert.AlertType.WARNING, "Warnung", "Bitte wählen Sie einen Erzieher aus.");
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
