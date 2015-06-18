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

public class StartMenuTest {

    private String[] startMenuOptions = new String[]{LIST_BOOKS, LIST_MOVIES, LOGIN, QUIT};
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
        StartMenu startMenu = new StartMenu(startMenuOptions, view, null, null);

        int actualOption = startMenu.selectedOption();

        assertThat(actualOption, is(4));
    }

    @Test
    public void shouldReturnMinuOneIfInvalidOptionIsEntered() {
        ByteArrayInputStream inputContent = new ByteArrayInputStream("5".getBytes());
        View view = new View(new Scanner(inputContent));
        StartMenu startMenu = new StartMenu(startMenuOptions, view, null, null);

        int actualOption = startMenu.selectedOption();

        assertThat(actualOption, is(-1));
    }

    @Test
    public void shouldDisplayOptions() {
        ByteArrayInputStream inputContent = new ByteArrayInputStream("4".getBytes());
        View view = new View(new Scanner(inputContent));
        StartMenu startMenu = new StartMenu(startMenuOptions, view, null, null);

        int actualOption = startMenu.selectedOption();

        assertThat(actualOption, is(4));
    }

    @Test
    public void shouldDisplayAvailableBooks() {
        Library library = mock(Library.class);
        StartMenu startMenu = new StartMenu(startMenuOptions, null, library, null);

        startMenu.processOption(1);

        verify(library, times(1)).displayAvailableBooks();
    }

    @Test
    public void shouldDisplayAvailableMovies() {
        View view = mock(View.class);
        Library library = mock(Library.class);
        StartMenu startMenu = new StartMenu(startMenuOptions, view, library, null);
        when(view.readLine()).thenReturn("");
        
        startMenu.processOption(2);

        verify(library, times(1)).displayAvailableMovies();
    }

    @After
    public void tearDown() {
        System.setOut(null);
    }
}
