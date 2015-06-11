package com.twu.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class BibliotecaTest {

    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    public void shouldPrintWelcomeMessage() {
        Biblioteca biblioteca = new Biblioteca();
        biblioteca.welcomeUser();

        String actualWelcomeMessage = outputStream.toString();

        assertThat(actualWelcomeMessage, is(equalTo("Bengaluru public library welcomes you!\n")));
    }

    @Test
    public void shouldReturnUserSelectedOption() {
        ByteArrayInputStream inputStream = new ByteArrayInputStream("3\n2\n".getBytes());
        System.setIn(inputStream);
        Biblioteca biblioteca = new Biblioteca();

        int actualSelectedOption = biblioteca.getOptionFromUser();

        assertThat(actualSelectedOption, is(equalTo(3)));
    }

    @Test
    public void shouldStartBibliotecaAndDisplayBooksIfSelected() {
        ByteArrayInputStream inputStream = new ByteArrayInputStream("1\n3\n".getBytes());
        System.setIn(inputStream);
        Biblioteca biblioteca = new Biblioteca();

        biblioteca.start();
        String actualOutput = outputStream.toString();

        assertThat(actualOutput, is(equalTo("Bengaluru public library welcomes you!\n1. List Books\n2. Check Out\n3. Quit" +
                "\nSelect an option : 1 Algorithms Cormen 2014\n" +
                "2 Physics Michio 2009\n3 C Dennis 1982\n1. List Books\n2. Check Out\n3. Quit\nSelect an option : ")));
    }

    @Test
    public void shouldStartBibliotecaAndQuitIfSelected() {
        ByteArrayInputStream inputStream = new ByteArrayInputStream("3".getBytes());
        System.setIn(inputStream);
        Biblioteca biblioteca = new Biblioteca();

        biblioteca.start();
        String actualOutput = outputStream.toString();

        assertThat(actualOutput, is(equalTo("Bengaluru public library welcomes you!\n" +
                "1. List Books\n2. Check Out\n3. Quit\nSelect an option : ")));
    }

    @After
    public void tearDown() {
        System.setOut(null);
        System.setIn(System.in);
    }
}
