package com.moviemanagerexam.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

public class DatabaseConnectionTest {

    @BeforeEach
    public void setUp() {
        File dbFile = new File("movie_manager_test.db");
        if (dbFile.exists()) {
            dbFile.delete();
        }
    }

    @Test
    public void testDatabaseInitialization() throws SQLException {
        assertDoesNotThrow(() -> {
            Connection conn = DatabaseConnection.getConnection();
            assertNotNull(conn, "Connection should not be null");
            conn.close();
        });
    }

    @Test
    public void testTableCreation() throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            
            stmt.execute("SELECT name FROM sqlite_master WHERE type='table' AND name='categories'");
            ResultSet rs = stmt.getResultSet();
            assertTrue(rs.next(), "Categories table should exist");
            
            stmt.execute("SELECT name FROM sqlite_master WHERE type='table' AND name='movies'");
            rs = stmt.getResultSet();
            assertTrue(rs.next(), "Movies table should exist");
            
            stmt.execute("SELECT name FROM sqlite_master WHERE type='table' AND name='movie_category'");
            rs = stmt.getResultSet();
            assertTrue(rs.next(), "Movie_category table should exist");
        }
    }

    @Test
    public void testDefaultCategoriesInserted() throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            
            stmt.execute("SELECT COUNT(*) as count FROM categories");
            ResultSet rs = stmt.getResultSet();
            rs.next();
            int count = rs.getInt("count");
            assertTrue(count > 0, "Default categories should be inserted");
        }
    }

    @Test
    public void testForeignKeyConstraint() throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            
            stmt.execute("PRAGMA foreign_keys = ON");
            stmt.execute("PRAGMA foreign_keys");
            ResultSet rs = stmt.getResultSet();
            rs.next();
            int status = rs.getInt(1);
            assertEquals(1, status, "Foreign keys should be enabled");
        }
    }
}
