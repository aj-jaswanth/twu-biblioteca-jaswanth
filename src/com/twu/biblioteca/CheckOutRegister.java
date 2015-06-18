package com.twu.biblioteca;

import java.util.ArrayList;

public class CheckOutRegister {
    private ArrayList<String> register = new ArrayList<String>();

    public void recordCheckOut(String description) {
        register.add(description);
    }

    public void recordReturn(String description) {
        register.remove(description);
    }

    public boolean isValidReturn(String description) {
        return register.contains(description);
    }

    @Override
    public String toString() {
        String description = "";
        for (String str : register)
            description += str + "\n";
        return description;
    }
}
