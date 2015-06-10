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
    public void addingANewBookToLibraryShouldDisplayItInAvailableList() {
        Library library = new Library();
        library.addBook(new Book("Algorithms", "Cormen", 2014));
        library.addBook(new Book("Physics", "Michio", 2009));
        library.addBook(new Book("C", "Dennis", 1982));
        library.addBook(new Book("Refactoring", "Martin", 2005));

        library.displayAvailableBooks();
        String actualBooksList = outputStream.toString();

        assertEquals("1 Algorithms Cormen 2014\n2 Physics Michio 2009\n" +
                "3 C Dennis 1982\n4 Refactoring Martin 2005\n", actualBooksList);
    }

    @Test
    public void checkedOutBookShouldNotBeAvailableInLibrary() {
        Library library = new Library();
        library.addBook(new Book("Algorithms", "Cormen", 2014));

        library.checkout(1);
        library.displayAvailableBooks();
        String actualBooksList = outputStream.toString();

        assertEquals("", actualBooksList);
    }

    @After
    public void tearDown() {
        System.setOut(null);
    }
}