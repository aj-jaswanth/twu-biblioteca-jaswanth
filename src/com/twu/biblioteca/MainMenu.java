package com.twu.biblioteca;

import static com.twu.biblioteca.Messages.MENU_SELECT_INVALID;
import static com.twu.biblioteca.Messages.MENU_SELECT_PROMPT;

public class MainMenu {
    private String[] options;
    private View view;

    public MainMenu(String[] options, View view) {
        this.options = options;
        this.view = view;
    }

    public void displayOptions() {
        for (int index = 0; index < options.length; index++)
            view.display((index + 1) + ". " + options[index]);
    }

    public int selectedOption() {
        view.display(MENU_SELECT_PROMPT);
        int selectedOption = view.readInteger();
        if (0 < selectedOption && selectedOption <= options.length)
            return selectedOption;
        view.display(MENU_SELECT_INVALID);
        return -1;
    }
}