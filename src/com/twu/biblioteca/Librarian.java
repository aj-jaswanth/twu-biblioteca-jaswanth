package com.twu.biblioteca;

import java.util.List;

public class Librarian {

    private List<Book> checkedOutBooks;
    private Library library;
    private View view;
    private List<Movie> checkedOutMovies;

    public Librarian(List<Book> checkedOutBooks, List<Movie> checkedOutMovies, Library library, View view) {
        this.checkedOutBooks = checkedOutBooks;
        this.library = library;
        this.view = view;
        this.checkedOutMovies = checkedOutMovies;
    }

    public void checkOutBook(String bookTitle) {
        SearchAgent<Book> searchAgent = new SearchAgent<Book>(bookTitle);
        library.searchBook(searchAgent);
        Book result = searchAgent.result();
        if (result != null) {
            view.display(Messages.CHECK_OUT_BOOK_THANK_YOU);
            checkedOutBooks.add(result);
            library.removeBook(bookTitle);
        } else
            view.display(Messages.CHECK_OUT_BOOK_ERROR);
    }

    public void checkOutMovie(String movieTitle) {
        SearchAgent<Movie> searchAgent = new SearchAgent<Movie>(movieTitle);
        library.searchMovie(searchAgent);
        Movie result = searchAgent.result();
        if (result != null) {
            view.display(Messages.CHECK_OUT_MOVIE_THANK_YOU);
            checkedOutMovies.add(result);
            library.removeMovie(movieTitle);
        } else
            view.display(Messages.CHECK_OUT_MOVIE_ERROR);
    }

    public void returnBook(String bookTitle) {
        int index = 0;
        for (; index < checkedOutBooks.size(); index++) {
            Book book = checkedOutBooks.get(index);
            if (book.hashCode() == bookTitle.hashCode()) {
                checkedOutBooks.remove(book);
                library.addBook(book);
                view.display(Messages.RETURN_BOOK_THANK_YOU);
                return;
            }
        }
        view.display(Messages.RETURN_BOOK_ERROR);
    }
}
