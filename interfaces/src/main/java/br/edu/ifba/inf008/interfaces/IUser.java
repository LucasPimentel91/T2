package br.edu.ifba.inf008.interfaces;

public interface IUser {
    public String getName();
    public String getEmail();
    public String getPassword();
    public String toString();
    public ArrayList<IBook> myListBooks();
}
