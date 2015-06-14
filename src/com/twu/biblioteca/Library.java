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

    public void checkout(String bookTitle) {
        Book dummyBook = new Book(bookTitle, null, 0);
        int index = 0;
        for (; index < availableBooks.size(); index++)
            if (availableBooks.get(index).equals(dummyBook)) {
                break;
            }
        if (index < availableBooks.size()) {
            checkedOutBooks.add(availableBooks.get(index));
            availableBooks.remove(index);
            view.display(Messages.CHECK_OUT_THANK_YOU);
        } else
            view.display(Messages.CHECK_OUT_ERROR);
    }

    public void returnBook(String bookTitle) {
        Book dummyBook = new Book(bookTitle, null, 0);
        int index = 0;
        for (; index < checkedOutBooks.size(); index++)
            if (checkedOutBooks.get(index).equals(dummyBook)) {
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