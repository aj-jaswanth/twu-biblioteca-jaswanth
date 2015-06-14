package com.twu.biblioteca;

import java.util.Scanner;

public class MainMenu {
    Scanner input = new Scanner(System.in);
    private String[] options;

    public MainMenu(String[] options) {
        this.options = options;
    }

    public void displayOptions() {
        for (int index = 0; index < options.length; index++)
            System.out.println((index + 1) + ". " + options[index]);
    }

    public int selectedOption() {
        System.out.print(Messages.MENU_SELECT_PROMPT);
        int selectedOption = input.nextInt();
        if (0 < selectedOption && selectedOption <= options.length)
            return selectedOption;
        System.out.println(Messages.MENU_SELECT_INVALID);
        return -1;
    }
}