package com.twu.biblioteca;


import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class LibraryTest {

    @Test
    public void noBookIsAvailableInLibrary() {
        Library library = new Library();

        int actualBookCount = library.availableBooksCount();

        assertThat(actualBookCount, is(equalTo(0)));
    }

    @Test
    public void threeBooksAreAvailableInLibrary() {
        Library library = new Library();
        library.addBook("Refactoring Practices", "Martin Flower", 2004);
        library.addBook("Physics", "Kip Thorne", 2010);
        library.addBook("Alogrithms", "Cormen", 2001);

        int actualBookCount = library.availableBooksCount();

        assertThat(actualBookCount, is(equalTo(3)));
    }

    @Test
    public void checkingOutABookFromLibraryShouldRemoveItFromAvailableBooks() {
        Library library = new Library();
        library.addBook("Refactoring Practices", "Martin Flower", 2004);
        library.addBook("Physics", "Kip Thorne", 2010);
        library.addBook("Algorithms", "Cormen", 2001);

        library.checkOutBook("Algorithms");
        boolean isCheckedOutBookAvailableInLibrary = library.isInLibrary("Algorithms");

        assertThat(isCheckedOutBookAvailableInLibrary, is(equalTo(false)));
    }
}