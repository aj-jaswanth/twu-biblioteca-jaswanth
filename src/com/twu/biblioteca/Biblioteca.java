package com.twu.biblioteca;

import java.util.Scanner;

public class Biblioteca {

    private MainMenu mainMenu = new MainMenu(new String[]{"List Books", "Check Out", "Quit"});
    private Library library = new Library();
    private Scanner scanner = new Scanner(System.in);

    public void start() {
        welcomeUser();
        while (true) {
            int optionSelectedByUser = getOptionFromUser();
            if (optionSelectedByUser == 1)
                library.displayAvailableBooks();
            else if (optionSelectedByUser == 2) {
                System.out.print("Enter book ID : ");
                library.checkout(scanner.nextInt());
            } else
                return;
        }
    }

    public void welcomeUser() {
        System.out.println("Bengaluru public library welcomes you!");
    }

    public int getOptionFromUser() {
        int selectedOption;
        while (true) {
            mainMenu.displayOptions();
            selectedOption = mainMenu.selectedOption();
            if (selectedOption == -1)
                continue;
            return selectedOption;
        }
    }
}