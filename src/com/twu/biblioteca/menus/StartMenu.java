package com.twu.biblioteca.menus;

import com.twu.biblioteca.*;

import static com.twu.biblioteca.Messages.MENU_SELECT_INVALID;
import static com.twu.biblioteca.Messages.MENU_SELECT_PROMPT;

public class StartMenu implements Menu {

    private final String[] options;
    private final View view;
    private final Library library;
    private final Librarian librarian;
    private final Authenticator authenticator;
    private App app;
    private User currentUser;

    public StartMenu(String[] options, View view, Authenticator authenticator, Library library, Librarian librarian) {
        this.options = options;
        this.view = view;
        this.authenticator = authenticator;
        this.library = library;
        this.librarian = librarian;
    }

    @Override
    public void displayOptions() {
        for (int index = 0; index < options.length; index++)
            view.display((index + 1) + ". " + options[index]);
    }

    @Override
    public int selectedOption() {
        view.display(MENU_SELECT_PROMPT);
        int selectedOption = view.readInteger();
        if (0 < selectedOption && selectedOption <= options.length)
            return selectedOption;
        view.display(MENU_SELECT_INVALID);
        return -1;
    }

    @Override
    public void registerApp(App app) {
        this.app = app;
    }

    @Override
    public int processOption(int selectedOption) {
        switch (selectedOption) {
            case 1:
                library.displayAvailableBooks();
                break;
            case 2:
                view.readLine();
                library.displayAvailableMovies();
                break;
            case 3:
                view.readLine();
                int userLevel = authenticator.authenticate();
                if (userLevel == 1)
                    app.useUserMenu();
                else if (userLevel == 2)
                    app.useLibrarianMenu();
                break;
            case 4:
                return -1;
        }
        return 0;
    }
}
