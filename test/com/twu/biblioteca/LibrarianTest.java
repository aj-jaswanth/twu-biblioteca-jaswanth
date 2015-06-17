package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.twu.biblioteca.Messages.RETURN_BOOK_ERROR;
import static org.junit.Assert.assertEquals;

public class LibrarianTest {

    private ByteArrayOutputStream outputContent;
    private View view;
    private List<Book> availableBooks = new ArrayList<Book>(Arrays.asList(new Book("Algorithms", "Cormen", 2014), new Book("Physics", "Michio", 2009),
            new Book("C", "Dennis", 1982)));
    private List<Movie> availableMovies = new ArrayList<Movie>(Arrays.asList(new Movie("Interstellar", 2014, "Christopher Nolan", 10.0),
            new Movie("Die Hard 4", 2009, "Bruce Wills", 10.0),
            new Movie("The Pursuit of Happyness", 2003, "Will Smith", 10.0)));

    @Before
    public void setUp() {
        outputContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputContent));
        view = new View(null);
    }

    @Test
    public void shouldCheckOutTheGivenBook() {
        Library library = new Library(view, availableBooks, availableMovies);
        Librarian librarian = new Librarian(new ArrayList<Book>(), null, library, view);

        librarian.checkOutBook("Physics");
        library.displayAvailableBooks();
        String actualOutput = outputContent.toString();
        String expectedOutput = "Thank you! Enjoy the book\n1 Algorithms Cormen 2014\n" +
                "2 C Dennis 1982\n";

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void checkedOutBookShouldNotBeAvailableInLibrary() {
        Library library = new Library(view, availableBooks, availableMovies);
        Librarian librarian = new Librarian(new ArrayList<Book>(), null, library, view);
        librarian.checkOutBook("Algorithms");
        library.displayAvailableBooks();
        String actualBooksList = outputContent.toString();

        assertEquals("Thank you! Enjoy the book\n1 Physics Michio 2009\n" +
                "2 C Dennis 1982\n", actualBooksList);
    }

    @Test
    public void checkedOutMovieShouldNotBeAvailableInLibrary() {
        availableMovies = new ArrayList<Movie>(Arrays.asList(new Movie("Interstellar", 2014, "Nolan", 10)));
        Library library = new Library(view, availableBooks, availableMovies);
        Librarian librarian = new Librarian(null, new ArrayList<Movie>(), library, view);
        librarian.checkOutMovie("Interstellar");
        library.displayAvailableMovies();
        String actualMoviesList = outputContent.toString();

        assertEquals("Thank you! Enjoy the movie\n", actualMoviesList);
    }


    @Test
    public void shouldCheckOutTheGivenMovie() {
        Library library = new Library(view, availableBooks, availableMovies);
        Librarian librarian = new Librarian(null, new ArrayList<Movie>(), library, view);

        librarian.checkOutMovie("Interstellar");
        library.displayAvailableMovies();
        String actualOutput = outputContent.toString();
        String expectedOutput = "Thank you! Enjoy the movie\n" +
                "1 Die Hard 4 2009 Bruce Wills 10.0\n" +
                "2 The Pursuit of Happyness 2003 Will Smith 10.0\n";

        assertEquals(expectedOutput, actualOutput);
    }


    @Test
    public void shouldPrintErrorMessageWhenBookIsNotAvailable() {
        Library library = new Library(view, availableBooks, availableMovies);
        Librarian librarian = new Librarian(new ArrayList<Book>(), null, library, view);

        librarian.checkOutBook("C#");
        String actualOutput = outputContent.toString();
        String expectedOutput = "That book is not available\n";

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void shouldAddAReturnedBookToLibrary() {
        Library library = new Library(view, availableBooks, availableMovies);
        Librarian librarian = new Librarian(new ArrayList<Book>(Collections.singletonList(new Book("Physics", "Michio", 2009))),
                null, library, view);
        librarian.checkOutBook("Physics");
        librarian.returnBook("Physics");
        library.displayAvailableBooks();
        String actualOutput = outputContent.toString();

        assertEquals("Thank you! Enjoy the book\n" +
                "Thank you for returning the book\n" +
                "1 Algorithms Cormen 2014\n" +
                "2 C Dennis 1982\n" +
                "3 Physics Michio 2009\n", actualOutput);
    }

    @Test
    public void shouldPrintErrorOnInvalidBookReturn() {
        Library library = new Library(view, availableBooks, availableMovies);
        Librarian librarian = new Librarian(new ArrayList<Book>(),
                null, library, view);

        librarian.returnBook("C#");
        String actualOutput = outputContent.toString();

        assertEquals("That is not a valid book to return\n", actualOutput);
    }


    @Test
    public void shouldPrintMessageOnSuccessfulCheckoutOfABook() {
        Library library = new Library(view, availableBooks, availableMovies);
        Librarian librarian = new Librarian(new ArrayList<Book>(), null, library, view);
        librarian.checkOutBook("Algorithms");
        String actualOutput = outputContent.toString();

        assertEquals("Thank you! Enjoy the book\n", actualOutput);
    }

    @Test
    public void shouldPrintMessageOnSuccessfulCheckoutOfAMovie() {
        availableMovies.add(new Movie("Interstellar", 2014, "Nolan", 10));
        Library library = new Library(view, availableBooks, availableMovies);
        Librarian librarian = new Librarian(new ArrayList<Book>(), new ArrayList<Movie>(), library, view);
        librarian.checkOutMovie("Interstellar");
        String actualOutput = outputContent.toString();

        assertEquals("Thank you! Enjoy the movie\n", actualOutput);
    }

    @Test
    public void shouldPrintMessageOnUnsuccessfulCheckoutOfABook() {
        Library library = new Library(view, availableBooks, availableMovies);
        library.addBook(new Book("Algorithms", "Cormen", 2014));
        Librarian librarian = new Librarian(new ArrayList<Book>(), null, library, view);
        librarian.checkOutBook("Apple Mac");
        String actualOutput = outputContent.toString();

        assertEquals("That book is not available\n", actualOutput);
    }

    @Test
    public void shouldPrintMessageOnUnsuccessfulCheckoutOfAMovie() {
        Library library = new Library(view, availableBooks, availableMovies);
        Librarian librarian = new Librarian(new ArrayList<Book>(), null, library, view);
        librarian.checkOutMovie("Unstoppable");
        String actualOutput = outputContent.toString();

        assertEquals("That movie is not available\n", actualOutput);
    }

    @Test
    public void returnedBookShouldNotBeInCheckedOutBooks() {
        Library library = new Library(view, availableBooks, availableMovies);
        Librarian librarian = new Librarian(new ArrayList<Book>(), null, library, view);
        librarian.checkOutBook("C");
        librarian.returnBook("C");
        librarian.returnBook("C");

        String actualOutput = outputContent.toString();

        assertEquals("Thank you! Enjoy the book\n" +
                "Thank you for returning the book\n" + RETURN_BOOK_ERROR + "\n", actualOutput);
    }

    @Test
    public void returnedBookShouldNotBeInCheckedOutMovies() {
        availableMovies.add(new Movie("Interstellar", 2014, "Nolan", 10));
        Library library = new Library(view, availableBooks, availableMovies);
        Librarian librarian = new Librarian(null, new ArrayList<Movie>(), library, view);
        librarian.checkOutMovie("Interstellar");
        librarian.returnMovie("Interstellar");
        librarian.returnMovie("Interstellar");

        String actualOutput = outputContent.toString();

        assertEquals("Thank you! Enjoy the movie\n" +
                "Thank you for returning the movie\n" + Messages.RETURN_MOVIE_ERROR + "\n", actualOutput);
    }
}
