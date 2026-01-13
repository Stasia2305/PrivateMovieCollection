package com.moviemanagerexam.model;

import java.util.ArrayList;
import java.util.List;

public class Movie {
    private int id;
    private String title;
    private final double imdbRating;
    private int personalRating;
    private final String fileLink;
    private String lastView;
    private List<String> categories;

    public Movie(int id, String title, double imdbRating, int personalRating, String fileLink, String lastView) {
        this.id = id;
        this.title = title;
        this.imdbRating = imdbRating;
        this.personalRating = personalRating;
        this.fileLink = fileLink;
        this.lastView = lastView;
        this.categories = new ArrayList<>();
    }

    public Movie(String title, double imdbRating, int personalRating, String fileLink) {
        this.title = title;
        this.imdbRating = imdbRating;
        this.personalRating = personalRating;
        this.fileLink = fileLink;
        this.categories = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getImdbRating() {
        return imdbRating;
    }

    public int getPersonalRating() {
        return personalRating;
    }

    public void setPersonalRating(int personalRating) {
        this.personalRating = personalRating;
    }

    public String getFileLink() {
        return fileLink;
    }

    public String getLastView() {
        return lastView;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    @Override
    public String toString() {
        return title;
    }
}
