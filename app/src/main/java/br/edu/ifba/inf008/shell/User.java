package br.edu.ifba.inf008.shell;

import br.edu.ifba.inf008.interfaces.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

public class User implements IUser, Serializable {
    private String id;
    private String name;
    private String email;
    private String password;
    private ArrayList<IBook> listBooksLoan;

    public User(String name, String email, String password) {
        this.id = generateUserId();
        this.name = name;
        this.email = email;
        this.password = password;
        this.listBooksLoan = new ArrayList<IBook>();
    }

    public String generateUserId() {
        return UUID.randomUUID().toString();
    }

    public String getID(){
        return id;
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
