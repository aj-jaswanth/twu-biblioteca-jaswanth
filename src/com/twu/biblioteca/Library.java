package com.twu.biblioteca;

import java.util.ArrayList;

public class Library {

    private ArrayList<Book> availableBooks = new ArrayList<Book>();
    private ArrayList<Book> checkedOutBooks = new ArrayList<Book>();
    private View view;

    public Library(View view) {
        this.view = view;
        availableBooks.add(new Book("Algorithms", "Cormen", 2014));
        availableBooks.add(new Book("Physics", "Michio", 2009));
        availableBooks.add(new Book("C", "Dennis", 1982));
    }

    public void addBook(Book book) {
        availableBooks.add(book);
    }

    public void displayAvailableBooks() {
        for (int x = 0; x < availableBooks.size(); x++)
            view.display((x + 1) + " " + availableBooks.get(x));
    }

    public void displayCheckedOutBooks() {
        for (int x = 0; x < checkedOutBooks.size(); x++)
            view.display((x + 1) + " " + checkedOutBooks.get(x));
    }

    public void checkout(int bookIndex) {
        if (0 < bookIndex && bookIndex <= availableBooks.size()) {
            checkedOutBooks.add(availableBooks.get(bookIndex - 1));
            availableBooks.remove(bookIndex - 1);
            view.display(Messages.CHECK_OUT_THANK_YOU);
        } else
            view.display(Messages.CHECK_OUT_ERROR);
    }

    public void returnBook() {

    }
}