package com.example.kitaverwaltung.controller;

import com.example.kitaverwaltung.dao.KindDAO;
import com.example.kitaverwaltung.model.Kind;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.sql.Date;

public class KindController {

    @FXML private TableView<Kind> kindTable;
    @FXML private TableColumn<Kind, Integer> fk_gruppe_idColumn;
    @FXML private TableColumn<Kind, String> vornameColumn;
    @FXML private TableColumn<Kind, String> nachnameColumn;
    @FXML private TableColumn<Kind, Date> geburtsdatumColumn;
    @FXML private TableColumn<Kind, String> bemerkungColumn;

    private ObservableList<Kind> kinderListe = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Initialisiere die TableColumns
        initializeTableColumns();

        // Lade die Kind-Daten
        loadKindData();
    }

    private void initializeTableColumns() {
        fk_gruppe_idColumn.setCellValueFactory(new PropertyValueFactory<>("fk_gruppe_id"));
        vornameColumn.setCellValueFactory(new PropertyValueFactory<>("vorname"));
        nachnameColumn.setCellValueFactory(new PropertyValueFactory<>("nachname"));
        geburtsdatumColumn.setCellValueFactory(new PropertyValueFactory<>("geburtsdatum"));
        bemerkungColumn.setCellValueFactory(new PropertyValueFactory<>("bemerkung"));
    }

    private void loadKindData() {
        // Kind-D
    }}