package com.twu.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static com.twu.biblioteca.Messages.*;
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
        ByteArrayInputStream inputStream = new ByteArrayInputStream("3\n2\n"
                .getBytes());
        Scanner scanner = new Scanner(inputStream);
        View view = new View(scanner);
        BasicMainMenu basicMainMenu = new BasicMainMenu(new String[]{LIST_BOOKS, LIST_MOVIES,
                CHECK_OUT_BOOK, CHECK_OUT_MOVIE, RETURN_BOOK, QUIT}, view, null, null);
        Library library = new Library(null, null, null);
        app = new App(basicMainMenu, library, null, view);

        int actualSelectedOption = app.getUserOption();

        assertThat(actualSelectedOption, is(equalTo(3)));
    }


    @Test
    public void shouldAskForOptionAgainIfUserSelectsInvalidOption() {
        ByteArrayInputStream inputStream = new ByteArrayInputStream("10\n6\n"
                .getBytes());
        Scanner scanner = new Scanner(inputStream);
        View view = new View(scanner);
        BasicMainMenu basicMainMenu = new BasicMainMenu(new String[]{LIST_BOOKS, LIST_MOVIES,
                CHECK_OUT_BOOK, CHECK_OUT_MOVIE, RETURN_BOOK, QUIT}, view, null, null);
        Library library = new Library(view, null, null);
        app = new App(basicMainMenu, library, null, view);

        app.getUserOption();
        String actualResult = outputStream.toString();

        assertEquals("1. List Books\n2. List Movies\n3. Check Out Book\n4. Check Out Movie\n5. Return Book\n6. Quit\nSelect an option : \nSelect a valid option!\n1. List Books\n2. List Movies\n3. Check Out Book\n4. Check Out Movie\n5. Return Book\n6. Quit\nSelect an option : \n", actualResult);
    }

    @Test
    public void shouldStartBibliotecaAndDisplayBooksIfSelected() {
        ByteArrayInputStream inputStream = new ByteArrayInputStream("1\n7\n"
                .getBytes());
        Scanner scanner = new Scanner(inputStream);
        View view = new View(scanner);
        Library library = mock(Library.class);
        BasicMainMenu basicMainMenu = new BasicMainMenu(new String[]{LIST_BOOKS, LIST_MOVIES,
                CHECK_OUT_BOOK, CHECK_OUT_MOVIE, RETURN_BOOK, RETURN_MOVIE,
                QUIT}, view, library, null);
        app = new App(basicMainMenu, library, null, view);

        app.start();

        verify(library, times(1)).displayAvailableBooks();
    }

    @Test
    public void shouldStartBibliotecaAndDisplayMoviesIfSelected() {
        ByteArrayInputStream inputStream = new ByteArrayInputStream("2\n7\n"
                .getBytes());
        Scanner scanner = new Scanner(inputStream);
        View view = new View(scanner);
        Library library = mock(Library.class);
        BasicMainMenu basicMainMenu = new BasicMainMenu(new String[]{LIST_BOOKS, LIST_MOVIES,
                CHECK_OUT_BOOK, CHECK_OUT_MOVIE, RETURN_BOOK, RETURN_MOVIE,
                QUIT}, view, library, null);
        app = new App(basicMainMenu, library, null, view);

        app.start();

        verify(library, times(1)).displayAvailableMovies();
    }

    @Test
    public void shouldStartBibliotecaAndQuitIfSelected() {
        ByteArrayInputStream inputStream = new ByteArrayInputStream("7"
                .getBytes());
        Scanner scanner = new Scanner(inputStream);
        View view = new View(scanner);
        BasicMainMenu basicMainMenu = new BasicMainMenu(new String[]{LIST_BOOKS, LIST_MOVIES,
                CHECK_OUT_BOOK, CHECK_OUT_MOVIE, RETURN_BOOK, RETURN_MOVIE,
                QUIT}, view, null, null);
        Library library = new Library(view, null, null);
        app = new App(basicMainMenu, library, null, view);

        app.start();
        String actualOutput = outputStream.toString();

        assertThat(actualOutput, is(equalTo("Bengaluru public library welcomes you!\n1. List Books\n2. List Movies\n3. Check Out Book\n4. Check Out Movie\n5. Return Book\n6. Return Movie\n7. Quit\nSelect an option : \n")));
    }

    @Test
    public void shouldStartBibliotecaAndCheckOutBookIfSelected() {
        ByteArrayInputStream inputStream = new ByteArrayInputStream
                ("3\nC\n7\n".getBytes());
        Scanner scanner = new Scanner(inputStream);
        View view = new View(scanner);
        Library library = mock(Library.class);
        Librarian librarian = mock(Librarian.class);
        BasicMainMenu basicMainMenu = new BasicMainMenu(new String[]{LIST_BOOKS, LIST_MOVIES,
                CHECK_OUT_BOOK, CHECK_OUT_MOVIE, RETURN_BOOK, RETURN_MOVIE,
                QUIT}, view, library, librarian);
        app = new App(basicMainMenu, library, librarian, view);

        app.start();

        verify(librarian).checkOutBook("C");
    }

    @Test
    public void shouldStartBibliotecaAndCheckOutMovieIfSelected() {
        ByteArrayInputStream inputStream = new ByteArrayInputStream
                ("4\nInterstellar\n7\n".getBytes());
        Scanner scanner = new Scanner(inputStream);
        View view = new View(scanner);
        Library library = mock(Library.class);
        Librarian librarian = mock(Librarian.class);
        BasicMainMenu basicMainMenu = new BasicMainMenu(new String[]{LIST_BOOKS, LIST_MOVIES,
                CHECK_OUT_BOOK, CHECK_OUT_MOVIE, RETURN_BOOK, RETURN_MOVIE,
                QUIT}, view, library, librarian);
        app = new App(basicMainMenu, library, librarian, view);

        app.start();

        verify(librarian).checkOutMovie("Interstellar");
    }

    @Test
    public void shouldStartBibliotecaAndReturnBookIfSelected() {
        ByteArrayInputStream inputStream = new ByteArrayInputStream
                ("3\nC\n5\nC\n7".getBytes());
        Scanner scanner = new Scanner(inputStream);
        View view = new View(scanner);
        Library library = mock(Library.class);
        Librarian librarian = mock(Librarian.class);
        BasicMainMenu basicMainMenu = new BasicMainMenu(new String[]{LIST_BOOKS, LIST_MOVIES,
                CHECK_OUT_BOOK, CHECK_OUT_MOVIE, RETURN_BOOK, RETURN_MOVIE,
                QUIT}, view, library, librarian);
        app = new App(basicMainMenu, library, librarian, view);

        app.start();

        verify(librarian).returnBook("C");
    }

    @Test
    public void shouldStartBibliotecaAndReturnMovieIfSelected() {
        ByteArrayInputStream inputStream = new ByteArrayInputStream
                ("4\nInterstellar\n6\nInterstellar\n7".getBytes());
        Scanner scanner = new Scanner(inputStream);
        View view = new View(scanner);
        Library library = mock(Library.class);
        Librarian librarian = mock(Librarian.class);
        BasicMainMenu basicMainMenu = new BasicMainMenu(new String[]{LIST_BOOKS, LIST_MOVIES,
                CHECK_OUT_BOOK, CHECK_OUT_MOVIE, RETURN_BOOK, RETURN_MOVIE,
                QUIT}, view, library, librarian);
        app = new App(basicMainMenu, library, librarian, view);

        app.start();

        verify(librarian).returnMovie("Interstellar");
    }

    @After
    public void tearDown() {
        System.setOut(null);
        System.setIn(System.in);
    }
}
