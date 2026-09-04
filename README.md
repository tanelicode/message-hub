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

## Lokales Setup

### Voraussetzungen

- Java 21
- Docker Desktop
- Git

### Projekt starten mit Docker

1. Repository klonen:

```bash
git clone https://github.com/tanelicode/message-hub.git
cd message-hub
```

2. `.env` Datei aus Beispiel erstellen:

```bash
cp .env.example .env
```

Unter Windows PowerShell:

```powershell
Copy-Item .env.example .env
```

3. Anwendung mit Docker starten:

```bash
docker compose up --build
```

4. Health Check öffnen:

```text
http://localhost:8080/api/health
```

5. Swagger UI öffnen:

```text
http://localhost:8080/swagger-ui/index.html
```

### Anwendung stoppen

```bash
docker compose down
```

## Tests ausführen

Unter Windows PowerShell:

```powershell
.\mvnw.cmd test
```

Unter Linux/macOS:

```bash
./mvnw test
```

## Continuous Integration

Das Projekt nutzt GitHub Actions.  
Bei jedem Push auf den `main` Branch werden die Tests automatisch ausgeführt.

### Health Check

```http
GET /api/health