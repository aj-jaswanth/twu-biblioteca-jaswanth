package com.twu.biblioteca;

import static com.twu.biblioteca.Messages.*;

public class BasicMainMenu {
    private String[] options;
    private View view;
    private final Library library;
    private final Librarian librarian;

    public BasicMainMenu(String[] options, View view, Library library, Librarian librarian) {
        this.options = options;
        this.view = view;
        this.library = library;
        this.librarian = librarian;
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

    public int processOption(int selectedOption) {
        try {
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
                    return -1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}