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
    private String[] startMenuOptions = {LIST_BOOKS, LIST_MOVIES, LOGIN, QUIT};
    private String[] userMenuOptions = {LIST_BOOKS, LIST_MOVIES, CHECK_OUT_BOOK, CHECK_OUT_MOVIE, RETURN_BOOK, RETURN_MOVIE, ABOUT_USER, LOGOUT};
    private String[] librarianMenuOptions = {LIST_BOOKS, LIST_MOVIES, CHECK_OUT_BOOK, CHECK_OUT_MOVIE, RETURN_BOOK, RETURN_MOVIE, ABOUT_USER, SHOW_HISTORY, LOGOUT};
    private CheckOutRegister checkOutRegister = new CheckOutRegister();

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
        StartMenu startMenu = new StartMenu(startMenuOptions, view, null, library, null);
        Authenticator authenticator = new Authenticator(view);
        UserMenu librerianMenu = new UserMenu(userMenuOptions, view, authenticator, library, null);
        Menu[] menus = {startMenu, librerianMenu};
        App app = new App(menus, library, null, view);

        app.start();
        String actualOutput = outputStream.toString();

        assertEquals("Bengaluru public library welcomes you!\n1. List Books\n2. List Movies\n3. Login\n4. Quit\nSelect an option : \n", actualOutput);
    }

    @Test
    public void shouldSwitchToUserMenuIfLoggedInAsANormalUser() {
        ByteArrayInputStream inputContent = new ByteArrayInputStream("3\n123-123\npwd\n8\n4".getBytes());
        View view = new View(new Scanner(inputContent));
        Library library = new Library(view, new ArrayList<Book>(), new ArrayList<Movie>());
        Authenticator authenticator = new Authenticator(view);
        StartMenu startMenu = new StartMenu(startMenuOptions, view, authenticator, library, null);
        UserMenu userMenu = new UserMenu(userMenuOptions, view, authenticator, library, null);

        Menu[] menus = {startMenu, userMenu};
        App app = new App(menus, library, null, view);

        app.start();
        String actualOutput = outputStream.toString();

        assertEquals("Bengaluru public library welcomes you!\n1. List Books\n2. List Movies\n3. Login\n4. Quit\nSelect an option : \nEnter your library Id : \nEnter your password : \n1. List Books\n2. List Movies\n3. Check Out Book\n4. Check Out Movie\n5. Return Book\n6. Return Movie\n7. About you\n8. Logout\n" +
                "Select an option : \n" +
                "1. List Books\n" +
                "2. List Movies\n" +
                "3. Login\n" +
                "4. Quit\n" +
                "Select an option : \n", actualOutput);
    }

    @Test
    public void shouldSwitchToLibrarianMenuIfLoggedInAsANormalUser() {
        ByteArrayInputStream inputContent = new ByteArrayInputStream("3\n123-321\ndwp\n9\n4".getBytes());
        View view = new View(new Scanner(inputContent));
        Library library = new Library(view, new ArrayList<Book>(), new ArrayList<Movie>());
        Authenticator authenticator = new Authenticator(view);
        StartMenu startMenu = new StartMenu(startMenuOptions, view, authenticator, library, null);
        UserMenu userMenu = new UserMenu(userMenuOptions, view, authenticator, library, null);
        LibrarianMenu librarianMenu = new LibrarianMenu(librarianMenuOptions, view, authenticator, library, null, checkOutRegister);

        Menu[] menus = {startMenu, userMenu, librarianMenu};
        App app = new App(menus, library, null, view);

        app.start();
        String actualOutput = outputStream.toString();

        assertEquals("Bengaluru public library welcomes you!\n1. List Books\n2. List Movies\n3. Login\n4. Quit\nSelect an option : \nEnter your library Id : \nEnter your password : \n1. List Books\n2. List Movies\n3. Check Out Book\n4. Check Out Movie\n5. Return Book\n6. Return Movie\n7. About you\n8. View checked out items\n9. Logout\n" +
                "Select an option : \n" +
                "1. List Books\n" +
                "2. List Movies\n" +
                "3. Login\n" +
                "4. Quit\n" +
                "Select an option : \n", actualOutput);
    }

    @After
    public void tearDown() {
        System.setOut(null);
        System.setIn(System.in);
    }
}
