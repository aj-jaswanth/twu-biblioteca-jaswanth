package com.twu.biblioteca;

import java.util.List;

public class Library {

    private View view;
    private List<Book> availableBooks;
    private List<Movie> availableMovies;

    public Library(View view, List<Book> availableBooks, List<Movie> availableMovies) {
        this.view = view;
        this.availableBooks = availableBooks;
        this.availableMovies = availableMovies;
    }

    public void addBook(Book book) {
        availableBooks.add(book);
    }

    public void removeBook(String bookTitle) {
        int index = 0;
        for (; index < availableBooks.size(); index++)
            if (availableBooks.get(index).hashCode() == bookTitle.hashCode())
                break;
        availableBooks.remove(index);
    }

    public void searchBook(SearchAgent searchAgent) {
        for (Book availableBook : availableBooks) {
            if (availableBook.hashCode() == searchAgent.hashCode()) {
                searchAgent.add(availableBook);
                break;
            }
        }
    }

    public void displayAvailableBooks() {
        for (int x = 0; x < availableBooks.size(); x++)
            view.display((x + 1) + " " + availableBooks.get(x));
    }

    public void displayAvailableMovies() {
        for (int x = 0; x < availableMovies.size(); x++)
            view.display((x + 1) + " " + availableMovies.get(x));
    }

    public void searchMovie(SearchAgent<Movie> searchAgent) {
        for (Movie availableMovie : availableMovies) {
            if (availableMovie.hashCode() == searchAgent.hashCode()) {
                searchAgent.add(availableMovie);
                break;
            }
        }
    }

    public void removeMovie(String movieTitle) {
        int index = 0;
        for (; index < availableMovies.size(); index++)
            if (availableMovies.get(index).hashCode() == movieTitle.hashCode())
                break;
        availableMovies.remove(index);
    }
}