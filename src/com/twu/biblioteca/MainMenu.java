package com.twu.biblioteca;

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
        view.display(Messages.MENU_SELECT_PROMPT);
        int selectedOption = view.readInteger();
        if (0 < selectedOption && selectedOption <= options.length)
            return selectedOption;
        view.display(Messages.MENU_SELECT_INVALID);
        return -1;
    }
}