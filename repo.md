# Movie Manager Exam

A JavaFX desktop application for managing a personal movie collection with SQLite database integration. Users can add, edit, delete, and organize movies by categories, rate them personally, and track viewing history.

## Technologies & Dependencies

- **Java**: 21
- **JavaFX**: 21.0.6 (UI framework)
- **Maven**: 3.x (build & dependency management)
- **SQLite**: Database for data persistence
- **JUnit 5**: 5.12.1 (testing framework)
- **SLF4J**: 2.0.12 (logging)
- **SQL Server JDBC**: 12.6.1 (database driver)
- **UI Libraries**: ControlsFX, FormsFX, ValidatorFX, BootstrapFX, Ikonli, TilesFX, FXGL

## Project Structure

```
src/
├── main/
│   ├── java/com/moviemanagerexam/
│   │   ├── Launcher.java                 # Application entry point
│   │   ├── moviemanagerexam/
│   │   │   └── MovieManager.java         # Main application class
│   │   ├── controller/
│   │   │   └── MainController.java       # JavaFX UI controller (FXML event handlers)
│   │   ├── dao/                          # Data Access Objects
│   │   │   ├── DatabaseConnection.java   # Database connection management
│   │   │   ├── ConnectionManager.java    # Connection pooling
│   │   │   ├── MovieDAO.java             # Movie CRUD operations
│   │   │   ├── CategoryDAO.java          # Category CRUD operations
│   │   │   ├── MovieCategoryDAO.java     # Movie-Category relationship management
│   │   │   └── DatabaseConnectionTest.java
│   │   ├── model/
│   │   │   ├── Movie.java                # Movie entity (id, title, ratings, fileLink, etc.)
│   │   │   └── Category.java             # Category entity (record type)
│   │   └── util/
│   │       └── AlertHelper.java          # UI alert utilities
│   └── resources/
│       ├── com/moviemanagerexam/fxml/
│       │   └── movie-manager.fxml        # Main UI layout
│       ├── css/                          # Stylesheets
│       └── database/
│           └── schema.sql                # Database schema definition
└── test/
    └── java/com/moviemanagerexam/dao/
        └── DatabaseConnectionTest.java   # Unit tests
```

## Core Components

### Models
- **Movie**: Represents a movie with properties including id, title, IMDB rating, personal rating, file path, last viewed date, and associated categories
- **Category**: A record-type class representing movie categories (id and name)

### Data Access Layer (DAO)
- **MovieDAO**: CRUD operations for movies
- **CategoryDAO**: CRUD operations for categories
- **MovieCategoryDAO**: Manages the many-to-many relationship between movies and categories
- **DatabaseConnection**: Handles SQLite connection establishment and management
- **ConnectionManager**: Connection pooling and lifecycle management

### UI Layer
- **MainController**: Handles all UI interactions including:
  - Movie table display with sortable columns (title, IMDB rating, personal rating, categories)
  - Search functionality
  - Category filtering
  - Rating filtering (minimum rating spinner)
  - CRUD operations (Add, Edit, Delete movies)
  - Rating editing
  - Movie playback (opens file with system player)
  - IMDB link integration for adding movies
  - Context-sensitive alerts and validation

## Key Features

- **CRUD Operations**: Create, read, update, and delete movies and categories
- **Movie Metadata**: Store IMDB ratings, personal ratings, file paths, and last viewed timestamps
- **Category Management**: Assign multiple categories to each movie
- **Search & Filtering**: Search by title and filter by category or minimum rating
- **IMDB Integration**: Add movies directly from IMDB links
- **Movie Playback**: Open movies with the system default media player
- **Database Persistence**: SQLite integration for reliable data storage
- **UI Validation**: Input validation and user feedback via alerts

## Database Schema

The application uses SQLite with tables for:
- **movies**: Stores movie metadata
- **categories**: Stores available categories
- **movie_category**: Junction table for many-to-many relationships

## Build & Execution

### Prerequisites
- Java 21 or higher
- Maven 3.6+

### Build
```bash
mvn clean package
```

### Run
```bash
mvn clean javafx:run
```

### Run Tests
```bash
mvn test
```

## Project Statistics
- **Total Lines of Code**: ~926 lines of Java code
- **Main Classes**: 14+ classes across model, DAO, controller, and utility layers

## Configuration

Build configuration in `pom.xml`:
- **Source/Target**: Java 21
- **Main Class**: `com.moviemanagerexam.Launcher`
- **JavaFX Plugin Version**: 0.0.8
- **Maven Compiler Plugin Version**: 3.13.0
