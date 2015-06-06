package com.twu.biblioteca;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class BookTest {
    @Test
    public void reflexivePropertyOfEquality() {
        Book firstBook = new Book("Algorithms", "Cormen", 2014);

        assertEquals(firstBook, firstBook);
    }

    @Test
    public void symmetricPropertyOfEquality() {
        Book firstBook = new Book("Algorithms", "Cormen", 2014);
        Book secondBook = new Book("Algorithms", "Cormen", 2014);

        assertEquals(firstBook, secondBook);
    }

    @Test
    public void transitivePropertyOfEquality() {
        Book firstBook = new Book("Algorithms", "Cormen", 2014);
        Book secondBook = new Book("Algorithms", "Cormen", 2014);
        Book thirdBook = new Book("Algorithms", "Cormen", 2014);

        assertEquals(firstBook, secondBook);
        assertEquals(secondBook, thirdBook);
        assertEquals(firstBook, thirdBook);
    }

    @Test
    public void ifTwoBooksAreEqualThenTheirHashCodesAreSame() {
        Book firstBook = new Book("Algorithms", "Cormen", 2014);
        Book secondBook = new Book("Algorithms", "Cormen", 2014);

        boolean twoBooksAreEqualThenHashCodesAreSame = firstBook.equals(secondBook) && (firstBook.hashCode() == secondBook.hashCode());

        assertThat(twoBooksAreEqualThenHashCodesAreSame, is(equalTo(true)));
    }
}
