package com.twu.biblioteca;

public interface Messages {
    String WELCOME_MESSAGE = "Bengaluru public library welcomes you!";
    String LIST_BOOKS = "List Books";
    String LIST_MOVIES = "List Movies";
    String CHECK_OUT_BOOK = "Check Out Book";
    String CHECK_OUT_MOVIE = "Check Out Movie";
    String QUIT = "Quit";
    String CHECK_OUT_BOOK_PROMPT = "Enter book title : ";
    String CHECK_OUT_BOOK_THANK_YOU = "Thank you! Enjoy the book";
    String CHECK_OUT_BOOK_ERROR = "That book is not available";
    String CHECK_OUT_MOVIE_PROMPT = "Enter movie title : ";
    String CHECK_OUT_MOVIE_THANK_YOU = "Thank you! Enjoy the movie";
    String CHECK_OUT_MOVIE_ERROR = "That movie is not available";
    String MENU_SELECT_PROMPT = "Select an option : ";
    String MENU_SELECT_INVALID = "Select a valid option!";
    String RETURN_BOOK_PROMPT = "Enter book title : ";
    String RETURN_BOOK = "Return Book";
    String RETURN_BOOK_THANK_YOU = "Thank you for returning the book";
    String RETURN_BOOK_ERROR = "That is not a valid book to return";
    String RETURN_MOVIE_PROMPT = "Enter movie title : ";
    String RETURN_MOVIE = "Return Movie";
    String RETURN_MOVIE_THANK_YOU = "Thank you for returning the movie";
    String RETURN_MOVIE_ERROR = "That is not a valid movie to return";
    String LOGIN = "Login";
    String LOGOUT = "Logout";
    String ABOUT_USER = "About you";
    String SHOW_HISTORY = "View checked out items";
    String LOGIN_ID_PROMPT = "Enter your library Id : ";
    String LOGIN_PASSWORD_PROMPT = "Enter your password : ";
    String LOGIN_FAILED = "Invalid login!";
}
