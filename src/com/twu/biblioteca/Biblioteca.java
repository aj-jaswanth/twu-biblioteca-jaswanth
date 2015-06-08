package com.twu.biblioteca;

public class Biblioteca {

    private MainMenu mainMenu = new MainMenu(new String[]{"List Books", "Quit"});
    private Library library = new Library();

    public void start() {
        welcomeUser();
        while (true) {
            if (getOptionFromUser() == 1)
                library.displayAvailableBooks();
            else
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