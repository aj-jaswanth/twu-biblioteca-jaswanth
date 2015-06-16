package com.twu.biblioteca;

public class App {

    private MainMenu mainMenu;
    private Library library;
    private View view;
    private Librarian librarian;

    public App(MainMenu mainMenu, Library library, View view) {
        this.mainMenu = mainMenu;
        this.library = library;
        this.view = view;
    }

    public void start() {
        welcomeUser();
        while (true) {
            int optionSelectedByUser = getUserOption();
            if (optionSelectedByUser == 1)
                library.displayAvailableBooks();
            else if (optionSelectedByUser == 2) {
                view.display(Messages.CHECK_OUT_PROMPT);
                view.readLine();
                library.checkout(view.readLine());
            } else if (optionSelectedByUser == 3) {
                view.display(Messages.RETURN_BOOK_PROMPT);
                view.readLine();
                library.returnBook(view.readLine());
            } else
                return;
        }
    }

    public void welcomeUser() {
        view.display(Messages.WELCOME_MESSAGE);
    }

    public int getUserOption() {
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