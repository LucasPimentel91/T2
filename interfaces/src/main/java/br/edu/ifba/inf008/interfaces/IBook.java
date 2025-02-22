package br.edu.ifba.inf008.interfaces;

public interface IBook {
    public abstract void display();
    public abstract boolean createBook(String title, String author, String ISBN, String genre, String year);
    public abstract boolean isValidTitle(String title);
    public abstract boolean isValidAuthor(String author);
    public abstract boolean isValidISBN(String ISBN);
    public abstract boolean isValidGenre(String genre);
    public abstract boolean isValidPublicationYear(String year);
}
