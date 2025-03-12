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
        }
        else showAlert(Alert.AlertType.ERROR, "Fehler", "Bitte wählen Sie zuerst einen Personentyp und eine Person aus.");
    }

    @FXML
    private void handleSaveButton() {
        String dateInput = arbeitstagField.getText();
        String status = statusComboBox.getValue();

        // Validate date format
        if (!isValidDateFormat(dateInput, "dd.MM.yyyy")) {
            showAlert(Alert.AlertType.ERROR, "Ungültiges Datum", "Bitte geben Sie das Datum im Format tt.mm.jjjj ein.");
            return;
        }

        // Check if status is selected
        if (status == null || status.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Kein Status ausgewählt", "Bitte wählen Sie einen Status aus.");
            return;
        }

        // Convert date format
        String formattedDate = convertDateFormat(dateInput, "dd.MM.yyyy", "yyyy-MM-dd");

        // Insert into database
        int arbeitstageId = AnwesenheitDAO.insertArbeitstag(formattedDate);
        //System.out.println("arbeitstage_id = " + arbeitstageId);

        if (arbeitstageId != -1) {
            // Get the selected person type and name
            String personType = personTypeComboBox.getValue();
            String personName = personNameComboBox.getValue();
            //System.out.println("personType = " + personType);
            //System.out.println("personName = " + personName);

            // Determine the status ID
            int statusId = status.equals("anwesend") ? 1 : 2;
            //System.out.println("statusId = " + statusId);

            // Insert the status into the appropriate table
            boolean success = AnwesenheitDAO.insertStatus(personType, personName, arbeitstageId, statusId);
            if (success) {
                showAlert(Alert.AlertType.INFORMATION, "Erfolg", "Der Arbeitstag und Status wurden erfolgreich gespeichert.");
                loadAnwesenheitData(); // Reload data and refresh the table
                // Eingabe-Felder verstecken
                newEntryBox.setVisible(false);
                btnSave.setVisible(false);
            } else {
                showAlert(Alert.AlertType.ERROR, "Fehler", "Fehler beim Speichern des Status.");
            }
        } else {
            showAlert(Alert.AlertType.ERROR, "Fehler", "Fehler beim Speichern des Arbeitstags.");
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