package com.twu.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

import static com.twu.biblioteca.Messages.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class AppTest {

    private ByteArrayOutputStream outputStream;
    private App app;
    private StartMenu startMenu;
    private Library library;
    private Librarian librarian;
    private String[] startMenuOptions = new String[]{LIST_BOOKS, LIST_MOVIES, LOGIN, QUIT};

    @Before
    public void setUp() {
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    public void shouldPrintWelcomeMessage() {
        Scanner scanner = new Scanner(System.in);
        View view = new View(scanner);
        app = new App(null, null, null, view);

        app.welcomeUser();
        String actualWelcomeMessage = outputStream.toString();

        assertThat(actualWelcomeMessage, is(equalTo("Bengaluru public library welcomes you!\n")));
    }

    @Test
    public void shouldDisplayStartMenuByDefault() {
        ByteArrayInputStream inputContent = new ByteArrayInputStream("4".getBytes());
        View view = new View(new Scanner(inputContent));
        Library library = new Library(view, new ArrayList<Book>(), new ArrayList<Movie>());
        StartMenu startMenu = new StartMenu(startMenuOptions, view, library, librarian);
        Menu[] menus = {startMenu, null, null};
        App app = new App(menus, library, librarian, view);

        app.start();
        String actualOutput = outputStream.toString();

        assertEquals("Bengaluru public library welcomes you!\n1. List Books\n2. List Movies\n3. Login\n4. Quit\nSelect an option : \n", actualOutput);
    }

    @After
    public void tearDown() {
        System.setOut(null);
        System.setIn(System.in);
    }
}
