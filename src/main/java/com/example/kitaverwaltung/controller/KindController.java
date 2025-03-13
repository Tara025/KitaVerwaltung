package com.example.kitaverwaltung.controller;

import com.example.kitaverwaltung.dao.KindDAO;
import com.example.kitaverwaltung.dao.ElternDAO;
import com.example.kitaverwaltung.dao.GruppeDAO;
import com.example.kitaverwaltung.dao.VerwalterDAO;
import com.example.kitaverwaltung.model.Kind;
import com.example.kitaverwaltung.model.Eltern;
import com.example.kitaverwaltung.model.Gruppe;
import com.example.kitaverwaltung.model.Verwalter;
import com.example.kitaverwaltung.util.TableColumnUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.StringConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

public class KindController {

    @FXML private TableView<Kind> kindTable;
    @FXML private TableColumn<Kind, Integer> gruppe_nameColumn;
    @FXML private TableColumn<Kind, String> vornameColumn;
    @FXML private TableColumn<Kind, String> nachnameColumn;
    @FXML private TableColumn<Kind, LocalDate> geburtsdatumColumn;
    @FXML private TableColumn<Kind, String> elternColumn;
    @FXML private TableColumn<Kind, String> bemerkungColumn;

    @FXML private ComboBox<Gruppe> gruppeComboBox;
    @FXML private TextField vornameField;
    @FXML private TextField nachnameField;
    @FXML private DatePicker geburtsdatumPicker;
    @FXML private ComboBox<Eltern> elternComboBox;
    @FXML private TextField bemerkungField;

    private ObservableList<Kind> kindList = FXCollections.observableArrayList();
    private ObservableList<Gruppe> gruppeList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        gruppe_nameColumn.setCellValueFactory(new PropertyValueFactory<>("gruppe_name"));
        vornameColumn.setCellValueFactory(new PropertyValueFactory<>("vorname"));
        nachnameColumn.setCellValueFactory(new PropertyValueFactory<>("nachname"));
        geburtsdatumColumn.setCellValueFactory(new PropertyValueFactory<>("geburtsdatum"));
        elternColumn.setCellValueFactory(new PropertyValueFactory<>("eltern"));
        bemerkungColumn.setCellValueFactory(new PropertyValueFactory<>("bemerkung"));

        //TableColumnUtil.setDateCellFactory(geburtsdatumColumn, "dd.MM.yyyy");

        loadKindData();
        loadElternData();
        loadGruppeData();
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

    private void loadElternData() {
        List<Eltern> elternList = ElternDAO.getEltern();
        ObservableList<Eltern> observableElternList = FXCollections.observableArrayList(elternList);
        elternComboBox.setItems(observableElternList);
        elternComboBox.setConverter(new StringConverter<Eltern>() {
            @Override
            public String toString(Eltern eltern) {
                if (eltern == null) {
                    return "";
                }
                return eltern.getVorname() + " " + eltern.getNachname();
            }

            @Override
            public Eltern fromString(String string) {
                return elternComboBox.getItems().stream().filter(e -> (e.getVorname() + " " + e.getNachname()).equals(string)).findFirst().orElse(null);
            }
        });
    }

    private void loadGruppeData() {
        List<Gruppe> gruppen = GruppeDAO.getGruppen();
        if (gruppen != null && !gruppen.isEmpty()) {
            gruppeList.setAll(gruppen);
            gruppeComboBox.setItems(gruppeList);
            gruppeComboBox.setConverter(new StringConverter<Gruppe>() {
                @Override
                public String toString(Gruppe gruppe) {
                    return (gruppe == null) ? "" : gruppe.getGruppenname();
                }

                @Override
                public Gruppe fromString(String string) {
                    return gruppeComboBox.getItems().stream().filter(g -> g.getGruppenname().equals(string)).findFirst().orElse(null);
                }
            });
        }
    }



    @FXML
    private void addKind() {
        String vorname = vornameField.getText();
        String nachname = nachnameField.getText();
        LocalDate geburtsdatum = geburtsdatumPicker.getValue();
        String bemerkung = bemerkungField.getText();
        Gruppe selectedGruppe = gruppeComboBox.getValue();
        Eltern selectedEltern = elternComboBox.getValue();

        if (vorname.isEmpty() || nachname.isEmpty() || geburtsdatum == null || selectedEltern == null || selectedGruppe == null) {
            System.out.println("❌ Alle Felder müssen ausgefüllt sein!");
            return;
        }

        // Format the date to yyyy-MM-dd
        String formattedDate = geburtsdatum.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        Kind neuesKind = new Kind(selectedGruppe.getGruppe_id(), vorname, nachname, formattedDate, bemerkung);
        //neuesKind.setEltern(selectedEltern.getVorname() + " " + selectedEltern.getNachname());
        //neuesKind.setFk_gruppe_id(selectedGruppe.getGruppe_id());


        // Add the new Kind to the database
        boolean success = KindDAO.addKind(neuesKind);

        if (success) {
            // Get the newly added Kind's ID
            int kindId = KindDAO.getLastInsertedKindId();

            // Add the connection between Kind and Eltern
            boolean connectionSuccess = KindDAO.addElternKindConnection(kindId, selectedEltern.getEltern_id());

            if (connectionSuccess) {
                System.out.println("✅ Kind erfolgreich hinzugefügt!");
                loadKindData();
            } else {
                System.out.println("❌ Fehler beim Hinzufügen der Verbindung.");
            }
        } else {
            System.out.println("❌ Fehler beim Hinzufügen des Kindes.");
        }
    }


    @FXML
    private void editKind() {
        // Logik zum Bearbeiten eines Kindes
    }

    @FXML
    private void deleteKind() {

        Kind selectedKind = kindTable.getSelectionModel().getSelectedItem();
        if (selectedKind != null) {
            // Confirmation alert
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Löschen bestätigen");
            alert.setHeaderText("Sind Sie sicher, dass Sie dieses Kind löschen möchten?");
            alert.setContentText(selectedKind.getVorname() + " " + selectedKind.getNachname());

            if (alert.showAndWait().get() == ButtonType.OK) {
                boolean success = KindDAO.deleteKind(selectedKind.getKind_id());
                if (success) {
                    kindList.remove(selectedKind);

                } else {
                    showAlert(Alert.AlertType.ERROR, "Fehler", "Fehler beim Löschen des Kindes.");
                }
            }
        } else {
            showAlert(Alert.AlertType.WARNING, "Warnung", "Bitte wählen Sie ein Kind aus.");
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