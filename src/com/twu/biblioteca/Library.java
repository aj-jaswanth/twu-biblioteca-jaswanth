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

    public void removeBook(String bookTitle) {
        int index = 0;
        for (; index < availableBooks.size(); index++)
            if (availableBooks.get(index).hashCode() == bookTitle.hashCode())
                break;
        availableBooks.remove(index);
    }

    public void displayAvailableBooks() {
        for (int x = 0; x < availableBooks.size(); x++)
            view.display((x + 1) + " " + availableBooks.get(x));
    }

    public Book checkout(String bookTitle) {
        int index = 0;
        Book book = null;
        for (; index < availableBooks.size(); index++)
            if (availableBooks.get(index).hashCode() == bookTitle.hashCode()) {
                break;
            }
        if (index < availableBooks.size()) {
            book = availableBooks.get(index);
            checkedOutBooks.add(book);
            availableBooks.remove(index);
            view.display(Messages.CHECK_OUT_THANK_YOU);
        } else
            view.display(Messages.CHECK_OUT_ERROR);
        return book;
    }

    public void returnBook(String bookTitle) {
        int index = 0;
        for (; index < checkedOutBooks.size(); index++)
            if (checkedOutBooks.get(index).hashCode() == bookTitle.hashCode()) {
                break;
            }
        if (index < checkedOutBooks.size()) {
            availableBooks.add(checkedOutBooks.get(index));
            checkedOutBooks.remove(index);
            view.display(Messages.RETURN_BOOK_THANK_YOU);
        } else
            view.display(Messages.RETURN_BOOK_ERROR);
    }
}