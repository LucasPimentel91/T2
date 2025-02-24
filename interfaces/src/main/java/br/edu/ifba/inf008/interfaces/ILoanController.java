package br.edu.ifba.inf008.interfaces;

import java.util.ArrayList;

public interface ILoanController {
    public boolean isValidUser(IUser user);
    public boolean isValidBook(IBook book);
    public boolean requestSetLoan(IUser user, IBook book);
    public String setDateLoan();
    public String setDateReturn();
    public ILoan setLoan(IUser user, IBook book, String loanDate, String returnDate);
    public ArrayList<ILoan> getLoanList(); 
    public void addLoan(ILoan loan);
}
