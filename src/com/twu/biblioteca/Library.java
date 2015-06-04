package com.twu.biblioteca;


import java.util.HashMap;

public class Library {

    private HashMap<String, Object[]> database = new HashMap<String, Object[]>();

    public void addBook(String name, String author, int year) {
        database.put(name, new Object[]{author, year, 0});
    }

    public int availableBooksCount() {
        return database.size();
    }

    public void checkOutBook(String bookName) {
        Object[] book = database.get(bookName);
        if (book != null && (Integer) book[2] == 0) {
            System.out.println("Thank you! Enjoy the book");
        } else
            System.out.println("That book is not available!");
    }

    public boolean isInLibrary(String bookName) {
        Object[] book = database.get(bookName);
        return book != null && (Integer) book[2] == 1;
    }
}