package com.twu.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static com.twu.biblioteca.Messages.RETURN_BOOK_ERROR;
import static org.junit.Assert.assertEquals;

public class LibraryTest {
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private View view;

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outputStream));
        view = new View(null);
    }

    @Test
    public void addingANewBookToLibraryShouldDisplayItInAvailableList() {
        Library library = new Library(view);
        library.addBook(new Book("Refactoring", "Martin", 2005));

        library.displayAvailableBooks();
        String actualBooksList = outputStream.toString();

        assertEquals("1 Algorithms Cormen 2014\n2 Physics Michio 2009\n" +
                "3 C Dennis 1982\n4 Refactoring Martin 2005\n", actualBooksList);
    }

    @Test
    public void checkedOutBookShouldNotBeAvailableInLibrary() {
        Library library = new Library(view);
        Librarian librarian = new Librarian(new ArrayList<Book>(), library, view);
        librarian.checkOutBook("Algorithms");
        library.displayAvailableBooks();
        String actualBooksList = outputStream.toString();

        assertEquals("Thank you! Enjoy the book\n1 Physics Michio 2009\n" +
                "2 C Dennis 1982\n", actualBooksList);
    }

    @Test
    public void shouldRemoveABookFromLibrary() {
        Library library = new Library(view);

        library.removeBook("Physics");
        library.displayAvailableBooks();
        String actualOutput = outputStream.toString();

        assertEquals("1 Algorithms Cormen 2014\n" +
                "2 C Dennis 1982\n", actualOutput);
    }

    @Test
    public void shouldPrintMessageOnSuccessfulCheckoutOfABook() {
        Library library = new Library(view);
        Librarian librarian = new Librarian(new ArrayList<Book>(), library, view);
        librarian.checkOutBook("Algorithms");
        String actualOutput = outputStream.toString();

        assertEquals("Thank you! Enjoy the book\n", actualOutput);
    }

    @Test
    public void shouldPrintMessageOnUnsuccessfulCheckoutOfABook() {
        Library library = new Library(view);
        library.addBook(new Book("Algorithms", "Cormen", 2014));
        Librarian librarian = new Librarian(new ArrayList<Book>(), library, view);
        librarian.checkOutBook("Apple Mac");
        String actualOutput = outputStream.toString();

        assertEquals("That book is not available\n", actualOutput);
    }

    @Test
    public void returnedBookShouldNotBeInCheckedOutBooks() {
        Library library = new Library(view);
        Librarian librarian = new Librarian(new ArrayList<Book>(), library, view);
        librarian.checkOutBook("C");
        librarian.returnBook("C");
        librarian.returnBook("C");

        String actualOutput = outputStream.toString();

        assertEquals("Thank you! Enjoy the book\n" +
                "Thank you for returning the book\n" + RETURN_BOOK_ERROR + "\n", actualOutput);
    }

    @Test
    public void shouldReturnCorrectBookAsSearchResult() {
        Library library = new Library(null);
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