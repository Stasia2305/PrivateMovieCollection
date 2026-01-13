package com.moviemanagerexam.dao;

import com.moviemanagerexam.model.Movie;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovieDAO {

    public List<Movie> getAllMovies() throws SQLException {
        List<Movie> movies = new ArrayList<>();
        String sql = "SELECT * FROM movies";
        MovieCategoryDAO movieCategoryDAO = new MovieCategoryDAO();

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Movie movie = new Movie(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getDouble("imdb_rating"),
                        rs.getInt("personal_rating"),
                        rs.getString("file_path"),
                        rs.getString("last_view")
                );
                movie.setCategories(movieCategoryDAO.getCategoriesForMovie(movie.getId()));
                movies.add(movie);
            }
        }
        return movies;
    }

    public void updateLastView(int id) throws SQLException {
        String sql = "UPDATE movies SET last_view = CURRENT_TIMESTAMP WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }

    public void deleteMovie(int id) throws SQLException {
        String sql = "DELETE FROM movies WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }

    public void addMovie(Movie movie) throws SQLException {
        String sql = "INSERT INTO movies (title, imdb_rating, personal_rating, file_path) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, movie.getTitle());
            pstmt.setDouble(2, movie.getImdbRating());
            pstmt.setInt(3, movie.getPersonalRating());
            pstmt.setString(4, movie.getFileLink());
            pstmt.executeUpdate();

            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    movie.setId(rs.getInt(1));
                }
            }
        }
    }

    public void updateMovie(Movie movie) throws SQLException {
        String sql = "UPDATE movies SET title = ?, personal_rating = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, movie.getTitle());
            pstmt.setInt(2, movie.getPersonalRating());
            pstmt.setInt(3, movie.getId());
            pstmt.executeUpdate();
        }
    }
}
