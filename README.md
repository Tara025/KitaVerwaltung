# 📚 KitaVerwaltung

Die **KitaVerwaltung** ist eine JavaFX-Anwendung zur Verwaltung von Eltern, Kindern, Erziehern, Verwaltern, Gruppen und Standorten mehrerer Kindertagesstätten.  
Das System nutzt eine **Supabase**-Datenbank für die Speicherung und Verwaltung der Daten.

Dieses Projekt wurde von der **Projektname: Simmy GmbH (Joel, Stephan, Margarita, Peggy)** im Auftrag des ** Projektname: Hühnerstall e.V.** (Kindergarten-Träger mit mehreren Standorten) entwickelt.

---

## 🚀 Features
- **Benutzerverwaltung & Login**:
  - **Verwalter/Admin**: Vollzugriff auf alle Funktionen (Verwalten von Eltern, Kindern, Gruppen, Erziehern etc.).
  - **Erzieher**: Zugriff auf Kinder, eigene Daten und die Anwesenheitsverwaltung (nur für Kinder oder sich selbst).
  
- **Datenverwaltung**:
  - **Eltern-Verwaltung**: Hinzufügen, Bearbeiten und Löschen von Eltern.
  - **Kinder-Verwaltung**: Hinzufügen, Bearbeiten und Löschen der Kinder.
  - **Gruppenverwaltung**: Verwaltung der Gruppen, zu denen Kinder und Erzieher gehören.
  - **Erzieher- & Verwalterverwaltung**: Verwaltung der Daten von Erziehern und Verwaltern.
  - **Standortverwaltung**: Verwaltung der verschiedenen Standorte der Kindertagesstätten.
  - **Anwesenheitsverwaltung**: Erfassung der Anwesenheit von Kindern, Erziehern und Verwaltern.

- **Datenbank-Anbindung**: Verbindung zur Supabase-REST-API für CRUD-Operationen.
- **Datenbank-Views** (also SQL-Views) für die strukturierte und benutzerfreundliche Darstellung von Daten, um die Interaktion zu erleichtern.
    Dadurch erfolgt der Zugriff auf die Daten nicht direkt über die Tabellen, sondern über speziell aufbereitete Datenansichten.
- **Dynamische UI**: JavaFX-basierte Oberfläche mit TableView, TextFields und ChoiceBoxes.
- **Frontend-Design**: Die Benutzeroberfläche wurde mit **FXML im SceneBuilder** erstellt und mit **CSS** designt, um eine ansprechende Benutzererfahrung zu gewährleisten.

---

## 🛠️ Technologie-Stack
- **Frontend**: JavaFX (mit FXML und SceneBuilder für die UI + CSS)
- **Backend**: Java (DAO-Pattern)
- **Datenbank**: Supabase (PostgreSQL)
- **Build-Tool**: Maven/Gradle

---

## 📂 Projektstruktur

- **KitaVerwaltung/**
  - **src/main/java/com/example/kitaverwaltung/**
    - **controller/** - JavaFX-Controller für die UI-Interaktionen
    - **dao/** - Datenzugriffsschicht für die Kommunikation mit der DB
    - **model/** - Datenmodelle (Eltern, Kinder, Erzieher, etc.)
    - **db/** - Datenbankverbindung (Supabase API)
    - **config/** - Konfigurationsdateien (API-Keys etc.)
  - **src/main/resources/fxml/** - FXML-Dateien für die UI
  - **src/main/resources/css/** - Stylesheets
  - **src/main/resources/icons/** - Icons für Buttons
  - **README.md** - Diese Datei
  - **pom.xml** - Maven Build-Datei (bei Gradle: build.gradle)

---

## ⚙️ Installation & Einrichtung

### 1️⃣ Voraussetzungen
- **Java 21** (Empfohlen: [Download JDK](https://adoptium.net/))
- **Maven** oder **Gradle** (je nach Build-System)
- **Supabase-Konto** ([https://supabase.com](https://supabase.com))

### 2️⃣ Projekt klonen
```bash
git clone https://github.com/dein-username/KitaVerwaltung.git
cd KitaVerwaltung

3️⃣ API-Key & Supabase-URL setzen
Erstelle eine Config.java im config/-Ordner mit:

public class Config {
    public static final String SUPABASE_URL = "https://deine-supabase-url.supabase.co";
    public static final String API_KEY = "dein-supabase-api-key";
}

4️⃣ Abhängigkeiten installieren (Maven)

mvn clean install

Falls du Gradle benutzt:
gradle build

5️⃣ Anwendung starten

mvn javafx:run

Oder falls Gradle:
gradle run

📊 Datenbankstruktur
Die Datenbank basiert auf PostgreSQL (Supabase) und enthält folgende Tabellen:

t_eltern (Eltern-Tabelle)
t_kinder (Kinder-Tabelle)
t_erzieher (Erzieher-Tabelle)
t_verwalter (Verwalter-Tabelle)
t_gruppen (Gruppen-Tabelle)
t_standorte (Standort-Tabelle)
t_anwesenheit (Anwesenheits-Tabelle)
Zusätzlich gibt es Views zur vereinfachten Datenanzeige.

📌 Rollen & Berechtigungen
Rolle	Berechtigungen
Admin	Vollzugriff auf alle Tabellen
Verwalter	Verwaltung von Eltern, Kindern, Gruppen, Erziehern & Standorten
Erzieher	Zugriff auf Kinder, eigene Daten & Anwesenheitsverwaltung (nur Kinder/sich selbst)

🛠️ Beispiele für wichtige Methoden
📌 ElternDAO
getEltern() → Holt Eltern aus der View (inkl. Kindern).
addEltern(Eltern e) → Fügt Eltern in die Tabelle ein.
editEltern(Eltern e) → Bearbeitet Eltern in der Tabelle.
deleteEltern(int elternId) → Löscht Eltern (Soft-Delete).

📌 ElternController
loadElternData() → Lädt Eltern in die TableView.
addEltern() → Fügt neue Eltern hinzu.
editEltern() → Bearbeitet vorhandene Eltern.
deleteSelectedEltern() → Löscht einen Elternteil.

🔗 Weiterentwicklung
✅ Geplante Features:

Im weiteren ist geplant, alle Zahlungen (hauptsächlich Beiträge) der Eltern in der Datenbank zu erfassen und
ein geeignetes Mahnwesen anzuschließen.

Eltern sollen dann auch via E-Mail-Benachrichtigungssystem über Zahlungsrückstände informiert werden.


📧 Kontakt & Support
Bei Fragen oder Fehlern gerne über GitHub kontakt aufnehmen.

🐙 GitHub Issues: https://github.com/dein-username/KitaVerwaltung/issues

Mitwirkende:
Joel
Stephan
Margarita
Peggy



