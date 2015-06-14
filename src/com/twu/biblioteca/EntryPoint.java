package com.twu.biblioteca;

import java.util.Scanner;

public class EntryPoint {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        View view = new View(scanner);
        MainMenu mainMenu = new MainMenu(new String[]{Messages.LIST_BOOKS,
                Messages.CHECK_OUT, Messages.QUIT}, view);
        Library library = new Library();
        App app = new App(mainMenu, library, view);
        app.start();
    }
}
