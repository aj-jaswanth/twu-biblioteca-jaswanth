package com.twu.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

public class MainMenuTest {

    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    public void shouldDisplayListOfOptions() {
        View view = new View(null);
        MainMenu mainMenu = new MainMenu(new String[]{"List Books", "Quit"}, view);

        mainMenu.displayOptions();
        String actualOutput = outputStream.toString();

        assertThat(actualOutput, startsWith("1. List Books\n2. Quit\n"));
    }

    @Test
    public void shouldDisplayInvalidMessageWhenInvalidOptionIsSelected() {
        ByteArrayInputStream inputStream = new ByteArrayInputStream("3".getBytes());
        View view = new View(new Scanner(inputStream));
        MainMenu mainMenu = new MainMenu(new String[]{"List Books", "Quit"}, view);

        mainMenu.selectedOption();
        String actualResponse = outputStream.toString();

        assertThat(actualResponse, endsWith("Select a valid option!\n"));
    }

    @Test
    public void shouldReturnMinusOneWhenInvalidOptionIsSelected() {
        ByteArrayInputStream inputStream = new ByteArrayInputStream("7".getBytes());
        View view = new View(new Scanner(inputStream));
        MainMenu mainMenu = new MainMenu(new String[]{"List Books", "Quit"}, view);

        int actualSelectedOption = mainMenu.selectedOption();

        assertThat(actualSelectedOption, is(equalTo(-1)));
    }


    @Test
    public void shouldReturnSelectedOptionNumberWhenAValidOptionIsSelected() {
        ByteArrayInputStream inputStream = new ByteArrayInputStream("1".getBytes());
        View view = new View(new Scanner(inputStream));
        MainMenu mainMenu = new MainMenu(new String[]{"List Books", "Quit"}, view);

        int actualSelectedOption = mainMenu.selectedOption();

        assertThat(actualSelectedOption, is(equalTo(1)));
    }

    @After
    public void tearDown() {
        System.setOut(null);
        System.setIn(System.in);
    }
}
