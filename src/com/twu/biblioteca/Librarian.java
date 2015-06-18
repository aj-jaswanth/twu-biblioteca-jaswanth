package com.twu.biblioteca;

import java.util.List;

import static com.twu.biblioteca.Messages.*;

public class Librarian {

    private List<Book> checkedOutBooks;
    private Library library;
    private View view;
    private List<Movie> checkedOutMovies;
    private CheckOutRegister checkOutRegister;
    private Authenticator authenticator;

    public Librarian(List<Book> checkedOutBooks, List<Movie> checkedOutMovies, Library library, View view, CheckOutRegister checkOutRegister, Authenticator authenticator) {
        this.checkedOutBooks = checkedOutBooks;
        this.library = library;
        this.view = view;
        this.checkedOutMovies = checkedOutMovies;
        this.checkOutRegister = checkOutRegister;
        this.authenticator = authenticator;
    }

    public void checkOutBook(String bookTitle) {
        SearchAgent<Book> searchAgent = new SearchAgent<Book>(bookTitle);
        library.searchBook(searchAgent);
        Book result = searchAgent.result();
        if (result != null) {
            view.display(CHECK_OUT_BOOK_THANK_YOU);
            checkedOutBooks.add(result);
            library.removeBook(bookTitle);
            checkOutRegister.recordCheckOut(authenticator.currentUserId() + " " + bookTitle);
        } else
            view.display(CHECK_OUT_BOOK_ERROR);
    }

    public void checkOutMovie(String movieTitle) {
        SearchAgent<Movie> searchAgent = new SearchAgent<Movie>(movieTitle);
        library.searchMovie(searchAgent);
        Movie result = searchAgent.result();
        if (result != null) {
            view.display(CHECK_OUT_MOVIE_THANK_YOU);
            checkedOutMovies.add(result);
            library.removeMovie(movieTitle);
            checkOutRegister.recordCheckOut(authenticator.currentUserId() + " " + movieTitle);
        } else
            view.display(CHECK_OUT_MOVIE_ERROR);
    }

    public void returnBook(String bookTitle) {
        int index = 0;
        for (; index < checkedOutBooks.size(); index++) {
            Book book = checkedOutBooks.get(index);
            if (book.hashCode() == bookTitle.hashCode() && checkOutRegister.isValidReturn(authenticator.currentUserId() + " " + bookTitle)) {
                checkedOutBooks.remove(book);
                library.addBook(book);
                view.display(RETURN_BOOK_THANK_YOU);
                checkOutRegister.recordReturn(authenticator.currentUserId() + " " + bookTitle);
                return;
            }
        }
        view.display(RETURN_BOOK_ERROR);
    }

    public void returnMovie(String movieTitle) {
        int index = 0;
        for (; index < checkedOutMovies.size(); index++) {
            Movie movie = checkedOutMovies.get(index);
            if (movie.hashCode() == movieTitle.hashCode() && checkOutRegister.isValidReturn(authenticator.currentUserId() + " " + movieTitle)) {
                checkedOutMovies.remove(movie);
                library.addMovie(movie);
                view.display(RETURN_MOVIE_THANK_YOU);
                checkOutRegister.recordCheckOut(authenticator.currentUserId() + " " + movieTitle);
                return;
            }
        }
        view.display(RETURN_MOVIE_ERROR);
    }
}
