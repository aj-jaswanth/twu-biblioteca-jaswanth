package com.twu.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class AuthenticatorTest {

    private ByteArrayOutputStream outputContent;

    @Before
    public void setUp() {
        outputContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputContent));
    }

    @Test
    public void shouldAuthenticateNormalUser() {
        ByteArrayInputStream inputContent = new ByteArrayInputStream("123-123\npwd".getBytes());
        Authenticator authenticator = new Authenticator(new View(new Scanner(inputContent)));

        int actualResult = authenticator.authenticate();

        assertThat(actualResult, is(1));
    }

    @Test
    public void shouldAuthenticateLibrarian() {
        ByteArrayInputStream inputContent = new ByteArrayInputStream("123-321\ndwp".getBytes());
        Authenticator authenticator = new Authenticator(new View(new Scanner(inputContent)));

        int actualResult = authenticator.authenticate();

        assertThat(actualResult, is(2));
    }

    @Test
    public void shouldRejectInvalidLogin() {
        ByteArrayInputStream inputContent = new ByteArrayInputStream("123\nabc".getBytes());
        Authenticator authenticator = new Authenticator(new View(new Scanner(inputContent)));

        int actualResult = authenticator.authenticate();

        assertThat(actualResult, is(0));
    }

    @After
    public void tearDown() {
        System.setOut(null);
    }
}
