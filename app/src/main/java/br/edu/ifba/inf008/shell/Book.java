package br.edu.ifba.inf008.shell;

import br.edu.ifba.inf008.interfaces.IBook;

public class Book implements IBook {
    private String title;
    private String ISBN;
    private String author;
    private String genre;
    private String year;

    public Book(String title, String ISBN, String author, String genre, String year) {
        this.title = title;
        this.ISBN = ISBN;
        this.author = author;
        this.genre = genre;
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public String getISBN() {
        return ISBN;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public String getYear() {  // O erro estava aqui
        return year;
    }

    @Override
    public void display(){

    }
}

