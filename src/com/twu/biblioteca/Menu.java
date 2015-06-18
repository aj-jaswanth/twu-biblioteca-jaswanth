package com.twu.biblioteca;

public interface Menu {

    void displayOptions();

    int selectedOption();

    int processOption(int selectedOption);
}
