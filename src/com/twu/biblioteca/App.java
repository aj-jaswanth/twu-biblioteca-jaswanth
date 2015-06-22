package com.twu.biblioteca;

import com.twu.biblioteca.menus.Menu;

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
        registerAppWithMenus();
        useStartMenu();
        welcomeUser();
        while (true) {
            if (currentMenu.processOption(getUserOption()) == -1)
                return;
        }
    }

    private void registerAppWithMenus() {
        for (Menu menu : menus)
            menu.registerApp(this);
    }

    public void useStartMenu() {
        currentMenu = menus[0];
    }

    public void useUserMenu() {
        currentMenu = menus[1];
    }

    public void useLibrarianMenu() {
        currentMenu = menus[2];
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