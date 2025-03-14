package br.edu.ifba.inf008.shell;

import br.edu.ifba.inf008.interfaces.*;

import java.io.Serializable;
import java.util.ArrayList;

public class BookController implements IBookController {
    //private ArrayList<IBook> bookList;

    //public BookController() { this.bookList = new ArrayList<IBook>(); }

    @Override
    public IBook createBook(String title, String author, String ISBN, String genre, String year) {
        if (requestCreateBook(title, author, ISBN, genre, year)) {
            IBook book = new Book(title, author, ISBN, genre, year);
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


    public boolean thisBookExists(IIOController ioController, IBook book) {
        return ioController.getListBook().contains(book);
    }

    public void isLoan(IBook book){
        book.isLoan();
    }
    public void returnBook(IBook book){
        book.returned();
    }

    public void teste(IBook book){
        book.display();
    }
}
