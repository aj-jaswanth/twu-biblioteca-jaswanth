package com.twu.biblioteca;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class LibraryTest {
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    public void addingANewBookToLibraryShouldDisplayItInAvailableListR() {
        Library library = new Library();

        library.addBook(new Book("Refactoring", "Martin", 2005));
        library.displayAvailableBooks();
        String actualBooksList = outputStream.toString();

        assertEquals(actualBooksList, "1 Algorithms Cormen 2014\n2 Physics Michio 2009\n" +
                "3 C Dennis 1982\n4 Refactoring Martin 2005\n");
    }


    @After
    public void tearDown() {
        System.setIn(null);
        System.setOut(null);
    }
}