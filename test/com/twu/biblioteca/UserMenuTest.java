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

public class UserMenuTest {

    private String[] userMenuOptions = new String[]{LIST_BOOKS, LIST_MOVIES, LOGIN, QUIT};
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
        UserMenu userMenu = new UserMenu(userMenuOptions, view, null, null, null);

        int actualOption = userMenu.selectedOption();

        assertThat(actualOption, is(4));
    }

    @Test
    public void shouldReturnMinuOneIfInvalidOptionIsEntered() {
        ByteArrayInputStream inputContent = new ByteArrayInputStream("15".getBytes());
        View view = new View(new Scanner(inputContent));
        UserMenu userMenu = new UserMenu(userMenuOptions, view, null, null, null);

        int actualOption = userMenu.selectedOption();

        assertThat(actualOption, is(-1));
    }

    @Test
    public void shouldDisplayOptions() {
        ByteArrayInputStream inputContent = new ByteArrayInputStream("4".getBytes());
        View view = new View(new Scanner(inputContent));
        UserMenu userMenu = new UserMenu(userMenuOptions, view, null, null, null);

        int actualOption = userMenu.selectedOption();

        assertThat(actualOption, is(4));
    }

    @Test
    public void shouldDisplayAvailableBooks() {
        Library library = mock(Library.class);
        UserMenu userMenu = new UserMenu(userMenuOptions, null, null, library, null);

        userMenu.processOption(1);

        verify(library, times(1)).displayAvailableBooks();
    }

    @Test
    public void shouldDisplayAvailableMovies() {
        View view = mock(View.class);
        Library library = mock(Library.class);
        UserMenu userMenu = new UserMenu(userMenuOptions, view, null, library, null);
        when(view.readLine()).thenReturn("");

        userMenu.processOption(2);

        verify(library, times(1)).displayAvailableMovies();
    }

    @Test
    public void shouldCheckOutABook() {
        View view = mock(View.class);
        when(view.readLine()).thenReturn("");
        Librarian librarian = mock(Librarian.class);
        UserMenu userMenu = new UserMenu(userMenuOptions, view, null, null, librarian);

        userMenu.processOption(3);

        verify(librarian, times(1)).checkOutBook("");
    }

    @Test
    public void shouldCheckOutAMovie() {
        View view = mock(View.class);
        when(view.readLine()).thenReturn("");
        Librarian librarian = mock(Librarian.class);
        UserMenu userMenu = new UserMenu(userMenuOptions, view, null, null, librarian);

        userMenu.processOption(4);

        verify(librarian, times(1)).checkOutMovie("");
    }

    @Test
    public void shouldReturnABook() {
        View view = mock(View.class);
        when(view.readLine()).thenReturn("");
        Librarian librarian = mock(Librarian.class);
        UserMenu userMenu = new UserMenu(userMenuOptions, view, null, null, librarian);

        userMenu.processOption(5);

        verify(librarian, times(1)).returnBook("");
    }

    @Test
    public void shouldReturnAMovie() {
        View view = mock(View.class);
        when(view.readLine()).thenReturn("");
        Librarian librarian = mock(Librarian.class);
        UserMenu userMenu = new UserMenu(userMenuOptions, view, null, null, librarian);

        userMenu.processOption(6);

        verify(librarian, times(1)).returnMovie("");
    }

    @Test
    public void shouldDisplayCurrentUser() {
        View view = mock(View.class);
        when(view.readLine()).thenReturn("");
        Librarian librarian = mock(Librarian.class);
        Authenticator authenticator = mock(Authenticator.class);
        when(authenticator.currentUser()).thenReturn(new User("a", "b", 12));
        UserMenu userMenu = new UserMenu(userMenuOptions, view, authenticator, null, librarian);

        userMenu.processOption(7);

        verify(authenticator, times(1)).currentUser();
    }

    @Test
    public void shouldBeAbleToLogout() {
        View view = mock(View.class);
        when(view.readLine()).thenReturn("");
        Librarian librarian = mock(Librarian.class);
        App app = mock(App.class);
        UserMenu userMenu = new UserMenu(userMenuOptions, view, null, null, librarian);
        userMenu.registerApp(app);

        userMenu.processOption(8);

        verify(app, times(1)).useStartMenu();
    }

    @After
    public void tearDown() {
        System.setOut(null);
    }
}
