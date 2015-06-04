package com.twu.biblioteca;

import java.util.Scanner;

public class MainMenu {

    private String[] options;

    public MainMenu(String[] options) {
        this.options = options;
    }

    public int totalAvailableOptions() {
        return options.length;
    }

    public void displayOptions() {
        for (int index = 0; index < options.length; index++)
            System.out.println((index + 1) + ". " + options[index]);
        System.out.println("Select an option : ");
    }

    public int selectedOption() {
        Scanner input = new Scanner(System.in);
        int selectedOption = input.nextInt();
        if (0 < selectedOption && selectedOption < totalAvailableOptions() == false) {
            System.out.println("Select a valid option!");
            return -1;
        }
        return selectedOption;
    }
}