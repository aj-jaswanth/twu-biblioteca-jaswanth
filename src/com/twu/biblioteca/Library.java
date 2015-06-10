package com.twu.biblioteca;

import java.util.ArrayList;

public class Library {

    private ArrayList<Book> availableBooks = new ArrayList<Book>();
    private ArrayList<Book> checkedOutBooks = new ArrayList<Book>();

    public void addBook(Book book) {
        availableBooks.add(book);
    }

    public void displayAvailableBooks() {
        for (int x = 0; x < availableBooks.size(); x++)
            System.out.println((x + 1) + " " + availableBooks.get(x));
    }

    public void checkout(int bookIndex) {
        if (0 < bookIndex && bookIndex <= availableBooks.size()) {
            checkedOutBooks.add(availableBooks.get(bookIndex - 1));
            availableBooks.remove(bookIndex - 1);
        }
    }
}