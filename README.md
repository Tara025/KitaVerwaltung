# KitaVerwaltung

Die **KitaVerwaltung** ist ein externes Projekt, das der **Simmy GmbH** (Joel, Stephan, Margarita, Peggy) vom **Hühnerstall e.V.** (Kindergarten-Träger mit mehreren Standorten) in Auftrag gegeben wurde. Ziel ist die Entwicklung einer Verwaltungssoftware, die mit Hilfe einer **Supabase-Datenbank** die Verwaltung von **Kindern, Eltern, Verwaltern, Erziehern, Gruppen und Standorten** ermöglicht. Zudem wird die **Anwesenheit** von Kindern, Erziehern und Verwaltern erfasst und regelmäßig aktualisiert.

## Technologie-Stack

- **Frontend**: JavaFX
- **Backend**: Java (DAO-Pattern)
- **Datenbank**: Supabase (PostgreSQL)
- **Build-Tool**: Maven

## Features

### Benutzerverwaltung & Login

Das System verfügt über ein **Login-System** mit unterschiedlichen Berechtigungen:

- **Verwalter/Admin**: Vollzugriff auf alle Funktionen (Verwalten von Eltern, Kindern, Gruppen, Erziehern etc.).
- **Erzieher**: Zugriff auf Kinder, eigene Daten und die Anwesenheitsverwaltung (nur für Kinder oder sich selbst).

### Datenverwaltung

Das System arbeitet mit **Views** für die **Anzeige** von Daten und **Tables** für das **Hinzufügen und Bearbeiten** von Datensätzen.

- Elternverwaltung
- Kinderverwaltung
- Gruppenverwaltung
- Erzieher- & Verwalterverwaltung
- Standortverwaltung
- Anwesenheitsverwaltung

## Setup & Installation

1. **Projekt klonen**:
   ```sh
   git clone https://github.com/dein-repo/KitaVerwaltung.git
   ```
2. **Abhängigkeiten installieren**:
   ```sh
   mvn clean install
   ```
3. **Supabase konfigurieren**:
   - `.env` Datei mit `SUPABASE_URL` und `API_KEY` erstellen
4. **Anwendung starten**:
   ```sh
   mvn javafx:run
   ```

## Datenbankstruktur

Die Datenbank basiert auf PostgreSQL (Supabase) und enthält folgende Tabellen:

- `t_eltern` (Eltern-Tabelle)
- `t_kinder` (Kinder-Tabelle)
- `t_erzieher` (Erzieher-Tabelle)
- `t_verwalter` (Verwalter-Tabelle)
- `t_gruppen` (Gruppen-Tabelle)
- `t_standorte` (Standort-Tabelle)
- `t_anwesenheit` (Anwesenheits-Tabelle)

Zusätzlich gibt es **Views** zur vereinfachten Datenanzeige.

## Rollen & Berechtigungen

| Rolle         | Berechtigungen                                                                     |
| ------------- | ---------------------------------------------------------------------------------- |
| **Admin**     | Vollzugriff auf alle Tabellen                                                      |
| **Verwalter** | Verwaltung von Eltern, Kindern, Gruppen, Erziehern & Standorten                    |
| **Erzieher**  | Zugriff auf Kinder, eigene Daten & Anwesenheitsverwaltung (nur Kinder/sich selbst) |

## Mitwirkende

- Joel
- Stephan
- Margarita
- Peggy

## Lizenz

MIT License

---


