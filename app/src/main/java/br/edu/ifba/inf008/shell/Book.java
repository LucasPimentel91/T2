package br.edu.ifba.inf008.shell;

import java.io.Serializable;

import br.edu.ifba.inf008.interfaces.IBook;

public class Book implements IBook, Serializable {
    private String title;
    private String ISBN;
    private String author;
    private String genre;
    private String year;
    private Boolean status;

    public Book(String title, String ISBN, String author, String genre, String year) {
        this.title = title;
        this.ISBN = ISBN;
        this.author = author;
        this.genre = genre;
        this.year = year;
        this.status = false;
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

    public String getYear() {  
        return year;
    }

    public void isLoan(){
        status = true;
    }

    public void returned(){
        status = false;
    }

    public String toString(){
        return getTitle();
    }

    @Override
    public void display(){
        System.out.println(title);
    }
}

