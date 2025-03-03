package br.edu.ifba.inf008.interfaces;

import java.util.ArrayList;

import javafx.collections.ObservableList;

public interface IIOController
{   
    public ArrayList<IUser> getListUser();
    public ArrayList<IBook> getListBook();
    public ArrayList<ILoan> getListLoan();
    public ObservableList<IBook> getBookListObs();
    public ObservableList<IUser> getUserListObs();
    public ObservableList<ILoan> getLoanListObs();
    public ObservableList<ILoan> getLoanLateListObs();
    public void addBook(IBook book);
    public void addUser(IUser user);
    public void addLoan(ILoan loan);
    public void saveData();
    public void loadData();
    public void updateLoan(ILoan loan);
    
}
