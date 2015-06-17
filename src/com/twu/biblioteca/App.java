package com.twu.biblioteca;

import static com.twu.biblioteca.Messages.WELCOME_MESSAGE;

public class App {

    private BasicMainMenu basicMainMenu;
    private Library library;
    private View view;
    private Librarian librarian;
    private BasicMainMenu[] basicMainMenus;

    public App(BasicMainMenu basicMainMenu, Library library, Librarian librarian, View view) {
        this.basicMainMenu = basicMainMenu;
        this.library = library;
        this.view = view;
        this.librarian = librarian;
    }

    public void start() {
        welcomeUser();
        while (true) {
            if (basicMainMenu.processOption(getUserOption()) == -1)
                return;
        }
    }

    public void welcomeUser() {
        view.display(WELCOME_MESSAGE);
    }

    public int getUserOption() {
        int selectedOption;
        while (true) {
            basicMainMenu.displayOptions();
            selectedOption = basicMainMenu.selectedOption();
            if (selectedOption == -1)
                continue;
            return selectedOption;
        }
    }
}