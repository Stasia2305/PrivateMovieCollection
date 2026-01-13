package com.moviemanagerexam.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovieCategoryDAO {

    public List<String> getCategoriesForMovie(int movieId) throws SQLException {
        List<String> categories = new ArrayList<>();
        String sql = "SELECT c.name FROM categories c JOIN movie_category mc ON c.id = mc.category_id WHERE mc.movie_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, movieId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    categories.add(rs.getString("name"));
                }
            }
        }
        return categories;
    }

    public void addMovieToCategory(int movieId, int categoryId) throws SQLException {
        String sql = "INSERT OR IGNORE INTO movie_category (movie_id, category_id) VALUES (?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, movieId);
            pstmt.setInt(2, categoryId);
            pstmt.executeUpdate();
        }
    }

}
