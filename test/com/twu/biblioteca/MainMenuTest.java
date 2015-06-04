package com.twu.biblioteca;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class MainMenuTest {

    @Test
    public void totalAvailableOptionShouldBeTwo() {
        MainMenu mainMenu = new MainMenu(new String[]{"List books", "Quit"});

        int actualTotalAvailableOptions = mainMenu.totalAvailableOptions();

        assertThat(actualTotalAvailableOptions, is(equalTo(2)));
    }
}
