package br.edu.ifba.inf008.interfaces;

import java.util.ArrayList;

public interface IUser {
    public String getName();
    public String getEmail();
    public String getPassword();
    public String toString();
    public String generateUserId();
    public String getID();
    public ArrayList<IBook> myListBooks();
}
