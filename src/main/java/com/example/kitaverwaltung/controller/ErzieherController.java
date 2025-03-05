package com.example.kitaverwaltung.controller;

import com.example.kitaverwaltung.dao.ErzieherDAO;
import com.example.kitaverwaltung.model.Erzieher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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

    private ObservableList<Erzieher> erzieherList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Verknüpfe die Spalten mit den Attributen der Erzieher-Objekte
        erzieher_idColumn.setCellValueFactory(new PropertyValueFactory<>("erzieher_id"));
        vornameColumn.setCellValueFactory(new PropertyValueFactory<>("vorname"));
        nachnameColumn.setCellValueFactory(new PropertyValueFactory<>("nachname"));
        adresseColumn.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        gehaltColumn.setCellValueFactory(new PropertyValueFactory<>("gehalt"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        // Lade die Erzieher-Daten
        loadErzieherData();
    }

    private void loadErzieherData() {
        // Lade die Erzieher-Daten über die DAO
        List<Erzieher> erzieher = ErzieherDAO.getErzieher();

        if (!erzieher.isEmpty()) {
            // Wenn Daten vorhanden sind, setze die Liste
            erzieherList.setAll(erzieher);
        }

        // Setze die ObservableList in das TableView
        erzieherTable.setItems(erzieherList);
    }

    @FXML
    private void addErzieher() {
        // Beispiel für Hinzufügen eines Erziehers (Du kannst hier ein Dialog oder eine Eingabemaske verwenden)
        Erzieher neuerErzieher = new Erzieher(0, "Max", "Mustermann", "Musterstraße 1", 2000.00, "max.mustermann@example.com");

        boolean success = ErzieherDAO.addErzieher(neuerErzieher);

        if (success) {
            // Bei Erfolg die Liste neu laden
            loadErzieherData();
        } else {
            showErrorDialog("Fehler", "Erzieher konnte nicht hinzugefügt werden.");
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
                showErrorDialog("Fehler", "Erzieher konnte nicht bearbeitet werden.");
            }
        }
    }

    @FXML
    private void deleteErzieher() {
        // Beispiel für Löschen eines Erziehers
        Erzieher ausgewählterErzieher = erzieherTable.getSelectionModel().getSelectedItem();

        if (ausgewählterErzieher != null) {
            // Bestätigung zum Löschen
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Löschen bestätigen");
            alert.setHeaderText("Sind Sie sicher, dass Sie diesen Erzieher löschen möchten?");
            alert.setContentText(ausgewählterErzieher.getVorname() + " " + ausgewählterErzieher.getNachname());

            if (alert.showAndWait().get() == ButtonType.OK) {
                boolean success = ErzieherDAO.deleteErzieher(ausgewählterErzieher.getErzieher_id());

                if (success) {
                    // Bei Erfolg die Liste neu laden
                    loadErzieherData();
                } else {
                    showErrorDialog("Fehler", "Erzieher konnte nicht gelöscht werden.");
                }
            }
        }
    }

    private void showErrorDialog(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
