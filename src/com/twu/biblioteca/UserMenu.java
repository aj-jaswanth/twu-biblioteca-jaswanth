package com.twu.biblioteca;

import static com.twu.biblioteca.Messages.*;

public class UserMenu implements Menu {
    private final String[] options;
    private final View view;
    private final Library library;
    private final Librarian librarian;
    private App app;
    private Authenticator authenticator;
    private User currentUser;

    public UserMenu(String[] options, View view, Authenticator authenticator, Library library, Librarian librarian) {
        this.options = options;
        this.view = view;
        this.library = library;
        this.librarian = librarian;
        this.authenticator = authenticator;
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
                view.display(authenticator.currentUser() + "");
                break;
            case 8:
                app.useStartMenu();
                break;
        }
        return 0;
    }

    @Override
    public void registerApp(App app) {
        this.app = app;
    }
}
