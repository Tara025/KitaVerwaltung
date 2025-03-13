package com.example.kitaverwaltung.controller;

import com.example.kitaverwaltung.dao.AnwesenheitDAO;
import com.example.kitaverwaltung.model.Anwesenheit;
import com.example.kitaverwaltung.util.TableColumnUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class AnwesenheitController {

    @FXML private ComboBox<String> personTypeComboBox;
    @FXML private ComboBox<String> personNameComboBox;
    @FXML private TableView<Anwesenheit> anwesenheitTable;
    @FXML private TableColumn<Anwesenheit, Date> dateColumn;
    @FXML private TableColumn<Anwesenheit, String> statusColumn;
    @FXML private Button btnEdit;
    @FXML private Button btnAdd;
    @FXML private Button btnSave;
    @FXML private HBox newEntryBox;
    @FXML private TextField arbeitstagField;
    @FXML private ComboBox<String> statusComboBox;

    private final ObservableList<Anwesenheit> anwesenheitList = FXCollections.observableArrayList();

    // Add a flag to indicate if we are editing
    private boolean isEditing = false;

    @FXML
    public void initialize() {
        personTypeComboBox.getItems().addAll("Verwalter", "Erzieher", "Kinder");
        personTypeComboBox.setOnAction(e -> updatePersonNameComboBox());

        dateColumn.setCellValueFactory(new PropertyValueFactory<>("datum"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        personNameComboBox.setOnAction(e -> loadAnwesenheitData());

        //Utility methode zum formatieren des datums
        TableColumnUtil.setDateCellFactory(dateColumn, "dd.MM.yyyy");
    }

    @FXML
    private void handleAddButton() {
        if (personTypeComboBox.getValue() != null && personNameComboBox.getValue() != null) {
            newEntryBox.setVisible(true);
            btnSave.setVisible(true);
            arbeitstagField.setEditable(true); // Make the field editable
        }
        else showAlert(Alert.AlertType.ERROR, "Fehler", "Bitte wählen Sie zuerst eine Personenart und einen Namen aus.");
    }

    @FXML
    private void handleSaveButton() {
        String dateInput = arbeitstagField.getText();
        String status = statusComboBox.getValue();

        // Validate date format
        if (!isValidDateFormat(dateInput, "dd.MM.yyyy")) {
            showAlert(Alert.AlertType.ERROR, "Ungültiges Datum", "Bitte geben Sie das Datum im Format TT.MM.JJJJ ein.");
            return;
        }

        // Check if status is selected
        if (status == null || status.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Kein Status ausgewählt", "Bitte wählen Sie einen Status aus.");
            return;
        }

        // Convert date format
        String formattedDate = convertDateFormat(dateInput, "dd.MM.yyyy", "yyyy-MM-dd");

        // Determine the status ID
        int statusId = status.equals("anwesend") ? 1 : 2;

        if (isEditing) {
            // Update the existing entry
            Anwesenheit selectedAnwesenheit = anwesenheitTable.getSelectionModel().getSelectedItem();
            if (selectedAnwesenheit != null) {
                String personType = personTypeComboBox.getValue();
                String personName = personNameComboBox.getValue();
                int arbeitstageId = AnwesenheitDAO.getArbeitstageIdByDate(formattedDate);

                if (arbeitstageId != -1) {
                    boolean success = AnwesenheitDAO.updateStatus(personType, personName, arbeitstageId, statusId);
                    if (success) {
                        showAlert(Alert.AlertType.INFORMATION, "Erfolg", "Der Status wurde erfolgreich aktualisiert.");
                        loadAnwesenheitData(); // Reload data and refresh the table
                        newEntryBox.setVisible(false);
                        btnSave.setVisible(false);
                        isEditing = false; // Reset the flag
                    } else {
                        showAlert(Alert.AlertType.ERROR, "Fehler", "Fehler beim Aktualisieren des Status.");
                    }
                } else {
                    showAlert(Alert.AlertType.ERROR, "Fehler", "Fehler beim Abrufen der Arbeitstage-ID.");
                }
            }
        } else {
            // Insert a new entry
            int arbeitstageId = AnwesenheitDAO.insertArbeitstag(formattedDate);  // gibt die von der Datenbank generierte ID zurück
            if (arbeitstageId != -1) {
                String personType = personTypeComboBox.getValue();
                String personName = personNameComboBox.getValue();
                boolean success = AnwesenheitDAO.insertStatus(personType, personName, arbeitstageId, statusId);
                if (success) {
                    showAlert(Alert.AlertType.INFORMATION, "Erfolg", "Der Arbeitstag und Status wurden erfolgreich gespeichert.");
                    loadAnwesenheitData(); // Reload data and refresh the table
                    newEntryBox.setVisible(false);
                    btnSave.setVisible(false);
                } else {
                    showAlert(Alert.AlertType.ERROR, "Fehler", "Fehler beim Speichern des Status.");
                }
            } else {
                showAlert(Alert.AlertType.ERROR, "Fehler", "Fehler beim Speichern des Arbeitstags.");
            }
        }
    }

    @FXML
    private void handleEditButton() {
        Anwesenheit selectedAnwesenheit = anwesenheitTable.getSelectionModel().getSelectedItem();
        if (selectedAnwesenheit != null) {
            // Populate the arbeitstagField with the current date
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
            arbeitstagField.setText(sdf.format(selectedAnwesenheit.getDatum()));
            arbeitstagField.setEditable(false); // Make the field non-editable

            // Populate the statusComboBox with the current status
            statusComboBox.setValue(selectedAnwesenheit.getStatus());

            newEntryBox.setVisible(true);
            btnSave.setVisible(true);
            isEditing = true; // Set the flag to true
        } else {
            showAlert(Alert.AlertType.WARNING, "Warnung", "Bitte wählen Sie einen Eintrag aus.");
        }
    }

    private boolean isValidDateFormat(String date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        sdf.setLenient(false);
        try {
            sdf.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    private String convertDateFormat(String date, String fromFormat, String toFormat) {
        SimpleDateFormat sdfFrom = new SimpleDateFormat(fromFormat);
        SimpleDateFormat sdfTo = new SimpleDateFormat(toFormat);
        try {
            Date parsedDate = sdfFrom.parse(date);
            return sdfTo.format(parsedDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void updatePersonNameComboBox() {
        String selectedType = personTypeComboBox.getValue();
        List<String> names = AnwesenheitDAO.getNamesByType(selectedType);
        personNameComboBox.setItems(FXCollections.observableArrayList(names));
    }

    private void loadAnwesenheitData() {
        String selectedType = personTypeComboBox.getValue();
        String selectedName = personNameComboBox.getValue();
        if (selectedType != null && selectedName != null) {
            List<Anwesenheit> anwesenheitList = AnwesenheitDAO.getAnwesenheitByTypeAndName(selectedType, selectedName);
            this.anwesenheitList.setAll(anwesenheitList);
            anwesenheitTable.setItems(this.anwesenheitList);
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