package com.twu.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static com.twu.biblioteca.Messages.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class LibrarianMenuTest {

    private String[] librarianMenuOptions = new String[]{LIST_BOOKS, LIST_MOVIES, LOGIN, QUIT};
    private ByteArrayOutputStream outputStream;

    @Before
    public void setUp() {
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    public void shouldReturnSelectedOptionIfValid() {
        ByteArrayInputStream inputContent = new ByteArrayInputStream("4".getBytes());
        View view = new View(new Scanner(inputContent));
        LibrarianMenu librarianMenu = new LibrarianMenu(librarianMenuOptions, view, null, null, null, null);

        int actualOption = librarianMenu.selectedOption();

        assertThat(actualOption, is(4));
    }

    @Test
    public void shouldReturnMinuOneIfInvalidOptionIsEntered() {
        ByteArrayInputStream inputContent = new ByteArrayInputStream("15".getBytes());
        View view = new View(new Scanner(inputContent));
        LibrarianMenu librarianMenu = new LibrarianMenu(librarianMenuOptions, view, null, null, null, null);

        int actualOption = librarianMenu.selectedOption();

        assertThat(actualOption, is(-1));
    }

    @Test
    public void shouldDisplayOptions() {
        ByteArrayInputStream inputContent = new ByteArrayInputStream("4".getBytes());
        View view = new View(new Scanner(inputContent));
        LibrarianMenu librarianMenu = new LibrarianMenu(librarianMenuOptions, view, null, null, null, null);

        int actualOption = librarianMenu.selectedOption();

        assertThat(actualOption, is(4));
    }

    @Test
    public void shouldDisplayAvailableBooks() {
        Library library = mock(Library.class);
        LibrarianMenu librarianMenu = new LibrarianMenu(librarianMenuOptions, null, null, library, null, null);

        librarianMenu.processOption(1);

        verify(library, times(1)).displayAvailableBooks();
    }

    @Test
    public void shouldDisplayAvailableMovies() {
        View view = mock(View.class);
        Library library = mock(Library.class);
        LibrarianMenu librarianMenu = new LibrarianMenu(librarianMenuOptions, view, null, library, null, null);
        when(view.readLine()).thenReturn("");

        librarianMenu.processOption(2);

        verify(library, times(1)).displayAvailableMovies();
    }

    @Test
    public void shouldCheckOutABook() {
        View view = mock(View.class);
        when(view.readLine()).thenReturn("");
        Librarian librarian = mock(Librarian.class);
        LibrarianMenu librarianMenu = new LibrarianMenu(librarianMenuOptions, view, null, null, librarian, null);

        librarianMenu.processOption(3);

        verify(librarian, times(1)).checkOutBook("");
    }

    @Test
    public void shouldCheckOutAMovie() {
        View view = mock(View.class);
        when(view.readLine()).thenReturn("");
        Librarian librarian = mock(Librarian.class);
        LibrarianMenu librarianMenu = new LibrarianMenu(librarianMenuOptions, view, null, null, librarian, null);

        librarianMenu.processOption(4);

        verify(librarian, times(1)).checkOutMovie("");
    }

    @Test
    public void shouldReturnABook() {
        View view = mock(View.class);
        when(view.readLine()).thenReturn("");
        Librarian librarian = mock(Librarian.class);
        LibrarianMenu librarianMenu = new LibrarianMenu(librarianMenuOptions, view, null, null, librarian, null);

        librarianMenu.processOption(5);

        verify(librarian, times(1)).returnBook("");
    }

    @Test
    public void shouldReturnAMovie() {
        View view = mock(View.class);
        when(view.readLine()).thenReturn("");
        Librarian librarian = mock(Librarian.class);
        LibrarianMenu librarianMenu = new LibrarianMenu(librarianMenuOptions, view, null, null, librarian, null);

        librarianMenu.processOption(6);

        verify(librarian, times(1)).returnMovie("");
    }

    @Test
    public void shouldDisplayCurrentLibrarian() {
        View view = mock(View.class);
        when(view.readLine()).thenReturn("");
        Librarian librarian = mock(Librarian.class);
        Authenticator authenticator = mock(Authenticator.class);
        when(authenticator.currentUser()).thenReturn(new User("a", "b", 12));
        LibrarianMenu librarianMenu = new LibrarianMenu(librarianMenuOptions, view, authenticator, null, librarian, null);

        librarianMenu.processOption(7);

        verify(authenticator, times(1)).currentUser();
    }

    @Test
    public void shouldBeAbleToLogout() {
        View view = mock(View.class);
        when(view.readLine()).thenReturn("");
        Librarian librarian = mock(Librarian.class);
        App app = mock(App.class);
        LibrarianMenu librarianMenu = new LibrarianMenu(librarianMenuOptions, view, null, null, librarian, null);
        librarianMenu.registerApp(app);

        librarianMenu.processOption(9);

        verify(app, times(1)).useStartMenu();
    }

    @After
    public void tearDown() {
        System.setOut(null);
    }
}
