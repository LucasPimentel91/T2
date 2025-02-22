package br.edu.ifba.inf008.shell;

import br.edu.ifba.inf008.interfaces.*;
import java.util.ArrayList;

public class BookController implements IBookController {
    private ArrayList<IBook> bookList;

    public BookController() {
        this.bookList = new ArrayList<>(); // Inicializa a lista para evitar NullPointerException
    }

    @Override
    public IBook createBook(String title, String author, String ISBN, String genre, String year) {
        if (requestCreateBook(title, author, ISBN, genre, year)) {
            IBook book = new Book(title, author, ISBN, genre, year);
            addBook(book);
            return book;
        }
        return null;
    }

    public boolean isValidTitle(String title) {
        return title != null && !title.isEmpty();
    }

    public boolean isValidAuthor(String author) {
        return author != null && !author.isEmpty();
    }

    public boolean isValidISBN(String ISBN) {
        return ISBN != null && !ISBN.isEmpty();
    }

    public boolean isValidGenre(String genre) {
        return genre != null && !genre.isEmpty();
    }

    public boolean isValidPublicationYear(String year) {
        return year != null && !year.isEmpty();
    }

    public boolean requestCreateBook(String title, String author, String ISBN, String genre, String year) {
        return isValidTitle(title) && isValidAuthor(author) && isValidISBN(ISBN) &&
               isValidGenre(genre) && isValidPublicationYear(year);
    }

    public ArrayList<IBook> getBookList() {
        return bookList;
    }

    public void addBook(IBook book) {
        if (book != null) {
            bookList.add(book);
        }
    }
}
