package br.edu.ifba.inf008.shell;

import br.edu.ifba.inf008.interfaces.*;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements IUser, Serializable {
    private String name;
    private String email;
    private String password;
    private ArrayList<IBook> listBooksLoan;

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.listBooksLoan = new ArrayList<IBook>();
    }

    public String getName() {
        return name;
    }

    public String getEmail(){
        return email;
    }

    public String getPassword(){
        return password;
    }

    public String toString(){
        return getName();
    }

    public ArrayList<IBook> myListBooks(){
        return listBooksLoan;
    }

}
