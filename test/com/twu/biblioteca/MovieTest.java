package com.twu.biblioteca;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class MovieTest {

    @Test
    public void shouldReturnFalseWhenNullIsPassed() {
        Movie firstMovie = new Movie("Interstellar", 2014, "Christopher Nolan", 10.0);

        boolean actualEquality = firstMovie.equals(null);

        assertThat(actualEquality, is(equalTo(false)));
    }

    @Test
    public void shouldReturnFalseWhenOtherObjectIsPassed() {
        Movie firstMovie = new Movie("Interstellar", 2014, "Christopher Nolan", 10.0);

        boolean actualEquality = firstMovie.equals("");

        assertThat(actualEquality, is(equalTo(false)));
    }

    @Test
    public void reflexivePropertyOfEquality() {
        Movie firstMovie = new Movie("Interstellar", 2014, "Christopher Nolan", 10.0);

        assertEquals(firstMovie, firstMovie);
    }

    @Test
    public void symmetricPropertyOfEquality() {
        Movie firstMovie = new Movie("Interstellar", 2014, "Christopher Nolan", 10.0);
        Movie secondMovie = new Movie("Interstellar", 2014, "Christopher Nolan", 10.0);

        assertEquals(firstMovie, secondMovie);
    }

    @Test
    public void transitivePropertyOfEquality() {
        Movie firstMovie = new Movie("Interstellar", 2014, "Christopher Nolan", 10.0);
        Movie secondMovie = new Movie("Interstellar", 2014, "Christopher Nolan", 10.0);
        Movie thirdMovie = new Movie("Interstellar", 2014, "Christopher Nolan", 10.0);

        assertEquals(firstMovie, secondMovie);
        assertEquals(secondMovie, thirdMovie);
        assertEquals(firstMovie, thirdMovie);
    }

    @Test
    public void ifTwoMoviesAreEqualThenTheirHashCodesAreSame() {
        Movie firstMovie = new Movie("Interstellar", 2014, "Christopher Nolan", 10.0);
        Movie secondMovie = new Movie("Interstellar", 2014, "Christopher Nolan", 10.0);

        boolean twoMoviesAreEqualThenHashCodesAreSame = firstMovie.equals(secondMovie) && (firstMovie.hashCode() == secondMovie.hashCode());

        assertThat(twoMoviesAreEqualThenHashCodesAreSame, is(equalTo(true)));
    }
}
