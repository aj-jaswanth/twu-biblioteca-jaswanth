package com.twu.biblioteca;

import java.util.ArrayList;

public class Library {

    private ArrayList<Book> availableBooks = new ArrayList<Book>();
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

    public void removeBook(String bookTitle) {
        int index = 0;
        for (; index < availableBooks.size(); index++)
            if (availableBooks.get(index).hashCode() == bookTitle.hashCode())
                break;
        availableBooks.remove(index);
    }

    public void searchBook(SearchAgent searchAgent) {
        for (Book availableBook : availableBooks) {
            if (availableBook.hashCode() == searchAgent.hashCode()) {
                searchAgent.add(availableBook);
                break;
            }
        }
    }

    public void displayAvailableBooks() {
        for (int x = 0; x < availableBooks.size(); x++)
            view.display((x + 1) + " " + availableBooks.get(x));
    }
}