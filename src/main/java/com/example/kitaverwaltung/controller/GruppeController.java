package com.example.kitaverwaltung.controller;

import com.example.kitaverwaltung.dao.ErzieherDAO;
import com.example.kitaverwaltung.dao.GruppeDAO;
import com.example.kitaverwaltung.dao.StandortDAO;
import com.example.kitaverwaltung.model.Erzieher;
import com.example.kitaverwaltung.model.Gruppe;
import com.example.kitaverwaltung.model.Standort;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.StringConverter;

import java.util.List;

public class GruppeController {

    @FXML private TableView<Gruppe> gruppeTable;
    @FXML private TableColumn<Gruppe, Integer> gruppe_id;
    @FXML private TableColumn<Gruppe, String> erzieher;
    @FXML private TableColumn<Gruppe, String> name;
    @FXML private TableColumn<Gruppe, String> standort_name;

    @FXML private TextField gruppennameField;
    @FXML private ComboBox<Standort> standortComboBox;
    @FXML private ComboBox<Erzieher> erzieherComboBox;

    private ObservableList<Gruppe> gruppeList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        gruppe_id.setCellValueFactory(new PropertyValueFactory<>("gruppe_id"));
        name.setCellValueFactory(new PropertyValueFactory<>("gruppenname"));
        erzieher.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getErzieherFullName()));
        standort_name.setCellValueFactory(new PropertyValueFactory<>("standortName"));

        // Lade die Gruppe-Daten
        loadGruppeData();
        loadErzieherData();
        loadStandortData();
    }

    private void loadErzieherData() {
        List<Erzieher> erzieherList = ErzieherDAO.getErzieher();
        ObservableList<Erzieher> observableErzieherList = FXCollections.observableArrayList(erzieherList);
        erzieherComboBox.setItems(observableErzieherList);
        erzieherComboBox.setConverter(new StringConverter<Erzieher>() {
            @Override
            public String toString(Erzieher erzieher) {
                return erzieher.getErzieherFullName();
            }

            @Override
            public Erzieher fromString(String string) {
                return erzieherComboBox.getItems().stream().filter(e -> e.getErzieherFullName().equals(string)).findFirst().orElse(null);
            }
        });
    }

    private void loadStandortData() {
        List<Standort> standortList = StandortDAO.getStandorte();
        ObservableList<Standort> observableStandortList = FXCollections.observableArrayList(standortList);
        standortComboBox.setItems(observableStandortList);
        standortComboBox.setConverter(new StringConverter<Standort>() {
            @Override
            public String toString(Standort standort) {
                return standort.getName();
            }

            @Override
            public Standort fromString(String string) {
                return standortComboBox.getItems().stream().filter(s -> s.getName().equals(string)).findFirst().orElse(null);
            }
        });
    }

    private void loadGruppeData() {
        List<Gruppe> gruppen = GruppeDAO.getGruppen();

        if (gruppen == null || gruppen.isEmpty()) {
            System.out.println("❌ Keine Gruppen-Daten gefunden.");
        } else {
            System.out.println("✅ Gruppen-Daten erfolgreich geladen.");

            ObservableList<Gruppe> observableGruppeListe = FXCollections.observableArrayList(gruppen);

            this.gruppeList.setAll(observableGruppeListe);
            gruppeTable.setItems(this.gruppeList);
        }
    }

    @FXML
    private void addGruppe() {
        String name = gruppennameField.getText();
        Standort selectedStandort = standortComboBox.getValue();
        Erzieher selectedErzieher = erzieherComboBox.getValue();

        if (name.isEmpty() || selectedStandort == null || selectedErzieher == null) {
            showAlert(Alert.AlertType.ERROR, "Fehler", "Alle Felder müssen ausgefüllt sein!");
            return;
        }

        Gruppe neueGruppe = new Gruppe(name, selectedErzieher.getErzieher_id(), selectedStandort.getStandort_id());

        boolean success = GruppeDAO.addGruppe(neueGruppe);

        if (success) {
            loadGruppeData();
        } else {
            showAlert(Alert.AlertType.ERROR, "Fehler", "Gruppe konnte nicht hinzugefügt werden.");
        }
    }


    @FXML
    private void editGruppe() {
        // Logik zum Bearbeiten einer Gruppe
    }

    @FXML
    private void deleteGruppe() {
        Gruppe selectedGruppe = gruppeTable.getSelectionModel().getSelectedItem();
        if (selectedGruppe != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Löschen bestätigen");
            alert.setHeaderText("Sind Sie sicher, dass Sie diese Gruppe löschen möchten?");
            alert.setContentText(selectedGruppe.getName());

            if (alert.showAndWait().get() == ButtonType.OK) {
                boolean success = GruppeDAO.deleteGruppe(selectedGruppe.getGruppe_id());
                if (success) {
                    gruppeList.remove(selectedGruppe);
                } else {
                    showAlert(Alert.AlertType.ERROR, "Fehler", "Fehler beim Löschen der Gruppe.");
                }
            }
        } else {
            showAlert(Alert.AlertType.WARNING, "Warnung", "Bitte wählen Sie eine Gruppe aus.");
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