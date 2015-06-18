package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class LibrarianTest {

    private ByteArrayOutputStream outputContent;
    private View view;
    private List<Book> availableBooks = new ArrayList<Book>(Arrays.asList(new Book("Algorithms", "Cormen", 2014), new Book("Physics", "Michio", 2009),
            new Book("C", "Dennis", 1982)));
    private List<Movie> availableMovies = new ArrayList<Movie>(Arrays.asList(new Movie("Interstellar", 2014, "Christopher Nolan", 10.0),
            new Movie("Die Hard 4", 2009, "Bruce Wills", 10.0),
            new Movie("The Pursuit of Happyness", 2003, "Will Smith", 10.0)));
    private Authenticator authenticator;
    private CheckOutRegister checkOutRegister;

    @Before
    public void setUp() {
        outputContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputContent));
        view = new View(null);
        this.authenticator = new Authenticator(null);
        this.checkOutRegister = new CheckOutRegister();
    }

    @Test
    public void shouldCheckOutTheGivenBook() {
        Library library = new Library(view, availableBooks, availableMovies);
        Librarian librarian = new Librarian(new ArrayList<Book>(), null, library, view, checkOutRegister, authenticator);

        librarian.checkOutBook("Physics");
        library.displayAvailableBooks();
        String actualOutput = outputContent.toString();
        String expectedOutput = "Thank you! Enjoy the book\n1 Algorithms Cormen 2014\n2 C Dennis 1982\n";

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void checkedOutBookShouldNotBeAvailableInLibrary() {
        Library library = new Library(view, availableBooks, availableMovies);
        Librarian librarian = new Librarian(new ArrayList<Book>(), null, library, view, checkOutRegister, authenticator);
        librarian.checkOutBook("Algorithms");
        library.displayAvailableBooks();
        String actualBooksList = outputContent.toString();

        assertEquals("Thank you! Enjoy the book\n1 Physics Michio 2009\n2 C Dennis 1982\n", actualBooksList);
    }

    @Test
    public void checkedOutMovieShouldNotBeAvailableInLibrary() {
        availableMovies = new ArrayList<Movie>(Collections.singletonList(new Movie("Interstellar", 2014, "Nolan", 10)));
        Library library = new Library(view, availableBooks, availableMovies);
        Librarian librarian = new Librarian(null, new ArrayList<Movie>(), library, view, checkOutRegister, authenticator);
        librarian.checkOutMovie("Interstellar");
        library.displayAvailableMovies();
        String actualMoviesList = outputContent.toString();

        assertEquals("Thank you! Enjoy the movie\n", actualMoviesList);
    }


    @Test
    public void shouldCheckOutTheGivenMovie() {
        Library library = new Library(view, availableBooks, availableMovies);
        Librarian librarian = new Librarian(null, new ArrayList<Movie>(), library, view, checkOutRegister, authenticator);

        librarian.checkOutMovie("Interstellar");
        library.displayAvailableMovies();
        String actualOutput = outputContent.toString();
        String expectedOutput = "Thank you! Enjoy the movie\n1 Die Hard 4 2009 Bruce Wills 10.0\n2 The Pursuit of Happyness 2003 Will Smith 10.0\n";

        assertEquals(expectedOutput, actualOutput);
    }


    @Test
    public void shouldPrintErrorMessageWhenBookIsNotAvailable() {
        Library library = new Library(view, availableBooks, availableMovies);
        Librarian librarian = new Librarian(new ArrayList<Book>(), null, library, view, checkOutRegister, authenticator);

        librarian.checkOutBook("C#");
        String actualOutput = outputContent.toString();
        String expectedOutput = "That book is not available\n";

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void shouldAddAReturnedBookToLibrary() {
        Library library = new Library(view, availableBooks, availableMovies);
        Librarian librarian = new Librarian(new ArrayList<Book>(Collections.singletonList(new Book("Physics", "Michio", 2009))),
                null, library, view, checkOutRegister, authenticator);
        librarian.checkOutBook("Physics");
        librarian.returnBook("Physics");
        library.displayAvailableBooks();
        String actualOutput = outputContent.toString();

        assertEquals("Thank you! Enjoy the book\nThank you for returning the book\n1 Algorithms Cormen 2014\n2 C Dennis 1982\n3 Physics Michio 2009\n", actualOutput);
    }

    @Test
    public void shouldPrintErrorOnInvalidBookReturn() {
        Library library = new Library(view, availableBooks, availableMovies);
        Librarian librarian = new Librarian(new ArrayList<Book>(),
                null, library, view, null, null);

        librarian.returnBook("C#");
        String actualOutput = outputContent.toString();

        assertEquals("That is not a valid book to return\n", actualOutput);
    }


    @Test
    public void shouldPrintMessageOnSuccessfulCheckoutOfABook() {
        Library library = new Library(view, availableBooks, availableMovies);
        Librarian librarian = new Librarian(new ArrayList<Book>(), null, library, view, checkOutRegister, authenticator);
        librarian.checkOutBook("Algorithms");
        String actualOutput = outputContent.toString();

        assertEquals("Thank you! Enjoy the book\n", actualOutput);
    }

    @Test
    public void shouldPrintMessageOnSuccessfulCheckoutOfAMovie() {
        availableMovies.add(new Movie("Interstellar", 2014, "Nolan", 10));
        Library library = new Library(view, availableBooks, availableMovies);
        Librarian librarian = new Librarian(new ArrayList<Book>(), new ArrayList<Movie>(), library, view, checkOutRegister, authenticator);
        librarian.checkOutMovie("Interstellar");
        String actualOutput = outputContent.toString();

        assertEquals("Thank you! Enjoy the movie\n", actualOutput);
    }

    @Test
    public void shouldPrintMessageOnUnsuccessfulCheckoutOfABook() {
        Library library = new Library(view, availableBooks, availableMovies);
        library.addBook(new Book("Algorithms", "Cormen", 2014));
        Librarian librarian = new Librarian(new ArrayList<Book>(), null, library, view, checkOutRegister, authenticator);
        librarian.checkOutBook("Apple Mac");
        String actualOutput = outputContent.toString();

        assertEquals("That book is not available\n", actualOutput);
    }

    @Test
    public void shouldPrintMessageOnUnsuccessfulCheckoutOfAMovie() {
        Library library = new Library(view, availableBooks, availableMovies);
        Librarian librarian = new Librarian(new ArrayList<Book>(), null, library, view, checkOutRegister, authenticator);
        librarian.checkOutMovie("Unstoppable");
        String actualOutput = outputContent.toString();

        assertEquals("That movie is not available\n", actualOutput);
    }

    @Test
    public void returnedBookShouldNotBeInCheckedOutBooks() {
        Library library = new Library(view, availableBooks, availableMovies);
        Librarian librarian = new Librarian(new ArrayList<Book>(), null, library, view, checkOutRegister, authenticator);
        librarian.checkOutBook("C");
        librarian.returnBook("C");
        librarian.returnBook("C");

        String actualOutput = outputContent.toString();

        assertEquals("Thank you! Enjoy the book\nThank you for returning the book\nThat is not a valid book to return\n", actualOutput);
    }

    @Test
    public void returnedBookShouldNotBeInCheckedOutMovies() {
        availableMovies.add(new Movie("Interstellar", 2014, "Nolan", 10));
        Library library = new Library(view, availableBooks, availableMovies);
        Librarian librarian = new Librarian(null, new ArrayList<Movie>(), library, view, checkOutRegister, authenticator);
        librarian.checkOutMovie("Interstellar");
        librarian.returnMovie("Interstellar");
        librarian.returnMovie("Interstellar");

        String actualOutput = outputContent.toString();

        assertEquals("Thank you! Enjoy the movie\nThank you for returning the movie\nThat is not a valid movie to return\n", actualOutput);
    }
}
