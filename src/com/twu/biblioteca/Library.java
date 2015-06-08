package com.twu.biblioteca;


import java.util.ArrayList;

public class Library {

    private ArrayList<Book> availableBooks = new ArrayList<Book>();
   
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
}