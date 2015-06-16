package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.Scanner;

public class EntryPoint {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        View view = new View(scanner);
        MainMenu mainMenu = new MainMenu(new String[]{Messages.LIST_BOOKS, Messages.LIST_MOVIES,
                Messages.CHECK_OUT_BOOK, Messages.CHECK_OUT_MOVIE, Messages.RETURN_BOOK, Messages.QUIT}, view);
        Library library = new Library(view);
        Librarian librarian = new Librarian(new ArrayList<Book>(), library, view);
        App app = new App(mainMenu, library, librarian, view);
        app.start();
    }
}
