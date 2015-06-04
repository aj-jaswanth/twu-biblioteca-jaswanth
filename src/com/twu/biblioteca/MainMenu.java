package com.twu.biblioteca;

public class MainMenu {

    private String[] options;

    public MainMenu(String[] options) {
        this.options = options;
    }

    public int totalAvailableOptions() {
        return options.length;
    }

}
