package com.twu.biblioteca;

import static com.twu.biblioteca.Messages.MENU_SELECT_INVALID;
import static com.twu.biblioteca.Messages.MENU_SELECT_PROMPT;

public class StartMenu implements Menu {

    private final String[] options;
    private final View view;
    private final Library library;
    private final Librarian librarian;

    public StartMenu(String[] options, View view, Library library, Librarian librarian) {
        this.options = options;
        this.view = view;
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
                return 1;
            case 4:
                return -1;
        }
        return 0;
    }
}
