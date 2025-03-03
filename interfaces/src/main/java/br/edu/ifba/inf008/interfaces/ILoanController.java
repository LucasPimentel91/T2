package br.edu.ifba.inf008.interfaces;

import java.time.LocalDate;
import java.util.ArrayList;

public interface ILoanController {
    public boolean isValidUser(IUser user);
    public boolean isValidBook(IBook book);
    public boolean requestSetLoan(IUser user, IBook book);
    public LocalDate setDateReturn();
    public ILoan setLoan(IUser user, IBook book, LocalDate loanDate);
    public void updateStatusLate(LocalDate date);
    public ILoan findLoanByBook(IBook book);
    /* 
    public ArrayList<ILoan> getLoanList(); 
    public void addLoan(ILoan loan);
    */
}
