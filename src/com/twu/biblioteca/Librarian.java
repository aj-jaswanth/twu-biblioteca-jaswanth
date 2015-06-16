package com.twu.biblioteca;

import java.util.List;

public class Librarian {

    private List<Book> checkedOutBooks;
    private Library library;

    public Librarian(List<Book> checkedOutBooks, Library library) {
        this.checkedOutBooks = checkedOutBooks;
        this.library = library;
    }

    public void checkOutBook(String bookTitle) {
        Book book = library.checkout(bookTitle);
        if (book != null)
            checkedOutBooks.add(book);
    }
}
