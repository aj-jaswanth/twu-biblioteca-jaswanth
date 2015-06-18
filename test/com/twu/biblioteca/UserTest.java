package com.twu.biblioteca;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class UserTest {

    @Test
    public void shouldReturnFalseWhenNullIsPassed() {
        User firstUser = new User("Jaswanth", "aj.jaswanth@gmail.com", 12345);

        boolean actualEquality = firstUser.equals(null);

        assertThat(actualEquality, is(equalTo(false)));
    }

    @Test
    public void shouldReturnFalseWhenOtherObjectIsPassed() {
        User firstUser = new User("Jaswanth", "aj.jaswanth@gmail.com", 12345);

        boolean actualEquality = firstUser.equals("");

        assertThat(actualEquality, is(equalTo(false)));
    }

    @Test
    public void reflexivePropertyOfEquality() {
        User firstUser = new User("Jaswanth", "aj.jaswanth@gmail.com", 12345);

        assertEquals(firstUser, firstUser);
    }

    @Test
    public void symmetricPropertyOfEquality() {
        User firstUser = new User("Jaswanth", "aj.jaswanth@gmail.com", 12345);
        User secondUser = new User("Jaswanth", "aj.jaswanth@gmail.com", 12345);

        assertEquals(firstUser, secondUser);
    }

    @Test
    public void transitivePropertyOfEquality() {
        User firstUser = new User("Jaswanth", "aj.jaswanth@gmail.com", 12345);
        User secondUser = new User("Jaswanth", "aj.jaswanth@gmail.com", 12345);
        User thirdUser = new User("Jaswanth", "aj.jaswanth@gmail.com", 12345);

        assertEquals(firstUser, secondUser);
        assertEquals(secondUser, thirdUser);
        assertEquals(firstUser, thirdUser);
    }

    @Test
    public void ifTwoUsersAreEqualThenTheirHashCodesAreSame() {
        User firstUser = new User("Jaswanth", "aj.jaswanth@gmail.com", 12345);
        User secondUser = new User("Jaswanth", "aj.jaswanth@gmail.com", 12345);

        boolean twoUsersAreEqualThenHashCodesAreSame = firstUser.equals(secondUser) && (firstUser.hashCode() == secondUser.hashCode());

        assertThat(twoUsersAreEqualThenHashCodesAreSame, is(equalTo(true)));
    }

    @Test
    public void shouldReturnInfoAboutUser() {
        User user = new User("Jaswanth", "aj.jaswanth@gmail.com", 12345);

        String actualOutput = user.toString();

        assertEquals("Jaswanth aj.jaswanth@gmail.com 12345", actualOutput);
    }
}
