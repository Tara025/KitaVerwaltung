package com.example.kitaverwaltung.controller;

import com.example.kitaverwaltung.model.Erzieher;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;
import javafx.util.Duration;

public class DashboardController {

    // UI-Elemente
    @FXML private Button btnEltern, btnVerwalter, btnErzieher, btnKinder, btnStandorte, btnGruppe, btnLogout;
    @FXML private StackPane mainContent;
    @FXML private Label welcomeLabel;



    // ----Live-Uhr-----
    private String currentUserName;
    private final DateTimeFormatter dateTimeFormatter =
            DateTimeFormatter.ofPattern("dd MMMM yyyy, HH:mm:ss 'Uhr'", Locale.getDefault());
    private Timeline liveClock;

    public void setWelcomeMessage(String userName) {
        this.currentUserName = userName; // Benutzernamen speichern
        startLiveClock(); // Live-Uhr starten
    }

    // Live-Uhr initialisieren
    private void startLiveClock() {
        // Sofortige erste Aktualisierung
        updateWelcomeMessage();

        // Timer für sekündliche Updates
        liveClock = new Timeline(new KeyFrame(Duration.seconds(1), event -> updateWelcomeMessage()));
        liveClock.setCycleCount(Timeline.INDEFINITE);
        liveClock.play();
    }

    // Willkommen Nachricht aktualisieren
    private void updateWelcomeMessage() {
        LocalDateTime now = LocalDateTime.now();
        String dayOfWeek = now.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.getDefault());
        String formattedDateTime = now.format(dateTimeFormatter);

        String message = String.format(
                "Hallo %s. Heute ist %s, der %s",
                currentUserName,
                dayOfWeek,
                formattedDateTime
        );

        if (welcomeLabel != null) {
            welcomeLabel.setText(message);
        } else {
            System.out.println("Fehler: welcomeLabel ist null!");
        }
    }



    // Benutzerverwaltung
    private static Object currentUser;

    public static void setCurrentUser(Object user) {
        currentUser = user;
    }

    // -- Initialisierung --

    @FXML
    public void initialize() {
        /*clearMainContent();*/

        // Container an Fenstergröße anpassen
        mainContent.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);


        if (currentUser != null) {
            if (currentUser instanceof Erzieher) {
                btnVerwalter.setDisable(true);
                btnErzieher.setDisable(true);
                btnStandorte.setDisable(true);
            }
        }

        btnEltern.setOnAction(e ->loadElternTable());
        btnVerwalter.setOnAction(e -> loadVerwalterTable());
        btnErzieher.setOnAction(e -> loadErzieherTable());
        btnKinder.setOnAction(e -> loadKinderTable());
        btnStandorte.setOnAction(e -> loadStandorteTable());
        btnGruppe.setOnAction(e -> loadGruppeTable());
        btnLogout.setOnAction(e -> handleLogout());
    }


    //-- Logout --
    @FXML
    private void handleLogout() {
        // Timer stoppen
        if (liveClock != null) {
            liveClock.stop();
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/kitaverwaltung/login.fxml"));
            Pane pane = loader.load();
            Stage stage = (Stage) mainContent.getScene().getWindow();

            // Fenstergröße zurücksetzen (wie beim Login)
            stage.setWidth(800);
            stage.setHeight(600);
            stage.centerOnScreen(); // Neu zentrieren

            // Set the scene to the login pane
            stage.getScene().setRoot(pane);
            stage.setTitle("Kitaverwaltung");


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void clearMainContent() {
        mainContent.getChildren().clear();
    }

    private void loadFXML(String fxmlFile) {
        clearMainContent();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Pane pane = loader.load();
            mainContent.getChildren().setAll(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void loadVerwalterTable() {
        loadFXML("/com/example/kitaverwaltung/verwalter.fxml");
    }

    @FXML
    private void loadErzieherTable() {
        loadFXML("/com/example/kitaverwaltung/erzieher.fxml");
    }

    @FXML
    private void loadKinderTable() {
        loadFXML("/com/example/kitaverwaltung/kind.fxml");
    }

    @FXML
    private void loadStandorteTable() {
        loadFXML("/com/example/kitaverwaltung/standort.fxml");
    }

    @FXML
    private void loadGruppeTable() {
        loadFXML("/com/example/kitaverwaltung/gruppe.fxml");
    }

    @FXML
    private void loadElternTable() { loadFXML("/com/example/kitaverwaltung/eltern.fxml"); }
}
