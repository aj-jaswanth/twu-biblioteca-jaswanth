package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class LibrarianTest {

    private ByteArrayOutputStream outputContent;
    private View view;

    @Before
    public void setUp() {
        outputContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputContent));
        view = new View(null);
    }

    @Test
    public void shouldCheckOutTheGivenBook() {
        Library library = new Library(view);
        Librarian librarian = new Librarian(new ArrayList<Book>(), library, view);

        librarian.checkOutBook("Physics");
        library.displayAvailableBooks();
        String actualOutput = outputContent.toString();
        String expectedOutput = "Thank you! Enjoy the book\n1 Algorithms Cormen 2014\n" +
                "2 C Dennis 1982\n";

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void shouldPrintErrorMessageWhenBookIsNotAvailable() {
        Library library = new Library(view);
        Librarian librarian = new Librarian(new ArrayList<Book>(), library, view);

        librarian.checkOutBook("C#");
        String actualOutput = outputContent.toString();
        String expectedOutput = "That book is not available\n";

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void shouldAddAReturnedBookToLibrary() {
        Library library = new Library(view);
        Librarian librarian = new Librarian(new ArrayList<Book>(Arrays.asList(new Book("Physics", "Michio", 2009))),
                library, view);

        library.checkout("Physics");
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
        Library library = new Library(view);
        Librarian librarian = new Librarian(new ArrayList<Book>(),
                library, view);

        librarian.returnBook("C#");
        String actualOutput = outputContent.toString();

        assertEquals("That is not a valid book to return\n", actualOutput);
    }
}
