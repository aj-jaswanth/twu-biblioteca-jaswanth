package com.twu.biblioteca;

import static com.twu.biblioteca.Messages.*;

public class App {

    private MainMenu mainMenu;
    private Library library;
    private View view;
    private Librarian librarian;

    public App(MainMenu mainMenu, Library library, Librarian librarian, View view) {
        this.mainMenu = mainMenu;
        this.library = library;
        this.view = view;
        this.librarian = librarian;
    }

    public void start() {
        welcomeUser();
        while (true) {
            try {
                switch (getUserOption()) {
                    case 1:
                        library.displayAvailableBooks();
                        break;
                    case 2:
                        view.readLine();
                        library.displayAvailableMovies();
                        break;
                    case 3:
                        view.display(CHECK_OUT_BOOK_PROMPT);
                        view.readLine();
                        librarian.checkOutBook(view.readLine());
                        break;
                    case 4:
                        view.display(CHECK_OUT_MOVIE_PROMPT);
                        view.readLine();
                        librarian.checkOutMovie(view.readLine());
                        break;
                    case 5:
                        view.display(RETURN_BOOK_PROMPT);
                        view.readLine();
                        librarian.returnBook(view.readLine());
                        break;
                    case 6:
                        view.display(RETURN_MOVIE_PROMPT);
                        view.readLine();
                        librarian.returnMovie(view.readLine());
                        break;
                    case 7:
                        return;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void welcomeUser() {
        view.display(WELCOME_MESSAGE);
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