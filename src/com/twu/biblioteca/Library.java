package com.twu.biblioteca;

import java.util.ArrayList;

public class Library {

    private ArrayList<Book> availableBooks = new ArrayList<Book>();
    private View view;
    private ArrayList<Movie> availableMovies = new ArrayList<Movie>();

    public Library(View view) {
        this.view = view;
        availableBooks.add(new Book("Algorithms", "Cormen", 2014));
        availableBooks.add(new Book("Physics", "Michio", 2009));
        availableBooks.add(new Book("C", "Dennis", 1982));
        availableMovies.add(new Movie("Interstellar", 2014, "Christopher Nolan", 10.0));
        availableMovies.add(new Movie("Die Hard 4", 2009, "Bruce Wills", 10.0));
        availableMovies.add(new Movie("The Pursuit Of Happyness", 2003, "Will Smith", 10.0));
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
}