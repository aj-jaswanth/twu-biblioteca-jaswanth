package com.twu.biblioteca;

import java.util.Scanner;

public class MainMenu {
    private String[] options;

    public MainMenu(String[] options) {
        this.options = options;
    }

    public void displayOptions() {
        for (int index = 0; index < options.length; index++)
            System.out.println((index + 1) + ". " + options[index]);
    }

    public int selectedOption() {
        Scanner input = new Scanner(System.in);
        System.out.println("Select an option : ");
        int selectedOption = input.nextInt();
        if (0 < selectedOption && selectedOption <= options.length)
            return selectedOption;
        System.out.println("Select a valid option!");
        return -1;
    }
}