package com.twu.bibliotecatest;


import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class LibraryTest {

    @Test
    public void noBookIsAvailableInLibrary() {
        Library library = new Library();

        int actual = library.availableBooksCount();

        assertThat(actual, is(0));
    }
}