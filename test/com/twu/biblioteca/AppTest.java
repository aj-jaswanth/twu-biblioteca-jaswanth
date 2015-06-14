package com.twu.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class AppTest {

    private ByteArrayOutputStream outputStream;
    private App app;

    @Before
    public void setUp() {
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    public void shouldPrintWelcomeMessage() {
        Scanner scanner = new Scanner(System.in);
        View view = new View(scanner);
        app = new App(null, null, view);

        app.welcomeUser();
        String actualWelcomeMessage = outputStream.toString();

        assertThat(actualWelcomeMessage, is(equalTo("Bengaluru public library welcomes you!\n")));
    }

    @Test
    public void shouldReturnUserSelectedOption() {
        ByteArrayInputStream inputStream = new ByteArrayInputStream("3\n2\n".getBytes());
        Scanner scanner = new Scanner(inputStream);
        View view = new View(scanner);
        MainMenu mainMenu = new MainMenu(new String[]{Messages.LIST_BOOKS,
                Messages.CHECK_OUT, Messages.QUIT}, view);
        Library library = new Library();
        app = new App(mainMenu, library, view);

        int actualSelectedOption = app.getUserOption();

        assertThat(actualSelectedOption, is(equalTo(3)));
    }

    @Test
    public void shouldStartBibliotecaAndDisplayBooksIfSelected() {
        ByteArrayInputStream inputStream = new ByteArrayInputStream("1\n3\n".getBytes());
        Scanner scanner = new Scanner(inputStream);
        View view = new View(scanner);
        MainMenu mainMenu = new MainMenu(new String[]{Messages.LIST_BOOKS,
                Messages.CHECK_OUT, Messages.QUIT}, view
        );
        Library library = new Library();
        app = new App(mainMenu, library, view);

        app.start();
        String actualOutput = outputStream.toString();

        assertThat(actualOutput, is(equalTo("Bengaluru public library welcomes you!\n1. List Books\n2. Check Out\n3. Quit" +
                "\nSelect an option : \n1 Algorithms Cormen 2014\n" +
                "2 Physics Michio 2009\n3 C Dennis 1982\n1. List Books\n2. Check Out\n3. Quit\nSelect an option : \n")));
    }

    @Test
    public void shouldStartBibliotecaAndQuitIfSelected() {
        ByteArrayInputStream inputStream = new ByteArrayInputStream("3".getBytes());
        Scanner scanner = new Scanner(inputStream);
        View view = new View(scanner);
        MainMenu mainMenu = new MainMenu(new String[]{Messages.LIST_BOOKS,
                Messages.CHECK_OUT, Messages.QUIT}, view);
        Library library = new Library();
        app = new App(mainMenu, library, view);

        app.start();
        String actualOutput = outputStream.toString();

        assertThat(actualOutput, is(equalTo("Bengaluru public library welcomes you!\n" +
                "1. List Books\n2. Check Out\n3. Quit\nSelect an option : \n")));
    }

    @Test
    public void shouldStartBibliotecaAndCheckOutBookIfSelected() {
        ByteArrayInputStream inputStream = new ByteArrayInputStream("2\n1\n3".getBytes());
        Scanner scanner = new Scanner(inputStream);
        View view = new View(scanner);
        MainMenu mainMenu = new MainMenu(new String[]{Messages.LIST_BOOKS,
                Messages.CHECK_OUT, Messages.QUIT}, view);
        Library library = new Library();
        app = new App(mainMenu, library, view);

        app.start();
        String actualOutput = outputStream.toString();

        assertThat(actualOutput, is(equalTo("Bengaluru public library welcomes you!\n1. List Books\n2. Check Out\n3. Quit\n" +
                "Select an option : \nEnter book ID : Thank you! Enjoy the book" +
                "\n1. List Books\n2. Check Out\n3. Quit\nSelect an option : \n")));
    }

    @After
    public void tearDown() {
        System.setOut(null);
        System.setIn(System.in);
    }
}
