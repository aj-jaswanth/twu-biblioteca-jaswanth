package com.twu.biblioteca.menus;

import com.twu.biblioteca.App;

public interface Menu {

    void displayOptions();

    int selectedOption();

    int processOption(int selectedOption);

    void registerApp(App app);
}
