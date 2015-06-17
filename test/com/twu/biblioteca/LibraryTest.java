package com.twu.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class LibraryTest {
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private List<Book> availableBooks;
    private List<Movie> availableMovies;
    private View view;

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outputStream));
        view = new View(null);
        this.availableBooks = new ArrayList<Book>(Arrays.asList(new Book("Algorithms", "Cormen", 2014), new Book("Physics", "Michio", 2009),
                new Book("C", "Dennis", 1982)));
        this.availableMovies = new ArrayList<Movie>();
    }

    @Test
    public void addingANewBookToLibraryShouldDisplayItInAvailableList() {
        Library library = new Library(view, availableBooks, availableMovies);
        library.addBook(new Book("Refactoring", "Martin", 2005));

        library.displayAvailableBooks();
        String actualBooksList = outputStream.toString();

        assertEquals("1 Algorithms Cormen 2014\n2 Physics Michio 2009\n3 C Dennis 1982\n4 Refactoring Martin 2005\n", actualBooksList);
    }

    @Test
    public void addingANewMovieToLibraryShouldDisplayItInAvailableList() {
        Library library = new Library(view, availableBooks, availableMovies);
        library.addMovie(new Movie("Interstellar", 2014, "Nolan", 10.0));

        library.displayAvailableMovies();
        String actualMoviesList = outputStream.toString();

        assertEquals("1 Interstellar 2014 Nolan 10.0\n", actualMoviesList);
    }


    @Test
    public void shouldRemoveABookFromLibrary() {
        Library library = new Library(view, availableBooks, availableMovies);

        library.removeBook("Physics");
        library.displayAvailableBooks();
        String actualOutput = outputStream.toString();

        assertEquals("1 Algorithms Cormen 2014\n2 C Dennis 1982\n", actualOutput);
    }

    @Test
    public void shouldRemoveAMovieFromLibrary() {
        availableMovies.add(new Movie("Interstellar", 2014, "Nolan", 10));
        Library library = new Library(view, availableBooks, availableMovies);
        library.removeMovie("Interstellar");
        library.displayAvailableMovies();
        String actualOutput = outputStream.toString();

        assertEquals("", actualOutput);
    }

    @Test
    public void shouldReturnCorrectBookAsSearchResult() {
        Library library = new Library(null, availableBooks, availableMovies);
        SearchAgent<Book> searchAgent = new SearchAgent<Book>("Physics");
        library.searchBook(searchAgent);

        Book actualResult = searchAgent.result();
        long actualHashCode = actualResult.hashCode();
        long expectedHashCode = "Physics".hashCode();

        assertEquals(expectedHashCode, actualHashCode);
    }

    @After
    public void tearDown() {
        System.setOut(null);
    }
}