package com.example.kitaverwaltung.controller;

import com.example.kitaverwaltung.dao.AnwesenheitDAO;
import com.example.kitaverwaltung.model.Anwesenheit;
import com.example.kitaverwaltung.util.TableColumnUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.Date;
import java.util.List;

public class AnwesenheitController {

    @FXML private ComboBox<String> personTypeComboBox;
    @FXML private ComboBox<String> personNameComboBox;
    @FXML private TableView<Anwesenheit> anwesenheitTable;
    @FXML private TableColumn<Anwesenheit, Date> dateColumn;
    @FXML private TableColumn<Anwesenheit, String> statusColumn;

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
}