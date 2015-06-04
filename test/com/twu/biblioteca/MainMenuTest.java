package com.twu.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

public class MainMenuTest {

    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @Before
    public void setUpStream() {
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    public void totalAvailableOptionShouldBeTwo() {
        MainMenu mainMenu = new MainMenu(new String[]{"List books", "Quit"});

        int actualTotalAvailableOptions = mainMenu.totalAvailableOptions();

        assertThat(actualTotalAvailableOptions, is(equalTo(2)));
    }

    @Test
    public void displayListOfOptions() {
        MainMenu mainMenu = new MainMenu(new String[]{"List Books", "Quit"});

        mainMenu.displayOptions();
        String actualOutput = outputStream.toString();

        assertThat(actualOutput, startsWith("1. List Books\n2. Quit\n"));
    }

    @Test
    public void shouldDisplayInvalidMessageWhenInvalidOptionIsSelected() {
        MainMenu mainMenu = new MainMenu(new String[]{"List Books", "Quit"});
        ByteArrayInputStream inputStream = new ByteArrayInputStream("3".getBytes());
        System.setIn(inputStream);

        mainMenu.selectedOption();
        String actualResponse = outputStream.toString();

        assertThat(actualResponse, startsWith("Select a valid option!"));
    }

    @After
    public void cleanUpStream() {
        System.setOut(null);
        System.setIn(null);
    }
}
