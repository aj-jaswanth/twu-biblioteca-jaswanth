package com.twu.biblioteca;

import java.util.Scanner;

public class View {

    private Scanner scanner;

    public View(Scanner scanner) {
        this.scanner = scanner;
    }

    public int readInteger() {
        return scanner.nextInt();
    }

    public void display(String message) {
        System.out.println(message);
    }

    public String readLine() {
        return scanner.nextLine();
    }
}
