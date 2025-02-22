package br.edu.ifba.inf008.interfaces;

import java.util.ArrayList;

public interface IBookController {
    public boolean isValidTitle(String title);
    public boolean isValidAuthor(String author);
    public boolean isValidISBN(String ISBN);
    public boolean isValidGenre(String genre);
    public boolean isValidPublicationYear(String year);
    public boolean requestCreateBook(String title, String author, String ISBN, String genre, String year);
    public IBook createBook(String title, String author, String ISBN, String genre, String year); 
    public ArrayList<IBook> getBookList();
    public void addBook(IBook book);

}
