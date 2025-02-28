package br.edu.ifba.inf008.interfaces;

import java.util.ArrayList;

public interface IUserController {
    public boolean isValidName(String name);
    public boolean isValidEmail(String email);
    public boolean isValidPassword(String password);
    public boolean requestCreateUser(String name, String email, String password);
    public IUser createUser(String name, String email, String password); 
    public boolean thisUserExists(IIOController iioController, IUser user);
    /* 
    public ArrayList<IUser> getUserList();
    public void addUser(IUser user);
    */
    public ArrayList<IBook> getListBooks(IUser user);
}
