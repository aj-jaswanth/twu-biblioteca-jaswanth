package com.twu.biblioteca;

import org.junit.After;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class ViewTest {

    @Test
    public void shouldDisplayGivenMessage() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    public void shouldTakeIntegerInput() {
        ByteArrayInputStream inputStream = new ByteArrayInputStream("23\n".getBytes());
        View view = new View(new Scanner(inputStream));

        int inputInteger = view.readInteger();

        assertEquals(23, inputInteger);
    }

    @Test
    public void shouldTakeStringInput() {
        ByteArrayInputStream inputStream = new ByteArrayInputStream("abcd\n".getBytes());
        View view = new View(new Scanner(inputStream));

        String actualString = view.readLine();

        assertEquals("abcd", actualString);
    }

    @After
    public void tearDown() {
        System.setOut(null);
        System.setIn(System.in);
    }
}
