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
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

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
        app = new App(null, null, null, view);

        app.welcomeUser();
        String actualWelcomeMessage = outputStream.toString();

        assertThat(actualWelcomeMessage, is(equalTo("Bengaluru public library welcomes you!\n")));
    }

    @Test
    public void shouldReturnUserSelectedOption() {
        ByteArrayInputStream inputStream = new ByteArrayInputStream("3\n2\n".getBytes());
        Scanner scanner = new Scanner(inputStream);
        View view = new View(scanner);
        MainMenu mainMenu = new MainMenu(new String[]{Messages.LIST_BOOKS, Messages.LIST_MOVIES,
                Messages.CHECK_OUT_BOOK, Messages.CHECK_OUT_MOVIE, Messages.RETURN_BOOK, Messages.QUIT}, view);
        Library library = new Library(null, null, null);
        app = new App(mainMenu, library, null, view);

        int actualSelectedOption = app.getUserOption();

        assertThat(actualSelectedOption, is(equalTo(3)));
    }


    @Test
    public void shouldAskForOptionAgainIfUserSelectsInvalidOption() {
        ByteArrayInputStream inputStream = new ByteArrayInputStream("10\n6\n".getBytes());
        Scanner scanner = new Scanner(inputStream);
        View view = new View(scanner);
        MainMenu mainMenu = new MainMenu(new String[]{Messages.LIST_BOOKS, Messages.LIST_MOVIES,
                Messages.CHECK_OUT_BOOK, Messages.CHECK_OUT_MOVIE, Messages.RETURN_BOOK, Messages.QUIT}, view);
        Library library = new Library(view, null, null);
        app = new App(mainMenu, library, null, view);

        app.getUserOption();
        String actualResult = outputStream.toString();

        assertEquals("1. List Books\n" +
                "2. List Movies\n" +
                "3. Check Out Book\n" +
                "4. Check Out Movie\n" +
                "5. Return Book\n" +
                "6. Quit\n" +
                "Select an option : \n" +
                "Select a valid option!\n" +
                "1. List Books\n" +
                "2. List Movies\n" +
                "3. Check Out Book\n" +
                "4. Check Out Movie\n" +
                "5. Return Book\n" +
                "6. Quit\n" +
                "Select an option : \n", actualResult);
    }

    @Test
    public void shouldStartBibliotecaAndDisplayBooksIfSelected() {
        ByteArrayInputStream inputStream = new ByteArrayInputStream("1\n6\n".getBytes());
        Scanner scanner = new Scanner(inputStream);
        View view = new View(scanner);
        MainMenu mainMenu = new MainMenu(new String[]{Messages.LIST_BOOKS, Messages.LIST_MOVIES,
                Messages.CHECK_OUT_BOOK, Messages.CHECK_OUT_MOVIE, Messages.RETURN_BOOK, Messages.QUIT}, view);
        Library library = mock(Library.class);
        app = new App(mainMenu, library, null, view);

        app.start();

        verify(library, times(1)).displayAvailableBooks();
    }

    @Test
    public void shouldStartBibliotecaAndDisplayMoviesIfSelected() {
        ByteArrayInputStream inputStream = new ByteArrayInputStream("2\n6\n".getBytes());
        Scanner scanner = new Scanner(inputStream);
        View view = new View(scanner);
        MainMenu mainMenu = new MainMenu(new String[]{Messages.LIST_BOOKS, Messages.LIST_MOVIES,
                Messages.CHECK_OUT_BOOK, Messages.CHECK_OUT_MOVIE, Messages.RETURN_BOOK, Messages.QUIT}, view);
        Library library = mock(Library.class);
        app = new App(mainMenu, library, null, view);

        app.start();

        verify(library, times(1)).displayAvailableMovies();
    }

    @Test
    public void shouldStartBibliotecaAndQuitIfSelected() {
        ByteArrayInputStream inputStream = new ByteArrayInputStream("6".getBytes());
        Scanner scanner = new Scanner(inputStream);
        View view = new View(scanner);
        MainMenu mainMenu = new MainMenu(new String[]{Messages.LIST_BOOKS, Messages.LIST_MOVIES,
                Messages.CHECK_OUT_BOOK, Messages.CHECK_OUT_MOVIE, Messages.RETURN_BOOK, Messages.QUIT}, view);
        Library library = new Library(view, null, null);
        app = new App(mainMenu, library, null, view);

        app.start();
        String actualOutput = outputStream.toString();

        assertThat(actualOutput, is(equalTo("Bengaluru public library welcomes you!\n" +
                "1. List Books\n" +
                "2. List Movies\n" +
                "3. Check Out Book\n" +
                "4. Check Out Movie\n" +
                "5. Return Book\n" +
                "6. Quit\n" +
                "Select an option : \n")));
    }

    @Test
    public void shouldStartBibliotecaAndCheckOutBookIfSelected() {
        ByteArrayInputStream inputStream = new ByteArrayInputStream("3\nC\n6\n".getBytes());
        Scanner scanner = new Scanner(inputStream);
        View view = new View(scanner);
        MainMenu mainMenu = new MainMenu(new String[]{Messages.LIST_BOOKS, Messages.LIST_MOVIES,
                Messages.CHECK_OUT_BOOK, Messages.CHECK_OUT_MOVIE, Messages.RETURN_BOOK, Messages.QUIT}, view);
        Library library = mock(Library.class);
        Librarian librarian = mock(Librarian.class);
        app = new App(mainMenu, library, librarian, view);

        app.start();

        verify(librarian).checkOutBook("C");
    }

    @Test
    public void shouldStartBibliotecaAndCheckOutMovieIfSelected() {
        ByteArrayInputStream inputStream = new ByteArrayInputStream("4\nInterstellar\n6\n".getBytes());
        Scanner scanner = new Scanner(inputStream);
        View view = new View(scanner);
        MainMenu mainMenu = new MainMenu(new String[]{Messages.LIST_BOOKS, Messages.LIST_MOVIES,
                Messages.CHECK_OUT_BOOK, Messages.CHECK_OUT_MOVIE, Messages.RETURN_BOOK, Messages.QUIT}, view);
        Library library = mock(Library.class);
        Librarian librarian = mock(Librarian.class);
        app = new App(mainMenu, library, librarian, view);

        app.start();

        verify(librarian).checkOutMovie("Interstellar");
    }

    @Test
    public void shouldStartBibliotecaAndReturnBookIfSelected() {
        ByteArrayInputStream inputStream = new ByteArrayInputStream("3\nC\n5\nC\n6".getBytes());
        Scanner scanner = new Scanner(inputStream);
        View view = new View(scanner);
        MainMenu mainMenu = new MainMenu(new String[]{Messages.LIST_BOOKS, Messages.LIST_MOVIES,
                Messages.CHECK_OUT_BOOK, Messages.CHECK_OUT_MOVIE, Messages.RETURN_BOOK, Messages.QUIT}, view);
        Library library = mock(Library.class);
        Librarian librarian = mock(Librarian.class);
        app = new App(mainMenu, library, librarian, view);

        app.start();

        verify(librarian).returnBook("C");
    }

    @After
    public void tearDown() {
        System.setOut(null);
        System.setIn(System.in);
    }
}
