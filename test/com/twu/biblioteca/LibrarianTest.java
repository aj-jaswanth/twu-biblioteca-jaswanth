package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

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
        Librarian librarian = new Librarian(new ArrayList<Book>(), library);

        librarian.checkOutBook("Physics");
        library.displayAvailableBooks();
        String actualOutput = outputContent.toString();
        String expectedOutput = "Thank you! Enjoy the book\n1 Algorithms Cormen 2014\n" +
                "2 C Dennis 1982\n";

        assertEquals(expectedOutput, actualOutput);
    }
}
