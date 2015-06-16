package com.twu.biblioteca;

public class Movie {
    private final String title;
    private final int yearOfRelease;
    private final String director;
    private final double rating;

    public Movie(String title, int yearOfRelease, String director, double rating) {
        this.title = title;
        this.yearOfRelease = yearOfRelease;
        this.director = director;
        this.rating = rating;
    }

    @Override
    public String toString() {
        return title + " " + yearOfRelease + " " + director + " " + rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Movie movie = (Movie) o;

        return !(title != null ? !title.equals(movie.title) : movie.title != null);

    }

    @Override
    public int hashCode() {
        return title != null ? title.hashCode() : 0;
    }
}
