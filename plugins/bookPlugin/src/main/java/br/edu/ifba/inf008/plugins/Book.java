package br.edu.ifba.inf008.plugins;

import br.edu.ifba.inf008.interfaces.IBook;

public class Book implements IBook {
    private String title;
    private String author;
    private String ISBN;
    private String genre; 
    private String year;

    @Override
    public boolean createBook(String title, String author, String ISBN, String genre, String year) {
        if (!isValidTitle(title) || !isValidAuthor(author) || !isValidISBN(ISBN) || 
            !isValidGenre(genre) || !isValidPublicationYear(year)) {
            return false;
        }
    
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.genre = genre;
        this.year = year;
        
        return true;
    }
    
    public void display() {
        System.out.println("Book: " + title + " by " + author + " ISBN: " + ISBN);
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
}
