package com.twu.biblioteca;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class SearchAgentTest {

    @Test
    public void shouldBeAbleToAddAnObject() {
        SearchAgent<Book> searchAgent = new SearchAgent<Book>("Physics");

        Book book = new Book("Physics", "Michio", 2009);
        searchAgent.add(book);
        Book actualBook = searchAgent.result();

        assertEquals(book, actualBook);
    }

    @Test
    public void equalityOfSearchAgentAndNothing() {
        SearchAgent firstSearchAgent = new SearchAgent("Physics");

        boolean actual = firstSearchAgent.equals(null);

        assertThat(actual, is(false));
    }

    @Test
    public void equalityOfSearchAgentAndOtherObject() {
        SearchAgent firstSearchAgent = new SearchAgent("Physics");

        boolean actual = firstSearchAgent.equals("OtherString");

        assertThat(actual, is(false));
    }

    @Test
    public void reflexivePropertyOfEqualsMethod() {
        SearchAgent firstSearchAgent = new SearchAgent("Physics");

        boolean actual = firstSearchAgent.equals(firstSearchAgent);

        assertThat(actual, is(true));
    }

    @Test
    public void symmetricPropertyOfEqualsMethod() {
        SearchAgent firstSearchAgent = new SearchAgent("Physics");
        SearchAgent secondSearchAgent = new SearchAgent("Physics");

        boolean actual = firstSearchAgent.equals(secondSearchAgent) == secondSearchAgent.equals(firstSearchAgent);

        assertThat(actual, is(true));
    }

    @Test
    public void transitivePropertyOfEqualsMethod() {
        SearchAgent firstSearchAgent = new SearchAgent("Physics");
        SearchAgent secondSearchAgent = new SearchAgent("Physics");
        SearchAgent thirdSearchAgent = new SearchAgent("Physics");

        boolean actual = firstSearchAgent.equals(secondSearchAgent) && secondSearchAgent.equals(thirdSearchAgent) && firstSearchAgent.equals(thirdSearchAgent);

        assertThat(actual, is(true));
    }

    @Test
    public void ifTwoSearchAgentsAreEqualThenTheirHashCodesMustBeEqual() {
        SearchAgent firstSearchAgent = new SearchAgent("Physics");
        SearchAgent secondSearchAgent = new SearchAgent("Physics");

        boolean actual = firstSearchAgent.equals(secondSearchAgent) && firstSearchAgent.hashCode() == secondSearchAgent.hashCode();

        assertThat(actual, is(true));
    }

    @Test
    public void ifTwoSearchAgentsAreDifferentThenTheirHashCodesMustBeDifferent() {
        SearchAgent firstSearchAgent = new SearchAgent("Physics");
        SearchAgent secondSearchAgent = new SearchAgent("Algorithms");

        boolean actual = firstSearchAgent.equals(secondSearchAgent) && firstSearchAgent.hashCode() == secondSearchAgent.hashCode();

        assertThat(actual, is(false));
    }
}
