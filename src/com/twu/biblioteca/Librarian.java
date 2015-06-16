package com.twu.biblioteca;

import java.util.List;

public class Librarian {

    private List<Book> checkedOutBooks;
    private Library library;
    private View view;

    public Librarian(List<Book> checkedOutBooks, Library library, View view) {
        this.checkedOutBooks = checkedOutBooks;
        this.library = library;
        this.view = view;
    }

    public void checkOutBook(String bookTitle) {
        SearchAgent<Book> searchAgent = new SearchAgent<Book>(bookTitle);
        library.searchBook(searchAgent);
        Book result = searchAgent.result();
        if (result != null) {
            view.display(Messages.CHECK_OUT_THANK_YOU);
            checkedOutBooks.add(result);
            library.removeBook(bookTitle);
        } else
            view.display(Messages.CHECK_OUT_ERROR);
    }
}
