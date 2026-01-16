# Movie Manager - Build and Run Instructions

## ⚠️ Important: JavaFX Module Path Requirements

JavaFX requires special module path configuration to run. **Do NOT try to run `java -jar`** as it will fail with "JavaFX runtime components are missing".

## Running the Application

### ✅ Option 1: Using the run script (RECOMMENDED)
```bash
./run.sh
```

### ✅ Option 2: Using Maven directly
```bash
./mvnw javafx:run
```

### ✅ Option 3: From IDE
In IntelliJ IDEA or other IDEs, run the `Launcher` class directly with Maven profile support enabled.

## Build Commands

### Compile only
```bash
./mvnw clean compile
```

### Compile and run tests
```bash
./mvnw clean test
```

### Build JAR (without running)
```bash
./mvnw clean package
```

## ❌ What NOT to Do

- ❌ `java -jar target/movie-manager.jar` (will fail - missing JavaFX runtime)
- ❌ `java -cp target/lib/* com.moviemanagerexam.Launcher` (will fail - missing module path)
- ❌ Run as plain Java without Maven - use Maven with `javafx:run` goal

## System Requirements

- **Java**: 21 or higher (required for JavaFX 21.0.6)
- **Maven**: 3.6 or higher
- **Database**: SQL Server with VPN access to EASV-DB4
- **Database Configuration**: Edit `src/main/java/com/moviemanagerexam/dal/DatabaseConnection.java` to change credentials

## Architecture

- **Framework**: JavaFX with FXML
- **Build System**: Maven with JavaFX Maven Plugin
- **Business Logic**: MovieService
- **Database**: SQL Server (movies, categories, movie_category tables)
- **Layers**: Launcher → MovieManager → MainController → MovieService → DAO → DatabaseConnection
