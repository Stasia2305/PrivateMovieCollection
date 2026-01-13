package com.moviemanagerexam.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {

    private static final String URL = "jdbc:sqlite:movie_manager.db";
    private static boolean initialized = false;

    static {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            System.err.println("Failed to load SQLite JDBC driver: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public DatabaseConnection() {
    }

    public static Connection getConnection() throws SQLException {
        if (!initialized) {
            initializeDatabase();
        }
        Connection conn = DriverManager.getConnection(URL);
        try (Statement stmt = conn.createStatement()) {
            stmt.execute("PRAGMA foreign_keys = ON");
        }
        return conn;
    }

    private static synchronized void initializeDatabase() throws SQLException {
        if (initialized) {
            return;
        }

        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new SQLException("SQLite JDBC driver not found", e);
        }

        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement()) {

            stmt.execute("PRAGMA foreign_keys = ON");
            
            stmt.execute("CREATE TABLE IF NOT EXISTS categories (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "name TEXT NOT NULL UNIQUE)");

            stmt.execute("CREATE TABLE IF NOT EXISTS movies (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "title TEXT NOT NULL," +
                    "imdb_rating REAL," +
                    "personal_rating INTEGER," +
                    "file_path TEXT NOT NULL," +
                    "last_view TEXT)");

            stmt.execute("CREATE TABLE IF NOT EXISTS movie_category (" +
                    "movie_id INTEGER NOT NULL," +
                    "category_id INTEGER NOT NULL," +
                    "PRIMARY KEY (movie_id, category_id)," +
                    "FOREIGN KEY (movie_id) REFERENCES movies(id) ON DELETE CASCADE," +
                    "FOREIGN KEY (category_id) REFERENCES categories(id) ON DELETE CASCADE)");

            String[] defaultCategories = {
                "Action", "Adventure", "Animation", "Comedy", "Crime", 
                "Documentary", "Drama", "Family", "Fantasy", "History", 
                "Horror", "Music", "Mystery", "Romance", "Science Fiction", 
                "TV Movie", "Thriller", "War", "Western"
            };
            for (String cat : defaultCategories) {
                try (var pstmt = conn.prepareStatement("INSERT OR IGNORE INTO categories (name) VALUES (?)")) {
                    pstmt.setString(1, cat);
                    pstmt.executeUpdate();
                }
            }
            
            initialized = true;
        } catch (SQLException e) {
            throw new SQLException("Failed to initialize database: " + e.getMessage(), e);
        }
    }
}
