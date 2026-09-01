# message-hub

Message Hub ist ein Spring-Boot-Backend zur Verwaltung von SMS-Nachrichten.  
Das Projekt bildet einen einfachen, aber praxisnahen SMS-Workflow ab: Nachrichten können erstellt, gespeichert, abgerufen und als gesendet oder fehlgeschlagen markiert werden.

## Ziel des Projekts

Das Projekt zeigt den Aufbau einer sauberen REST-API mit Java und Spring Boot.  
Im Mittelpunkt stehen Backend-Struktur, Datenbankanbindung, Validierung, Fehlerbehandlung und ein nachvollziehbarer Nachrichtenstatus.

## Technologien

- Java 21
- Spring Boot
- Spring Web
- Spring Data JPA
- PostgreSQL
- Jakarta Validation
- Lombok
- Maven

## Funktionen

- SMS-Nachricht erstellen
- Alle SMS-Nachrichten abrufen
- Einzelne SMS-Nachricht per ID abrufen
- SMS als gesendet markieren
- SMS als fehlgeschlagen markieren
- Eingaben validieren
- Fehler bei ungültigen Anfragen sauber zurückgeben
- Fehler bei nicht vorhandenen SMS-IDs als 404 zurückgeben

## API-Endpunkte

### Health Check

```http
GET /api/health