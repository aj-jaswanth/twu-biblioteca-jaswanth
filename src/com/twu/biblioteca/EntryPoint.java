package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static com.twu.biblioteca.Messages.*;

public class EntryPoint {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        View view = new View(scanner);
        MainMenu mainMenu = new MainMenu(new String[]{LIST_BOOKS, LIST_MOVIES,
                CHECK_OUT_BOOK, CHECK_OUT_MOVIE, RETURN_BOOK, RETURN_MOVIE, QUIT}, view);
        List<Book> availableBooks = new ArrayList<Book>(Arrays.asList(new Book("Algorithms", "Cormen", 2014), new Book("Physics", "Michio", 2009),
                new Book("C", "Dennis", 1982)));
        List<Movie> availableMovies = new ArrayList<Movie>(Arrays.asList(new Movie("Interstellar", 2014, "Christopher Nolan", 10.0),
                new Movie("Die Hard 4", 2009, "Bruce Wills", 10.0),
                new Movie("The Pursuit of Happyness", 2003, "Will Smith", 10.0)));
        Library library = new Library(view, availableBooks, availableMovies);
        Librarian librarian = new Librarian(new ArrayList<Book>(), new ArrayList<Movie>(), library, view);
        App app = new App(mainMenu, library, librarian, view);
        app.start();
    }
}
