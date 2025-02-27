package br.edu.ifba.inf008.interfaces;

public interface IBook {
    public String getTitle();
    public String getISBN();
    public String getAuthor();
    public String getGenre();
    public String getYear();
    public void isLoan();
    public void returned();
    public abstract void display();
}
