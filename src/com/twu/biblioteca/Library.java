package com.twu.biblioteca;


import java.util.HashMap;

public class Library {

    private HashMap<String, Object[]> database = new HashMap<String, Object[]>();

    public void addBook(String name, String author, int year) {
        database.put(name, new Object[]{author, year});
    }

    public int availableBooksCount() {
        return database.size();
    }
}