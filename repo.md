# Movie Manager Exam

A JavaFX application for managing a movie collection, using SQLite for data persistence.

## Technologies
- **Java**: 25
- **JavaFX**: 21.0.6
- **Maven**: Project management and build tool
- **SQLite**: Database for storing movie and category information

## Project Structure
- `src/main/java/com/moviemanagerexam`: Main source code
    - `controller/`: JavaFX controllers (e.g., `MainController.java`)
    - `dao/`: Data Access Objects for database operations (`MovieDAO.java`, `CategoryDAO.java`, etc.)
    - `model/`: Domain models (`Movie.java`, `Category.java`)
    - `util/`: Utility classes (`AlertHelper.java`, `FileValidator.java`)
    - `MovieManager.java`: Main application class
    - `Launcher.java`: Application entry point
- `src/main/resources`: Application resources
    - `com/moviemanagerexam/fxml/`: FXML layout files
    - `CSS/`: Stylesheets
    - `database/`: Database schema scripts

## Key Features
- CRUD operations for movies and categories
- SQLite database integration
- JavaFX UI with FXML

## How to Run
To run the application, use the following Maven command:
```bash
mvn clean javafx:run
```
