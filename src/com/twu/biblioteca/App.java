package com.twu.biblioteca;

import static com.twu.biblioteca.Messages.WELCOME_MESSAGE;

public class App {

    private Library library;
    private View view;
    private Librarian librarian;
    private Menu[] menus;
    private Menu currentMenu;

    public App(Menu[] menus, Library library, Librarian librarian, View view) {
        this.menus = menus;
        this.library = library;
        this.view = view;
        this.librarian = librarian;
    }

    public void start() {
        setStartMenu();
        welcomeUser();
        while (true) {
            if (currentMenu.processOption(getUserOption()) == -1)
                return;
        }
    }

    private void setStartMenu() {
        currentMenu = menus[0];
    }

    public void welcomeUser() {
        view.display(WELCOME_MESSAGE);
    }

    public int getUserOption() {
        int selectedOption;
        while (true) {
            currentMenu.displayOptions();
            selectedOption = currentMenu.selectedOption();
            if (selectedOption == -1)
                continue;
            return selectedOption;
        }
    }
}