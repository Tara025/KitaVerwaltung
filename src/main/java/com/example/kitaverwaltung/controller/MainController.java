package com.example.kitaverwaltung.controller;

import com.example.kitaverwaltung.dao.VerwalterDAO;
import com.example.kitaverwaltung.model.Verwalter;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MainController {

    @FXML private TableView<Verwalter> verwalterTable;
    @FXML private TableColumn<Verwalter, String> nameColumn;

    private ObservableList<Verwalter> verwalterListe = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        // Verwalter-Daten von der DAO abrufen
        verwalterListe.setAll(VerwalterDAO.getVerwalter());

        // Die geladenen Daten in das TableView setzen
        verwalterTable.setItems(verwalterListe);
    }
}
