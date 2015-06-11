package com.twu.biblioteca;

import java.util.ArrayList;

public class Library {

    private ArrayList<Book> availableBooks = new ArrayList<Book>();
    private ArrayList<Book> checkedOutBooks = new ArrayList<Book>();

    public Library() {
        availableBooks.add(new Book("Algorithms", "Cormen", 2014));
        availableBooks.add(new Book("Physics", "Michio", 2009));
        availableBooks.add(new Book("C", "Dennis", 1982));
    }

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
            System.out.println("Thank you! Enjoy the book");
        } else
            System.out.println("That book is not available");
    }
}