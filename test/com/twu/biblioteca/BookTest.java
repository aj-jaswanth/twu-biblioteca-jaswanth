package com.twu.biblioteca;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class BookTest {
    @Test
    public void reflexivePropertyOfEquality() {
        Book firstBook = new Book("Algorithms", "Cormen", 2014);

        boolean areActuallyEqual = firstBook.equals(firstBook);

        assertThat(areActuallyEqual, is(equalTo(true)));
    }
}
