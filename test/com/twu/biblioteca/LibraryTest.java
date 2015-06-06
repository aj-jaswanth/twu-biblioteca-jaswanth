package com.twu.biblioteca;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class LibraryTest {
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    public void noBookIsAvailableInLibrary() {
        Library library = new Library();

        int actualBookCount = library.availableBooksCount();

        assertThat(actualBookCount, is(equalTo(0)));
    }

    @Test
    public void threeBooksAreAvailableInLibrary() {
        Library library = new Library();
        library.addBook("Refactoring Practices", "Martin Fowler", 2004);
        library.addBook("Physics", "Kip Thorne", 2010);
        library.addBook("Alogrithms", "Cormen", 2001);

        int actualBooksCount = library.availableBooksCount();

        assertThat(actualBooksCount, is(equalTo(3)));
    }

    @Test
    public void checkingOutAnAvailableBookFromLibraryShouldRemoveItFromAvailableBooks() {
        Library library = new Library();
        library.addBook("Refactoring Practices", "Martin Fowler", 2004);
        library.addBook("Physics", "Kip Thorne", 2010);
        library.addBook("Algorithms", "Cormen", 2001);

        library.checkOutBook("Algorithms");
        boolean isCheckedOutBookAvailableInLibrary = library.isInLibrary("Algorithms");

        assertThat(isCheckedOutBookAvailableInLibrary, is(equalTo(false)));
    }

    @Test
    public void checkingOutAnAvailableBookFromLibraryShouldPrintThankYou() {
        Library library = new Library();
        library.addBook("Refactoring Practices", "Martin Fowler", 2004);
        library.addBook("Physics", "Kip Thorne", 2010);
        library.addBook("Algorithms", "Cormen", 2001);

        library.checkOutBook("Algorithms");
        String actualResponse = outputStream.toString();

        assertThat(actualResponse, equalTo("Thank you! Enjoy the book\n"));
    }

    @Test
    public void checkingOutAnUnavailableBookShouldPrintBookNotAvailable() {
        Library library = new Library();
        library.addBook("Refactoring Practices", "Martin Fowler", 2004);
        library.addBook("Physics", "Kip Thorne", 2010);
        library.addBook("Algorithms", "Cormen", 2001);

        library.checkOutBook("Mathematics");
        String actualResponse = outputStream.toString();

        assertThat(actualResponse, equalTo("That book is not available!\n"));
    }

    @After
    public void tearDown() {
        System.setIn(null);
        System.setOut(null);
    }
}